package com.ontariotechu.sofe3980U;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
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
@WebMvcTest(BinaryAPIController.class)
public class BinaryAPIControllerTest {

    @Autowired
    private MockMvc mvc;

    // Test for "/add" (string result)
    @Test
    public void add() throws Exception {
        this.mvc.perform(get("/add").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(content().string("10001"));  // 7 + 10 = 17, binary result is 10001
    }

    // Test for "/add_json" (JSON result)
    @Test
    public void addJson() throws Exception {
        this.mvc.perform(get("/add_json").param("operand1", "111").param("operand2", "1010"))
            .andExpect(status().isOk())
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand1").value(111))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operand2").value(1010))
            .andExpect(MockMvcResultMatchers.jsonPath("$.result").value("10001"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.operator").value("add"));
    }

    // Test for "/multiply" (string result)
    @Test
    public void multiply() throws Exception {
        this.mvc.perform(get("/multiply").param("operand1", "101").param("operand2", "10"))  // 5 * 2 = 10
            .andExpect(status().isOk())
            .andExpect(content().string("1010"));  // Expected binary result (10 in decimal)
    }

    // Test for "/and" (string result)
    @Test
    public void and() throws Exception {
        this.mvc.perform(get("/and").param("operand1", "1101").param("operand2", "1011"))  // 13 & 11 = 9
            .andExpect(status().isOk())
            .andExpect(content().string("1001"));  // Expected binary result (9 in decimal)
    }

    // Test for "/or" (string result)
    @Test
    public void or() throws Exception {
        this.mvc.perform(get("/or").param("operand1", "1100").param("operand2", "1010"))  // 12 | 10 = 14
            .andExpect(status().isOk())
            .andExpect(content().string("1110"));  // Expected binary result (14 in decimal)
    }
}
