package com.lionxxw.babasport.core.service.user.impl;

import com.lionxxw.babasport.core.dao.user.AddrDao;
import com.lionxxw.babasport.core.dto.user.Addr;
import com.lionxxw.babasport.core.query.user.AddrQuery;
import com.lionxxw.babasport.core.service.user.AddrService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 地址
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class AddrServiceImpl implements AddrService {

	@Resource
	AddrDao addrDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addAddr(Addr addr) {
		return addrDao.addAddr(addr);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Addr getAddrByKey(Integer id) {
		return addrDao.getAddrByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Addr> getAddrsByKeys(List<Integer> idList) {
		return addrDao.getAddrsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return addrDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return addrDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateAddrByKey(Addr addr) {
		return addrDao.updateAddrByKey(addr);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Addr> getAddrListWithPage(AddrQuery addrQuery) {
		int total = addrDao.getAddrListCount(addrQuery);
		if (total > 0){
			List<Addr> list = addrDao.getAddrListWithPage(addrQuery);
			return new PageResult<Addr>(total, addrQuery.getPageSize(),list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Addr> getAddrList(AddrQuery addrQuery) {
		return addrDao.getAddrList(addrQuery);
	}
}
