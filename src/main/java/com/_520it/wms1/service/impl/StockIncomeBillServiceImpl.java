package com._520it.wms1.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.Depot;
import com._520it.wms1.domain.Product;
import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.domain.StockIncomeBill;
import com._520it.wms1.domain.StockIncomeBillItem;
import com._520it.wms1.mapper.ProductStockMapper;
import com._520it.wms1.mapper.StockIncomeBillItemMapper;
import com._520it.wms1.mapper.StockIncomeBillMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.StockIncomeBillQueryObject;
import com._520it.wms1.service.IStockIncomeBillService;
import com._520it.wms1.util.UserContextUtil;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	private StockIncomeBillMapper mapper;
	@Setter
	private StockIncomeBillItemMapper stockIncomeBillItemMapper;
	@Setter
	private ProductStockMapper productStockMapper;

	@Override
	public void audit(Long id) {
		StockIncomeBill sb = mapper.get(id);
		//如果未审核则进行审核
		if (sb.getStatus() == StockIncomeBill.STATUS_NORMAL) {
			//设置审核信息
			sb.setAuditor(UserContextUtil.getCurrentUser());
			sb.setAuditTime(new Date());
			sb.setStatus(sb.STATUS_AUDIT);
			mapper.updateAudit(sb);
			//----------库存的改变------------------
			//根据仓库id和货品id查库存
			List<StockIncomeBillItem> items = sb.getItems();
			Long depotId = sb.getDepot().getId();
			for (StockIncomeBillItem item : items) {
				Long productId = item.getProduct().getId();
				ProductStock stock = productStockMapper
						.selectStockByProductIdAndDepotId(productId, depotId);
				if (stock == null) {
					//库存为空则插入一条数据
					ProductStock newtStock = new ProductStock();
					//为库存对象注入产品
					Depot d = new Depot();
					d.setId(depotId);
					newtStock.setDepot(d);
					//注入总额
					newtStock.setAmount(item.getAmount());
					//注入成本价
					newtStock.setPrice(item.getCostPrice());
					//注入总数量
					newtStock.setStoreNumber(item.getNumber());
					//为库存对象注入产品
					Product p = new Product();
					p.setId(productId);
					newtStock.setProduct(p);
					//插入一条库存数据
					productStockMapper.save(newtStock);
				} else {
					//若已存在库存,则修改其成本价格和数量,总金额
					stock.setAmount(stock.getAmount().add(item.getAmount()));
					stock.setStoreNumber(stock.getStoreNumber().add(
							item.getNumber()));
					stock.setPrice(stock.getAmount().divide(
							stock.getStoreNumber(), RoundingMode.HALF_UP));
					productStockMapper.update(stock);
				}
			}
		}

	}

	public void delete(Long id) {
		stockIncomeBillItemMapper.deleteItemsByBillId(id);
		mapper.delete(id);
	}

	public void save(StockIncomeBill sb) {
		//保存制单人和制单时间
		sb.setInputUser(UserContextUtil.getCurrentUser());
		sb.setInputTime(new Date());
		//设置总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		//保存订单明细
		List<StockIncomeBillItem> items = sb.getItems();
		for (StockIncomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			//计算单条库存明细的总金额
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amount);
			totalAmount = totalAmount.add(amount);
		}
		//设置库存单的总数量和总金额
		sb.setTotalAmount(totalAmount);
		sb.setTotalNumber(totalNumber);
		//保存明细
		mapper.save(sb);
		for (StockIncomeBillItem item : items) {
			item.setBillId(sb.getId());
			stockIncomeBillItemMapper.save(item);
		}
	}

	public StockIncomeBill get(Long id) {
		return mapper.get(id);
	}

	public List<StockIncomeBill> list() {
		return mapper.list();
	}

	public void update(StockIncomeBill sb) {
		//删除原来的入库单明细
		stockIncomeBillItemMapper.deleteItemsByBillId(sb.getId());
		//保存制单人和制单时间
		sb.setInputUser(UserContextUtil.getCurrentUser());
		sb.setInputTime(new Date());
		System.out.println(sb);
		//设置入库单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		List<StockIncomeBillItem> items = sb.getItems();
		for (StockIncomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			//计算单条库存明细的总金额
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amount);
			totalAmount = totalAmount.add(amount);
		}
		//设置库存单的总数量和总金额
		sb.setTotalAmount(totalAmount);
		sb.setTotalNumber(totalNumber);
		//更新入库单
		mapper.update(sb);
		//保存明细
		for (StockIncomeBillItem item : items) {
			item.setBillId(sb.getId());
			stockIncomeBillItemMapper.save(item);
		}
	}

	@Override
	public PageResult pageQuery(StockIncomeBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<StockIncomeBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}

}
