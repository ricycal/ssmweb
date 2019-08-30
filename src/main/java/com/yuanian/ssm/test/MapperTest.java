package com.yuanian.ssm.test;

import com.yuanian.ssm.bean.Department;
import com.yuanian.ssm.bean.Employee;
import com.yuanian.ssm.dao.DepartmentMapper;
import com.yuanian.ssm.dao.EmployeeMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class MapperTest {
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	EmployeeMapper employeeMapper;
	@Autowired
	SqlSession sqlSession;

	@Test
	public  void testCRUD(){
//		ApplicationContext ioc = new ClassPathXmlApplicationContext("application");
//		DepartmentMapper bean = ioc.getBean(DepartmentMapper.class)
		System.out.println(departmentMapper);
		employeeMapper.insertSelective(new Employee(null,"cmy","F","cmy@yuanian.com",1));

		EmployeeMapper mapper = sqlSession.getMapper(EmployeeMapper.class);
		for(int i=0;i<1;i++){
			String uid = UUID.randomUUID().toString().substring(0,5)+i;
			mapper.insertSelective(new Employee(null,uid, "F", uid+"@yuanian.com", 1));
		}
		System.out.println("批量完成");

		Employee employee=new Employee();

		employee=employeeMapper.selectByPrimaryKeyWithDept(1);
		System.out.println(employee.getDepartment().getDeptName());
		}

//		@Test
//		public void test(){
//		Employee employee=new Employee();
//
//			employee=employeeMapper.selectByPrimaryKeyWithDept(1);
//			System.out.println(employee);
//
//		}

}
