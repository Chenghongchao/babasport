package com.lionxxw.babasport.core.service.user.impl;

import com.lionxxw.babasport.core.dao.user.EmployeeDao;
import com.lionxxw.babasport.core.dto.user.Employee;
import com.lionxxw.babasport.core.query.user.EmployeeQuery;
import com.lionxxw.babasport.core.service.user.EmployeeService;
import com.lionxxw.common.model.PageResult;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 员工
 * @author lixu
 * @Date [2014-3-27 下午03:31:57]
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

	@Resource
	EmployeeDao employeeDao;

	/**
	 * 插入数据库
	 * 
	 * @return
	 */
	public Integer addEmployee(Employee employee) {
		return employeeDao.addEmployee(employee);
	}

	/**
	 * 根据主键查找
	 */
	@Transactional(readOnly = true)
	public Employee getEmployeeByKey(String id) {
		return employeeDao.getEmployeeByKey(id);
	}
	
	@Transactional(readOnly = true)
	public List<Employee> getEmployeesByKeys(List<String> idList) {
		return employeeDao.getEmployeesByKeys(idList);
	}

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	public Integer deleteByKey(String id) {
		return employeeDao.deleteByKey(id);
	}

	public Integer deleteByKeys(List<String> idList) {
		return employeeDao.deleteByKeys(idList);
	}

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	public Integer updateEmployeeByKey(Employee employee) {
		return employeeDao.updateEmployeeByKey(employee);
	}
	
	@Transactional(readOnly = true)
	public PageResult<Employee> getEmployeeListWithPage(EmployeeQuery employeeQuery) {
		int total = employeeDao.getEmployeeListCount(employeeQuery);
		if (total > 0){
			List<Employee> list = employeeDao.getEmployeeListWithPage(employeeQuery);
			return new PageResult<Employee>(total, employeeQuery.getPageSize(),list);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	public List<Employee> getEmployeeList(EmployeeQuery employeeQuery) {
		return employeeDao.getEmployeeList(employeeQuery);
	}
}
