package com.lionxxw.babasport.core.service.impl;

import com.lionxxw.babasport.core.dao.TestTbDao;
import com.lionxxw.babasport.core.dto.TestTbDto;
import com.lionxxw.babasport.core.entity.TestTb;
import com.lionxxw.babasport.core.service.TestTbService;
import com.lionxxw.common.utils.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TestTbServiceImpl implements TestTbService {

	@Autowired
	private TestTbDao testTbDao;
	
	public void addTestTb(TestTbDto testTb) {
		TestTb obj = BeanUtils.createBeanByTarget(testTb, TestTb.class);
		testTbDao.insertSelective(obj);
		
		throw new RuntimeException();
	}
}
