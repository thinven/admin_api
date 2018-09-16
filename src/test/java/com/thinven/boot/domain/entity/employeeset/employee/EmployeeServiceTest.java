package com.thinven.boot.domain.entity.employeeset.employee;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.thinven.boot.domain.entity.employeeset.employee.service.EmployeeService;
import com.thinven.boot.support.domain.entity.model.Message;
import com.thinven.boot.support.util.ParamUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class EmployeeServiceTest {
	@Autowired
	EmployeeService employeeService;

	@Test
	public void add() {
		Message<Employee> msg = new Message<Employee>();
		for (int i = 0; i < 10; i++) {
			Employee employee = new Employee();
			employee.setUid(ParamUtil.getUUID());
			employee.setFirstname("first name" + i);
			employee.setLastname("last name" + i);
			employee.setBirthday("1974-02-16");
			employee.setGender(10L);
			employee.setPhone("010-2209-8121");
			employee.setEmail("hihi@" + i);

			msg.setParams(employee);
			msg = employeeService.add(msg);
		}
	}
}
