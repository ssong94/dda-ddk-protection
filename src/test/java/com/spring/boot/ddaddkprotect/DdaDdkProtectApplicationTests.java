package com.spring.boot.ddaddkprotect;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class DdaDdkProtectApplicationTests {

	@Test
	void contextLoads() {
	}

	@Autowired
	private MockMvc mockMvc;

	@Test
	@DisplayName("더블 클릭 방지 테스트")
	void doubleClickRequestTest() throws Exception {

		// given
		String json = "{\"name\":\"Alice\",\"email\":\"alice@example.com\"}";

		// Act



		// then



	}

}
