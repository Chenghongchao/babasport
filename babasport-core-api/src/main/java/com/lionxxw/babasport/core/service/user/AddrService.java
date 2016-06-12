package com.lionxxw.babasport.core.service.user;

import com.lionxxw.babasport.core.dto.user.Addr;
import com.lionxxw.babasport.core.query.user.AddrQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;


/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
/**
 * <p>Description:  </p>
 *
 * @author wangxiang
 * @date 16/6/12 下午3:23
 * @version 1.0
 */
public interface AddrService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addAddr(Addr addr);

	/**
	 * 根据主键查询
	 */
	public Addr getAddrByKey(Integer id);

	/**
	 * 根据主键批量查询
	 */
	List<Addr> getAddrsByKeys(List<Integer> idList);

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
	Integer updateAddrByKey(Addr addr);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param addrQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Addr> getAddrListWithPage(AddrQuery addrQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param addrQuery
	 *            查询条件
	 * @return
	 */
	List<Addr> getAddrList(AddrQuery addrQuery);
}
