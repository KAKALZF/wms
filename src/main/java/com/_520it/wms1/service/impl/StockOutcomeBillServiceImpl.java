package com._520it.wms1.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.ProductStock;
import com._520it.wms1.domain.SaleAccount;
import com._520it.wms1.domain.StockOutcomeBill;
import com._520it.wms1.domain.StockOutcomeBillItem;
import com._520it.wms1.mapper.ProductStockMapper;
import com._520it.wms1.mapper.SaleAccountMapper;
import com._520it.wms1.mapper.StockOutcomeBillItemMapper;
import com._520it.wms1.mapper.StockOutcomeBillMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.StockOutcomeBillQueryObject;
import com._520it.wms1.service.IStockOutcomeBillService;
import com._520it.wms1.util.UserContextUtil;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService {
	@Setter
	private StockOutcomeBillMapper mapper;
	@Setter
	private StockOutcomeBillItemMapper stockOutcomeBillItemMapper;
	@Setter
	private ProductStockMapper productStockMapper;
	@Setter
	private SaleAccountMapper saleAccountMapper;

	@Override
	public void audit(Long id) {
		StockOutcomeBill sb = mapper.get(id);
		//如果未审核则进行审核
		if (sb.getStatus() == StockOutcomeBill.STATUS_NORMAL) {
			//设置审核信息
			sb.setAuditor(UserContextUtil.getCurrentUser());
			sb.setAuditTime(new Date());
			sb.setStatus(sb.STATUS_AUDIT);
			mapper.updateAudit(sb);
			//----------库存的改变------------------

			//获取仓库的id
			Long depotId = sb.getDepot().getId();
			//获取出库明细
			List<StockOutcomeBillItem> items = sb.getItems();
			for (StockOutcomeBillItem item : items) {
				//获取产品的id
				Long productId = item.getProduct().getId();
				//根据仓库id和货品id查库存
				ProductStock stock = productStockMapper
						.selectStockByProductIdAndDepotId(productId, depotId);
				//如果库存为空
				if (stock == null) {
					throw new RuntimeException(sb.getDepot().getName()
							+ "仓库中没有货品:" + item.getProduct().getName());
				}
				//如果库存不足
				if (stock.getStoreNumber().compareTo(item.getNumber()) < 0) {
					throw new RuntimeException(sb.getDepot().getName()
							+ "仓库中货品:" + item.getProduct().getName() + "不足");
				}
				//库存足够,则减去库存中相应的出库数量
				stock.setStoreNumber(stock.getStoreNumber().subtract(
						item.getNumber()));//减少库存
				stock.setAmount(stock.getStoreNumber().multiply(
						stock.getPrice()));//重新计算库存总金额
				productStockMapper.update(stock);
				//-----------记录销售账----------------
				SaleAccount sa = new SaleAccount();
				//设置客户
				sa.setClient(sb.getClient());
				//设置单个成本
				sa.setCostPrice(stock.getPrice());
				//设置成本总额
				sa.setCostAmount(item.getNumber().multiply(stock.getPrice()));
				//设置销售的数量
				sa.setNumber(item.getNumber());
				//设置销售的产品
				sa.setProduct(item.getProduct());
				//设置销售价格
				sa.setSalePrice(item.getSalePrice());
				//设置销售总额
				sa.setSaleAmount(item.getNumber().multiply(item.getSalePrice()));
				//设置销售人
				sa.setSaleman(UserContextUtil.getCurrentUser());
				//设置销售日期
				sa.setVdate(new Date());
				//新增销售表格数据
				saleAccountMapper.save(sa);

			}
		}

	}

	public void delete(Long id) {
		stockOutcomeBillItemMapper.deleteItemsByBillId(id);
		mapper.delete(id);
	}

	public void save(StockOutcomeBill sb) {
		//保存制单人和制单时间
		sb.setInputUser(UserContextUtil.getCurrentUser());
		sb.setInputTime(new Date());
		//设置总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		//保存订单明细
		List<StockOutcomeBillItem> items = sb.getItems();
		for (StockOutcomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			//计算单条库存明细的总金额
			BigDecimal amount = item.getSalePrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amount);
			totalAmount = totalAmount.add(amount);
		}
		//设置库存单的总数量和总金额
		sb.setTotalAmount(totalAmount);
		sb.setTotalNumber(totalNumber);
		//保存明细
		mapper.save(sb);
		for (StockOutcomeBillItem item : items) {
			item.setBillId(sb.getId());
			stockOutcomeBillItemMapper.save(item);
		}
	}

	public StockOutcomeBill get(Long id) {
		return mapper.get(id);
	}

	public void update(StockOutcomeBill sb) {
		//删除原来的入库单明细
		stockOutcomeBillItemMapper.deleteItemsByBillId(sb.getId());
		//保存制单人和制单时间
		sb.setInputUser(UserContextUtil.getCurrentUser());
		sb.setInputTime(new Date());
		System.out.println(sb);
		//设置入库单的总金额和总数量
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		List<StockOutcomeBillItem> items = sb.getItems();
		for (StockOutcomeBillItem item : items) {
			totalNumber = totalNumber.add(item.getNumber());
			//计算单条库存明细的总金额
			BigDecimal amount = item.getSalePrice().multiply(item.getNumber())
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
		for (StockOutcomeBillItem item : items) {
			item.setBillId(sb.getId());
			stockOutcomeBillItemMapper.save(item);
		}
	}

	@Override
	public PageResult pageQuery(StockOutcomeBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<StockOutcomeBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}

}
