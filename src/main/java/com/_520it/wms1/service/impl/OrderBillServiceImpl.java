package com._520it.wms1.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import lombok.Setter;

import com._520it.wms1.domain.OrderBill;
import com._520it.wms1.domain.OrderBillItem;
import com._520it.wms1.mapper.OrderBillItemMapper;
import com._520it.wms1.mapper.OrderBillMapper;
import com._520it.wms1.page.PageResult;
import com._520it.wms1.query.OrderBillQueryObject;
import com._520it.wms1.service.IOrderBillService;
import com._520it.wms1.util.UserContextUtil;

public class OrderBillServiceImpl implements IOrderBillService {
	@Setter
	private OrderBillMapper mapper;
	@Setter
	private OrderBillItemMapper billItemMapper;

	public void delete(Long id) {
		billItemMapper.deleteByBillId(id);
		mapper.delete(id);
	}

	public void save(OrderBill bill) {
		//设置制单人和制单时间
		bill.setInputUser(UserContextUtil.getCurrentUser());
		bill.setInputTime(new Date());
		//重新设定为待审核状态
		bill.setStatus(OrderBill.STATUS_NORMAL);
		BigDecimal totalAmount = BigDecimal.ZERO;
		BigDecimal totalNumber = BigDecimal.ZERO;
		//迭代出明细中的每一个对象
		for (OrderBillItem item : bill.getItems()) {
			BigDecimal amount = item.getCostPrice().multiply(item.getNumber())
					.setScale(2, RoundingMode.HALF_UP);
			item.setAmount(amount);
			totalAmount = totalAmount.add(amount);
			totalNumber = totalNumber.add(item.getNumber());
		}
		//设置单据的总额和总数量
		bill.setTotalAmount(totalAmount);
		bill.setTotalNumber(totalNumber);
		//保存订单对象
		mapper.save(bill);
		//保存订单明细
		for (OrderBillItem item : bill.getItems()) {
			item.setBillId(bill.getId());
			billItemMapper.save(item);

		}
	}

	public void update(OrderBill bill) {
		OrderBill old = mapper.get(bill.getId());
		//如果未审核则进行修改
		if (old.getStatus() == old.STATUS_NORMAL) {
			billItemMapper.deleteByBillId(old.getId());
			BigDecimal totalAmount = BigDecimal.ZERO;
			BigDecimal totalNumber = BigDecimal.ZERO;
			//迭代出明细中的每一个对象
			for (OrderBillItem item : bill.getItems()) {
				BigDecimal amount = item.getCostPrice()
						.multiply(item.getNumber())
						.setScale(2, RoundingMode.HALF_UP);
				item.setAmount(amount);
				totalAmount = totalAmount.add(amount);
				totalNumber = totalNumber.add(item.getNumber());
			}
			//设置单据的总额和总数量
			bill.setTotalAmount(totalAmount);
			bill.setTotalNumber(totalNumber);
			mapper.update(bill);
			//保存订单明细
			for (OrderBillItem item : bill.getItems()) {
				item.setBillId(bill.getId());
				billItemMapper.save(item);

			}
			mapper.update(bill);
		}
	}

	public OrderBill get(Long id) {

		return mapper.get(id);
	}

	public List<OrderBill> list() {
		return mapper.list();
	}

	@Override
	public PageResult pageQuery(OrderBillQueryObject qo) {
		Long count = mapper.getTotalCount(qo);
		if (count <= 0) {
			return PageResult.emptyResult;
		}
		List<OrderBill> listData = mapper.getListData(qo);
		PageResult pageResult = new PageResult(listData, count.intValue(),
				qo.getCurrentPage(), qo.getPageSize());
		return pageResult;
	}

	@Override
	public void audit(Long billId) {
		//查询出待审核的订单
		OrderBill orderBill = mapper.get(billId);
		//订单为未审核状态则精心审核
		if (orderBill.getStatus() == orderBill.STATUS_NORMAL) {
			//设置审核人
			orderBill.setAuditor(UserContextUtil.getCurrentUser());
			//设置审核时间
			orderBill.setAuditTime(new Date());
			//修改审核状态
			orderBill.setStatus(orderBill.STATUS_AUDIT);
			//更新审核
			mapper.updateAudit(orderBill);
		}
	}
}
