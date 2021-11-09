package com.dailycodebuffer.user;

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

	@Test
	public void testPostUser() throws IOException {
		HttpPost postRequest = new HttpPost(cloudGatewayURL + "/users/");
		String userJson = "{\"firstName\": \"TestUserName\",\"lastName\": \"TestUserLastName\",\"email\": \"TestUserEmail\",\"departmentId\": 1}";
		StringEntity userEntity = new StringEntity(userJson, ContentType.APPLICATION_JSON);
		postRequest.setEntity(userEntity);
		HttpResponse httpPostResponse = HttpClientBuilder.create().build().execute(postRequest);
		assertThat(httpPostResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	public void testGetUser() throws IOException {
		int userId = 1;
		HttpGet getUserRequest = new HttpGet(cloudGatewayURL + "/users/" + userId);
		HttpResponse httpGetResponse = HttpClientBuilder.create().build().execute(getUserRequest);
		assertThat(httpGetResponse.getStatusLine().getStatusCode()).isEqualTo(HttpStatus.SC_OK);
	}
}
