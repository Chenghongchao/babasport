package com.lionxxw.babasport.core.service.user;

import com.lionxxw.babasport.core.dto.user.Employee;
import com.lionxxw.babasport.core.query.user.EmployeeQuery;
import com.lionxxw.common.model.PageResult;

import java.util.List;

/**
 * 
 * @author lixu
 * @Date [2014-3-28 下午01:50:28]
 */
public interface EmployeeService {
	/**
	 * 基本插入
	 * 
	 * @return
	 */
	Integer addEmployee(Employee employee);

	/**
	 * 根据主键查询
	 */
	Employee getEmployeeByKey(String id);

	/**
	 * 根据主键批量查询
	 */
	List<Employee> getEmployeesByKeys(List<String> idList);

	/**
	 * 根据主键删除
	 * 
	 * @return
	 */
	Integer deleteByKey(String id);

	/**
	 * 根据主键批量删除
	 * 
	 * @return
	 */
	Integer deleteByKeys(List<String> idList);

	/**
	 * 根据主键更新
	 * 
	 * @return
	 */
	Integer updateEmployeeByKey(Employee employee);

	/**
	 * 根据条件查询分页查询
	 * 
	 * @param employeeQuery
	 *            查询条件
	 * @return
	 */
	PageResult<Employee> getEmployeeListWithPage(EmployeeQuery employeeQuery);

	/**
	 * 根据条件查询
	 * 
	 * @param employeeQuery
	 *            查询条件
	 * @return
	 */
	List<Employee> getEmployeeList(EmployeeQuery employeeQuery);
}
