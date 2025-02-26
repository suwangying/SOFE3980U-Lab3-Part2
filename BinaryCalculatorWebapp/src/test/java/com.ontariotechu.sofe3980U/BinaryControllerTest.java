package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.junit.runner.RunWith;

import org.junit.*;
import org.junit.runner.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.boot.test.mock.mockito.*;
import org.springframework.test.context.junit4.*;

import static org.hamcrest.Matchers.containsString;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.BDDMockito.*;

@RunWith(SpringRunner.class)
@WebMvcTest(BinaryController.class)
public class BinaryControllerTest {

    @Autowired
    private MockMvc mvc;

   
    @Test
    public void getDefault() throws Exception {
        this.mvc.perform(get("/"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", ""))
			.andExpect(model().attribute("operand1Focused", false));
    }
	
	    @Test
    public void getParameter() throws Exception {
        this.mvc.perform(get("/").param("operand1","111"))
            .andExpect(status().isOk())
            .andExpect(view().name("calculator"))
			.andExpect(model().attribute("operand1", "111"))
			.andExpect(model().attribute("operand1Focused", true));
    }
	@Test
	    public void postParameter() throws Exception {
        this.mvc.perform(post("/").param("operand1","111").param("operator","+").param("operand2","111"))//.andDo(print())
            .andExpect(status().isOk())
            .andExpect(view().name("result"))
			.andExpect(model().attribute("result", "1110"))
			.andExpect(model().attribute("operand1", "111"));
    }
    @Test
public void postMultiplyParameter() throws Exception {
    this.mvc.perform(post("/").param("operand1", "101").param("operator", "*").param("operand2", "10")) // 5 * 2 = 10 (1010 in binary)
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1010")) 
        .andExpect(model().attribute("operand1", "101"));
}
@Test
public void postAndParameter() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1101").param("operator", "&").param("operand2", "1011")) // 1101 & 1011 = 1001
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1001")) 
        .andExpect(model().attribute("operand1", "1101"));
}
@Test
public void postOrParameter() throws Exception {
    this.mvc.perform(post("/").param("operand1", "1100").param("operator", "|").param("operand2", "1010")) // 1100 | 1010 = 1110
        .andExpect(status().isOk())
        .andExpect(view().name("result"))
        .andExpect(model().attribute("result", "1110")) 
        .andExpect(model().attribute("operand1", "1100"));
}

}
