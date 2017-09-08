package com._520it.wms1.web.action;

import com._520it.wms1.query.OrderBillChartQueryObject;
import com._520it.wms1.query.SaleChartQueryObject;
import com._520it.wms1.service.IBrandService;
import com._520it.wms1.service.IChartService;
import com._520it.wms1.service.IClientService;
import com._520it.wms1.service.ISupplierService;
import com.alibaba.fastjson.JSON;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChartAction extends BaseAction {
    private static final long serialVersionUID = 1L;
    @Setter
    IChartService chartService;
    @Setter
    ISupplierService supplierService;
    @Setter
    IBrandService brandService;
    @Setter
    IClientService clientService;
    @Getter
    private OrderBillChartQueryObject qo = new OrderBillChartQueryObject();
    @Getter
    private SaleChartQueryObject sqo = new SaleChartQueryObject();

    //订单报表
    public String orderChart() throws Exception {
        System.out.println(qo);
        put("suppliers", supplierService.list());
        put("brands", brandService.list());
        put("groupTypes", OrderBillChartQueryObject.GROUP_BY_TYPE);
        put("listData", chartService.queryOrderBillChart(qo));
        return "orderChart";
    }

    //销售报表
    public String saleChart() throws Exception {
        System.out.println(sqo);
        put("brands", brandService.list());
        put("clients", clientService.list());
        put("groupTypes", SaleChartQueryObject.GROUP_BY_TYPE);
        put("listData", chartService.querySaleBillChart(sqo));
        return "saleChart";
    }

    //销售报表柱状图
    public String saleChartByBar() throws Exception {
        System.out.println(sqo);
        put("brands", brandService.list());
        put("clients", clientService.list());
        put("groupTypes", SaleChartQueryObject.GROUP_BY_TYPE);
        List<Map<String, Object>> list = chartService.querySaleBillChart(sqo);
        put("listData", list);
        //--------------------------------
        //存储分组的对象数据,格式[对象1,对象2,...]
        List<Object> groupByTypeList = new ArrayList<>();
        //存储分组的对象对应的数据,格式[数据1,数据2,...]
        List<Object> saleAmountList = new ArrayList<>();
        for (Map<String, Object> map : list) {
            groupByTypeList.add(map.get("groupType"));
            saleAmountList.add(map.get("saleAmount"));
        }
        put("groupByType",
                SaleChartQueryObject.GROUP_BY_TYPE.get(sqo.getGroupByType()));
        put("groupByTypeList", JSON.toJSONString(groupByTypeList));
        //JSON.toJSONString()的作用:将[admin, 赵总]--->["admin","赵总"]
        System.out.println(JSON.toJSONString(groupByTypeList));
        System.out.println(groupByTypeList);
        put("saleAmountList", JSON.toJSONString(saleAmountList));
        return "saleChartByBar";
    }

    //销售报表饼状图
    public String saleChartByPie() throws Exception {
        System.out.println(sqo);
        put("brands", brandService.list());
        put("clients", clientService.list());
        //将分组类型放至值栈
        put("groupTypes", SaleChartQueryObject.GROUP_BY_TYPE);
        //
        List<Map<String, Object>> list = chartService.querySaleBillChart(sqo);
        //list:[{groupType=admin, saleAmount=1567500.00, grossProfit=30000.00, totalNumber=720.00},
        // {groupType=赵总, saleAmount=99000.00, grossProfit=15000.00, totalNumber=60.00}]
        System.out.println(list);
        put("listData", list);
        //--------------------------------
        List<Object> groupByTypeList = new ArrayList<>();
        List<Map<String, Object>> data = new ArrayList<>();
        for (Map<String, Object> map : list) {
            groupByTypeList.add(map.get("groupType"));
            Map<String, Object> dataMap = new HashMap<String, Object>();
            data.add(dataMap);
            dataMap.put("name", map.get("groupType"));
            dataMap.put("value", map.get("saleAmount"));
        }
        put("groupByType",
                SaleChartQueryObject.GROUP_BY_TYPE.get(sqo.getGroupByType()));
        put("dataList", JSON.toJSONString(data));
        put("groupByTypeList", JSON.toJSONString(groupByTypeList));
        return "saleChartByPie";
    }
}
