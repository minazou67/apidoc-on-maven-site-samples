package org.minazou67.samples.apidoc.controller;

import javax.validation.Valid;

import org.minazou67.samples.apidoc.model.Employee;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping(value = "/employee", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
public class EmployeeController {

	@ApiOperation(value = "Add Employee")
	@RequestMapping(method = RequestMethod.POST)
	public Employee addEmployee(@RequestBody final Employee employee) {
		return employee;
	}

	@ApiOperation(value = "Get Employee")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Employee getEmployee(@PathVariable("id") final int id) {
		Employee employee = new Employee(id, "Foo");
		return employee;
	}

	@ApiOperation(value = "Update Employee")
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable("id") final int id, @Valid @RequestBody final Employee employee) {
		return employee;
	}

	@ApiOperation(value = "Delete Employee")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Employee deleteEmployee(@PathVariable("id") final int id) {
		Employee employee = new Employee(id, "Foo");
		return employee;
	}
}
