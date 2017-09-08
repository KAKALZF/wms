package com._520it.wms1.domain;

import generator.ObjectProp;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@ObjectProp("商品")
public class Product extends BaseDomain {
	@ObjectProp("商品名称")
	private String name;
	@ObjectProp("商品编号")
	private String sn;
	@ObjectProp("成本价格")
	private BigDecimal costPrice;
	@ObjectProp("销售价格")
	private BigDecimal salePrice;
	@ObjectProp("图片")
	private String imagePath;
	@ObjectProp("货品介绍")
	private String intro;
	@ObjectProp("品牌")
	private Brand brand;

	public String getSmallImagePath() {
		String fileName = imagePath.substring(0, imagePath.lastIndexOf("."))
				+ "_small";
		String ext = imagePath.substring(imagePath.lastIndexOf("."),
				imagePath.length());
		return fileName + ext;

	}

	public String getJsonString() {
		Map<String, Object> json = new HashMap<>();
		json.put("name", name);
		json.put("brandName", brand.getName());
		json.put("costPrice", costPrice);
		json.put("salePrice", salePrice);
		json.put("id",id);
		return JSONObject.toJSONString(json);
	}

}
