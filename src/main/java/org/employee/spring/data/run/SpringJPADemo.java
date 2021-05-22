package org.employee.spring.data.run;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.employee.spring.data.config.ApplicationConfig;
import org.employee.spring.data.dao.DepartmentServiceImpl;
import org.employee.spring.data.dao.EmployeeServiceImpl;
import org.employee.spring.data.dao.ProjectServiceImpl;
import org.employee.spring.data.models.Department;
import org.employee.spring.data.models.Dependent;
import org.employee.spring.data.models.Employee;
import org.employee.spring.data.models.Project;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;

public class SpringJPADemo {

	public static void main(String... args) throws ParseException {

		GenericApplicationContext ctx = new AnnotationConfigApplicationContext(ApplicationConfig.class);
		EmployeeServiceImpl employee = (EmployeeServiceImpl) ctx.getBean("createEmployeeService");
		DepartmentServiceImpl dep = (DepartmentServiceImpl) ctx.getBean("createDepartmentService");
		ProjectServiceImpl pro = (ProjectServiceImpl) ctx.getBean("createProjectService");

		System.out.println("***********CREATE DATA START*****************");
		SimpleDateFormat ft = new SimpleDateFormat("dd.mm.yyyy");
		Date date = ft.parse("05.05.1972");

		Department department = new Department();
		Date startDate2 = ft.parse("01.06.2016");
		department.setDepartmentManager("Mirza Jahic");
		department.setDepartmentName("IT");
		department.setDepartmentLocation("Zurich");
		department.setManagerStartDate(startDate2);

		Department department2 = new Department();
		Date startDate = ft.parse("01.01.2020");
		department2.setDepartmentManager("Miguell Mons");
		department2.setDepartmentName("BSF");
		department2.setDepartmentLocation("London");
		department2.setManagerStartDate(startDate);

		Employee employee1 = new Employee();
		employee1.setAddress("MyAddress 1");
		employee1.setDepartment(department);
		employee1.setDob(date);
		employee1.setName("John Mayer");
		employee1.setSalary(134967.99);
		employee1.setSex("M");
		employee1.setSupervisor("Migguel Sassi");
		employee1.setWeeklyHours(45);

		Date date2 = ft.parse("03.08.1982");
		Employee employee2 = new Employee();
		employee2.setAddress("MyAddress 2");
		employee2.setDepartment(department);
		employee2.setDob(date2);
		employee2.setName("Old Man");
		employee2.setSalary(166967.99);
		employee2.setSex("M");
		employee2.setSupervisor("Albert Wall");
		employee2.setWeeklyHours(42);

		Date date3 = ft.parse("06.12.1967");
		Employee employee3 = new Employee();
		employee3.setAddress("MyAddress 3");
		employee3.setDepartment(department2);
		employee3.setDob(date3);
		employee3.setName("Carmen Imholz");
		employee3.setSalary(100000);
		employee3.setSex("F");
		employee3.setSupervisor("Massimo Dutti");
		employee3.setWeeklyHours(40);

		Set<Employee> empSet = new HashSet<>();
		empSet.add(employee1);
		empSet.add(employee2);

		Set<Employee> empSet2 = new HashSet<>();
		empSet2.add(employee3);

		dep.create(department2);
		dep.create(department);

		employee.create(employee1);
		employee.create(employee2);
		employee.create(employee3);

		department.setEmployees(empSet);
		department2.setEmployees(empSet2);

		/*
		 * Set<Dependent> depSet1 = new HashSet<>(); Dependent dependent1 = new
		 * Dependent(); Date date4 = ft.parse("06.02.2001"); dependent1.setDob(date4);
		 * dependent1.setEmployee(employee1); dependent1.setName("Mary Collins");
		 * dependent1.setRelationship("Wife"); dependent1.setSex("F");
		 * depSet1.add(dependent1); employee1.setDependents(depSet1);
		 * 
		 * Set<Dependent> depSet2 = new HashSet<>(); Dependent dependent2 = new
		 * Dependent(); Date date5 = ft.parse("04.11.1989"); dependent2.setDob(date5);
		 * dependent2.setEmployee(employee2); dependent2.setName("Bob Max");
		 * dependent2.setRelationship("Son"); dependent2.setSex("M");
		 * depSet2.add(dependent2); employee2.setDependents(depSet2);
		 * 
		 * Set<Dependent> depSet3 = new HashSet<>(); Dependent dependent3 = new
		 * Dependent(); Date date6 = ft.parse("06.07.1999"); dependent3.setDob(date6);
		 * dependent3.setEmployee(employee3); dependent3.setName("Mandy Hills");
		 * dependent3.setRelationship("Sister"); dependent3.setSex("F");
		 * depSet3.add(dependent3); employee3.setDependents(depSet3);
		 * 
		 */

		Project project1 = new Project();
		project1.setProjectLocation("Zurich");
		project1.setProjectName("CCBE");
		Set<Employee> employees = new HashSet<>();

		employees.add(employee1);
		employees.add(employee3);

		project1.setEmployee(employees);

		Project project2 = new Project();
		project2.setProjectLocation("Zurich");
		project2.setProjectName("XPP");
		Set<Employee> employees2 = new HashSet<>();
		employees2.add(employee2);
		project2.setEmployee(employees2);

		Set<Project> projects = new HashSet<>();
		projects.add(project1);

		Set<Project> projects2 = new HashSet<>();
		projects2.add(project2);

		pro.create(project1);
		pro.create(project2);

		department.setProjects(projects);
		department2.setProjects(projects2);

		System.out.println("***********CREATE DATA END*****************");

		List<Project> listPro = pro.findAll();
		System.out.println("***********pro.findAll()*****************");
		for (Project p : listPro) {
			System.out.println(p.toString());
		}
		List<Employee> listEmp = employee.findAll();
		System.out.println("***********employee.findAll()*****************");
		for (Employee e : listEmp) {
			System.out.println(e.toString());
			Set<Dependent> listDep = e.getDependents();
			System.out.println("***********Dependents*****************");
			for (Dependent d : listDep) {
				System.out.println(d.toString());

			}
			System.out.println("************************************");
		}

		ctx.close();
	}
}