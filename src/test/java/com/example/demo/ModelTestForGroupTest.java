package com.example.demo;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class ModelTestForGroupTest {

	private MockMvc mockMvc;

	@Autowired
	GroupController target;

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(target).build();
	}

	@MockBean
	private GroupService groupService;

	@Test
	public void isModelTest() throws Exception {
		// modelにセットされているかのテスト
		when(groupService.findByNo("10000")).thenReturn("SS1部");
		mockMvc.perform(post("/output_2?number=10000"))
				.andExpect(model().attribute("name", "SS1部"));
	}

}