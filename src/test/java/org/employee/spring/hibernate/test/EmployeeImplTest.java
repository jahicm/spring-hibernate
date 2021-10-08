package org.employee.spring.hibernate.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.employee.hibernate.config.ApplicationConfig;
import org.employee.hibernate.models.Employee;
import org.employee.spring.hibernate.dao.IGenericDao;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class EmployeeImplTest {

	static IGenericDao<Employee> employee;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {

		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		employee = (IGenericDao<Employee>) ctx.getBean("createEmployee");

		SimpleDateFormat ft = new SimpleDateFormat("dd.mm.yyyy");
		Date date = ft.parse("05.05.1972");
		Employee employee1 = new Employee();
		employee1.setAddress("MyAddress 1");
		employee1.setDob(date);
		employee1.setName("John Mayer");
		employee1.setSalary(134967.99);
		employee1.setSex("M");
		employee1.setSupervisor("Migguel Sassi");
		employee1.setWeeklyHours(45);

		employee.create(employee1);
	}

	@Test
	public void test1() {

		Employee emp = employee.findOne(1L);
		System.out.println(emp.toString());

		assertNotNull(emp);
	}

	@Test
	public void test2() {

		Employee emp = employee.findOne(1L);
		emp.setName("Updated");
		employee.update(emp);

		Employee updatedEmp = employee.findOne(1L);
		assertEquals("Updated", updatedEmp.getName());
	}

	@Test
	public void test3() {
		Employee emp = employee.findOne(1L);
		employee.delete(emp);

		Employee deletedEmp = employee.findOne(1L);
		assertNull(deletedEmp);

	}

}
