package com.lionxxw.babasport.core.service.country.impl;

import com.lionxxw.babasport.core.dao.country.TownDao;
import com.lionxxw.babasport.core.dto.country.Town;
import com.lionxxw.babasport.core.query.country.TownQuery;
import com.lionxxw.babasport.core.service.country.TownService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 县/区
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class TownServiceImpl implements TownService {

	@Resource
	TownDao townDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addTown(Town town) {
		return townDao.addTown(town);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Town getTownByKey(Integer id) {
		return townDao.getTownByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Town> getTownsByKeys(List<Integer> idList) {
		return townDao.getTownsByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(Integer id) {
		return townDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<Integer> idList) {
		return townDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateTownByKey(Town town) {
		return townDao.updateTownByKey(town);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Town> getTownListWithPage(TownQuery townQuery) {
		int total = townDao.getTownListCount(townQuery);
		if (total > 0){
			List<Town> list = townDao.getTownListWithPage(townQuery);
			return new PageResult<Town>(total, townQuery.getPageSize(), list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Town> getTownList(TownQuery townQuery) {
		return townDao.getTownList(townQuery);
	}
}
