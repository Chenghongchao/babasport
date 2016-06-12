package com.lionxxw.babasport.core.service.product;

import com.lionxxw.babasport.core.dto.product.Img;
import com.lionxxw.babasport.core.query.product.ImgQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;


/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface ImgService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addImg(Img img);

	/**
	 * 根据主键查询
	 */
	Img getImgByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Img> getImgsByKeys(List<Integer> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(Integer id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<Integer> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateImgByKey(Img img);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param imgQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Img> getImgListWithPage(ImgQuery imgQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param imgQuery
	 *            查询条件
	 * @return
	 */
	List<Img> getImgList(ImgQuery imgQuery);
}
