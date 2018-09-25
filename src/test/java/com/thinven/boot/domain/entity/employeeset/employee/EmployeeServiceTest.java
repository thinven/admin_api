package com.thinven.boot.domain.entity.employeeset.employee;

import javax.transaction.Transactional;

//import org.hamcrest.CoreMatchers;
import org.junit.Assert;
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
	public void test() {
		Message<Employee> msg = new Message<Employee>();
		Employee params = new Employee();
		params.setUid(ParamUtil.getUUID());
		params.setId("employeeAuthId");
		params.setFirstname("employeeFirstname");
		params.setLastname("employeeLastname");
		params.setBirthday("employeeBirthday");
		params.setGender(10L);
		params.setPhone("employeePhone");
		params.setEmail("employeeEmail");
		params.setDelete(20L);

		// 등록 테스트.
		msg.setParams(params);
		msg = employeeService.add(msg);
		Assert.assertTrue(msg.getDesc(), msg.isOk());
		// Employee info = (Employee) msg.get("employee");
		// Assert.assertThat(info.getId() + " != " + msg.getParams().getId(),
		// info.getId(), CoreMatchers.is(msg.getParams().getId()));
	}
}
