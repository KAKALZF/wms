package com._520it.wms1.web.action;

import java.io.File;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

import org.apache.struts2.ServletActionContext;
import org.springframework.util.StringUtils;

import com._520it.wms1.domain.Brand;
import com._520it.wms1.domain.Product;
import com._520it.wms1.domain.RequiredPermission;
import com._520it.wms1.query.ProductQueryObject;
import com._520it.wms1.service.IBrandService;
import com._520it.wms1.service.IProductService;
import com._520it.wms1.util.FileUploadUtil;
import com.opensymphony.xwork2.ActionContext;

public class ProductAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	@Setter
	private IProductService service;
	@Setter
	private IBrandService brandService;
	@Getter
	private ProductQueryObject qo = new ProductQueryObject();

	@Getter
	private Product product = new Product();
	@Setter
	private File pic;
	@Setter
	private String picFileName;

	@RequiredPermission("商品列表")
	public String execute() {
		try {
			List<Brand> brands = brandService.list();
			ServletActionContext.getContext().put("brands", brands);
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return LIST;
	}

	//供采购订单选择货品时使用
	public String selectProductList() {
		try {
			List<Brand> brands = brandService.list();
			ServletActionContext.getContext().put("brands", brands);
			ActionContext.getContext().put("result", service.pageQuery(qo));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("查询失败");
		}
		return "selectProductList";
	}

	@RequiredPermission("商品编辑")
	public String input() {
		try {
			if (product.getId() != null) {
				System.out.println(product.getId() + "=====================");
				product = service.get(product.getId());
			}
			List<Brand> brands = brandService.list();
			//put("brands", brands);
			ServletActionContext.getContext().put("brands", brands);
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("跳转失败");
		}
		return INPUT;
	}

	@RequiredPermission("商品保存/更新")
	public String saveOrUpdate() {
		try {
			if (pic != null && StringUtils.hasLength(product.getImagePath())) {//product.getImagePath()
				FileUploadUtil.deleteFile(product.getImagePath());//??
			}
			if (pic != null) {
				String imagePath = FileUploadUtil.uploadFile(pic, picFileName);
				product.setImagePath(imagePath);
			}
			if (product.getId() == null) {
				service.save(product);
				addActionMessage("保存成功");
			} else {
				service.update(product);
				addActionMessage("更新成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError("保存或更新失败");
		}
		return SUCCESS;
	}

	@RequiredPermission("商品删除")
	public String delete() throws Exception {
		try {
			if (StringUtils.hasLength(product.getImagePath())) {
				FileUploadUtil.deleteFile(product.getImagePath());//删除空字符串会报越界?
			}
			if (product.getId() != null) {
				service.delete(product.getId());
				putMsg("删除成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			putMsg("删除失败");
		}
		return NONE;
	}

}
