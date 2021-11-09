package com.dailycodebuffer.department;

import com.dailycodebuffer.department.entity.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DepartmentServiceApplicationTests {
	String cloudGatewayURL = "http://localhost:9191";
	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testPostDepartment() throws IOException {
		HttpPost postRequest = new HttpPost(cloudGatewayURL + "/departments/");
		Department department = new Department((long)1, "Test Department", "Test Department Address", "Test Department Code");
		StringEntity departmentEntity = new StringEntity(mapper.writeValueAsString(department), ContentType.APPLICATION_JSON);
		postRequest.setEntity(departmentEntity);
		HttpResponse httpPostResponse = HttpClientBuilder.create().build().execute(postRequest);
		assertThat(httpPostResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
		Department postDepartmentResponse = TestUtil.retrieveResourceFromResponse(httpPostResponse, Department.class);
		assertThat(postDepartmentResponse.getDepartmentName()).isEqualTo("Test Department");
	}

	@Test
	public void testGetDepartment() throws IOException {
		HttpPost postRequest = new HttpPost(cloudGatewayURL + "/departments/");
		Department department = new Department("Test Department", "Test Department Address", "Test Department Code");
		StringEntity departmentEntity = new StringEntity(mapper.writeValueAsString(department), ContentType.APPLICATION_JSON);
		postRequest.setEntity(departmentEntity);
		HttpResponse postDepartmentRequestResponse = HttpClientBuilder.create().build().execute(postRequest);
		assertThat(postDepartmentRequestResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);

		Department postDepartmentResponse = TestUtil.retrieveResourceFromResponse(postDepartmentRequestResponse, Department.class);
		long departmentId = postDepartmentResponse.getDepartmentId();

		HttpGet getRequest = new HttpGet(cloudGatewayURL + "/departments/" + departmentId);
		HttpResponse getDepartmentRequestResponse = HttpClientBuilder.create().build().execute(getRequest);
		assertThat(getDepartmentRequestResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
		Department departmentResponse = TestUtil.retrieveResourceFromResponse(getDepartmentRequestResponse, Department.class);
		assertThat(departmentResponse.getDepartmentId()).isEqualTo(departmentId);
	}
}
