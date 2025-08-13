package com.rubypaper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubypaper.domain.BoardVO;

//@WebMvcTest
//@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BoardControllerTest {
	
//	@Autowired
//	private MockMvc mockMvc; //모형 컨테이너 사용
	
	@Autowired
	private TestRestTemplate restTemplate; //실제 톰캣
	
	@Test //실제 톰캣
	public void testHello() {
		String result = restTemplate.getForObject("/hello?name=둘리", String.class);
		assertEquals("hello : 둘리", result);
	}
	
	@Test //실제 톰캣
	public void testHello2() {
		BoardVO board = restTemplate.getForObject("/getBoard", BoardVO.class);
		assertEquals("테스터", board.getWriter());
	}
	
	
//	@Test //목업용
//	public void testHello() throws Exception{
//		mockMvc.perform(get("/hello").param("name","둘리"))
//		.andExpect(status().isOk())
//		.andExpect(content().string("hello : 둘리"))
//		.andDo(print());
//	}
//	
//	@Test //목업용
//	public void testHelloJson2() throws Exception{
//		ObjectMapper objectMapper = new ObjectMapper();
//		MvcResult mvcResult = mockMvc.perform(get("/getBoard").param("seq","100"))
//										.andExpect(status().isOk())
//										.andExpect(content().contentType(MediaType.APPLICATION_JSON))
//										.andExpect(jsonPath("$.writer").value("테스터"))
//										.andDo(print())
//										.andReturn();
//		String jsonString = mvcResult.getResponse().getContentAsString();
//		BoardVO board = objectMapper.readValue(jsonString, BoardVO.class);
//		assertEquals(board.getSeq(), 100);
//	}
}
