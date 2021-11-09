package com.dailycodebuffer.department;

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

	@Test
	public void testPostDepartment() throws IOException {
		HttpPost postRequest = new HttpPost(cloudGatewayURL + "/departments/");
		String departmentJson = "{\"departmentName\": \"Test\",\"departmentAddress\": \"TestAddress\", \"departmentCode\": \"TestCode\"}";
		StringEntity departmentEntity = new StringEntity(departmentJson, ContentType.APPLICATION_JSON);
		postRequest.setEntity(departmentEntity);
		HttpResponse httpPostResponse = HttpClientBuilder.create().build().execute(postRequest);
		assertThat(httpPostResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	public void testGetDepartment() throws IOException {
		int departmentId = 1;
		HttpGet getRequest = new HttpGet(cloudGatewayURL + "/departments/" + departmentId);
		HttpResponse httpGetResponse = HttpClientBuilder.create().build().execute(getRequest);
		assertThat(httpGetResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}
}
