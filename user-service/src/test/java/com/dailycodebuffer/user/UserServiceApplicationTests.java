package com.dailycodebuffer.user;

import com.dailycodebuffer.user.VO.Department;
import com.dailycodebuffer.user.entity.User;
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
class UserServiceApplicationTests {
	String cloudGatewayURL = "http://localhost:9191";
	private final ObjectMapper mapper = new ObjectMapper();

	@Test
	public void testPostUser() throws IOException {
		HttpPost postRequest = new HttpPost(cloudGatewayURL + "/users/");
		User user = new User((long)1, "Test UserFirstName", "Test UserLastName", "testUserEmail@gmail.com", (long)1);
		StringEntity userEntity = new StringEntity(mapper.writeValueAsString(user), ContentType.APPLICATION_JSON);
		postRequest.setEntity(userEntity);
		HttpResponse httpPostResponse = HttpClientBuilder.create().build().execute(postRequest);
		assertThat(httpPostResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	public void testGetUser() throws IOException {
		HttpPost postDepartmentRequest = new HttpPost(cloudGatewayURL + "/departments/");
		Department department = new Department((long)3, "Test Department", "Test Department Address", "Test Department Code");
		StringEntity departmentEntity = new StringEntity(mapper.writeValueAsString(department), ContentType.APPLICATION_JSON);
		postDepartmentRequest.setEntity(departmentEntity);
		HttpResponse postDepartmentRequestResponse = HttpClientBuilder.create().build().execute(postDepartmentRequest);
		assertThat(postDepartmentRequestResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);

		HttpPost postUserRequest = new HttpPost(cloudGatewayURL + "/users/");
		User user = new User((long)2, "Test UserFirstName", "Test UserLastName", "testUserEmail@gmail.com", (long)3);
		StringEntity userEntity = new StringEntity(mapper.writeValueAsString(user), ContentType.APPLICATION_JSON);
		postUserRequest.setEntity(userEntity);
		HttpResponse postUserRequestResponse = HttpClientBuilder.create().build().execute(postUserRequest);
		assertThat(postUserRequestResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);

		long userId = 2;
		HttpGet getUserRequest = new HttpGet(cloudGatewayURL + "/users/" + userId);
		HttpResponse getUserRequestResponse = HttpClientBuilder.create().build().execute(getUserRequest);
		assertThat(getUserRequestResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}
}
