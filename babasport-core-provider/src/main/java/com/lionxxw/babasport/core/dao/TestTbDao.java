package com.lionxxw.babasport.core.dao;

import com.lionxxw.babasport.core.entity.TestTb;
import com.lionxxw.babasport.core.mapper.TestTbMapper;
import com.lionxxw.common.base.MyBatisBaseDao;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class TestTbDao extends MyBatisBaseDao<TestTb> {

	@Autowired
	@Getter
	private TestTbMapper mapper;
}
