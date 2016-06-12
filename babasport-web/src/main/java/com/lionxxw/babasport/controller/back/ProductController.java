package com.lionxxw.babasport.controller.back;

import com.lionxxw.babasport.core.service.product.*;
import com.lionxxw.babasport.core.service.product.impl.*;
import com.lionxxw.babasport.staticpage.StaticPageService;
import com.lionxxw.common.constants.DataStatus;
import com.lionxxw.common.model.PageQuery;
import com.lionxxw.common.model.PageResult;
import com.lionxxw.common.model.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;


/**
 * <p>Description: 后台商品管理 </p>
 *
 * @author wangxiang
 * @date 16/5/31 下午4:35
 * @version 1.0
 */
@Controller
public class ProductController extends BaseBackController{
	
	@Autowired
	private BrandService brandService;
	@Autowired
	private ProductService productService;
	@Autowired
	private ColorService colorService;
	@Autowired
	private SizeService sizeService;
	@Autowired
	private ProductTypeService typeService;
	@Autowired
	private MaterialService materialService;
	@Autowired
	private StaticPageService staticPageService;
	@Autowired
	private SkuService skuService;
	@Autowired
	private ProductImageService imageService;

	// 商品列表
	@RequestMapping(value = "/product/list.do")
	public ModelAndView list(ProductDto params, PageQuery query) throws Exception{
		ModelAndView mv = new ModelAndView();
		List<BrandDto> brands = brandService.queryByParam(null);
		PageResult<ProductDto> products = productService.queryByPage(params, query);

		mv.addObject("brands", brands);
		mv.addObject("products", products);
		mv.addObject("params", params);
		mv.setViewName("product/list");
		return mv;
	}

	// 商品添加跳转页
	@RequestMapping(value = "/product/toAdd.do")
	public ModelAndView toAdd() throws Exception{
		ModelAndView mv = new ModelAndView();
		productParamsInit(mv);
		mv.setViewName("product/add");
		return mv;
	}

	// 商品新增
	@RequestMapping(value = "/product/add.do")
	public String add(ProductDto product, ProductImageDto image) throws Exception{
		product.setImage(image);
		productService.save(product);
		return "redirect:/back/product/list.do";
	}

	// 商品上架
	@RequestMapping(value = "/product/isShow.do")
	@ResponseBody
	public Response<String> isShow(Integer[] ids){
		Response<String> response = new Response<String>();
		if (null != ids && ids.length > 0){
			String msg = "";
			if (ids.length > 1){
				msg = "批量";
			}
			ProductDto productDto = new ProductDto();
			productDto.setIsShow(true);
			try {
				for (Integer id : ids){
					productDto.setId(id);
					productService.update(productDto); // 修改上架状态
					// 静态化
					toStaticPage(id);
				}
				response.setMessage(msg + "上架成功!");
			}catch (Exception e){
				e.printStackTrace();
				response.setMessage(msg +"上架失败!");
				response.setStatus(DataStatus.HTTP_FAILE);
			}
		}else{
			response.setMessage("未选中上架商品!");
			response.setStatus(DataStatus.HTTP_FAILE);
		}
		return response;
	}

	/**		
	 * <p>Description: 静态化页面 </p>
	 * 
	 * @param id 商品id
	 * @author wangxiang
	 * @date 16/6/6 下午8:16
	 * @version 1.0
	 */
	private void toStaticPage(Integer id) throws Exception{
		Map<String, Object> root = new HashMap<String, Object>();
		ProductDto product = productService.getById(id);
		root.put("images", getProductImageDtos(product));
		//2:加载sku
		//3:此商品  库存大于0的
		List<SkuDto> skus = skuService.queryInventory(id);
		if(null != skus && skus.size() > 0){
			//4:不一样的颜色集合(去重)
			root.put("colors", getColors(skus));
			//5:不一样的尺寸集合(去重)
			root.put("sizes", getSizes(skus));
			root.put("skus", skus);
		}
		root.put("product", product);
		staticPageService.productIndex(root, id);
	}

	/**		
	 * <p>Description: 新增商品时,需要加载的配置 </p>
	 * 
	 * @param mv
	 * @author wangxiang
	 * @date 16/6/2 上午10:56
	 * @version 1.0
	 */
	private void productParamsInit(ModelAndView mv) throws Exception{
		List<BrandDto> brands = brandService.queryByParam(null);
		mv.addObject("brands", brands);
		List<ColorDto> colors = colorService.queryByParam(null);
		mv.addObject("colors", colors);
		List<SizeDto> sizes = sizeService.queryByParam(null);
		mv.addObject("sizes", sizes);
		List<ProductTypeDto> types = typeService.queryByParam(null);
		mv.addObject("types", types);
		List<MaterialDto> materials = materialService.queryByParam(null);
		mv.addObject("materials", materials);
	}

	private List<ProductImageDto> getProductImageDtos(ProductDto product) throws Exception {
		ProductImageDto params = new ProductImageDto();
		params.setProductId(product.getId());
		List<ProductImageDto> productImageDtos = imageService.queryByParam(params);
		if (null != productImageDtos && productImageDtos.size() > 0){
			product.setImage(productImageDtos.get(0));
		}
		return productImageDtos;
	}

	private List<ColorDto> getColors(List<SkuDto> skus) {
		List<Integer> colorIds = new ArrayList<Integer>();
		List<ColorDto> colors = new ArrayList<ColorDto>();
		ColorDto color;
		Properties cp = colorProperties();
		for(SkuDto sku : skus){
			if(!colorIds.contains(sku.getColorId())){
				colorIds.add(sku.getColorId());
				color = new ColorDto();
				color.setId(sku.getColorId());
				color.setName(cp.getProperty(sku.getColorId() + ""));
				colors.add(color);
			}
		}
		return colors;
	}

	private List<SizeDto> getSizes(List<SkuDto> skus) {
		List<Integer> sizeIds = new ArrayList<Integer>();
		List<SizeDto> sizes = new ArrayList<SizeDto>();
		SizeDto size;
		Properties sp = sizeProperties();
		for(SkuDto sku : skus){
			if(!sizeIds.contains(sku.getSizeId())){
				sizeIds.add(sku.getSizeId());
				size = new SizeDto();
				size.setId(sku.getSizeId());
				size.setName(sp.getProperty(sku.getSizeId() + ""));
				sizes.add(size);
			}
		}
		return sizes;
	}
}