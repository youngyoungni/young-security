package com.young.web.controller;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author Youngni
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserControllerTest {

    @Autowired
    private WebApplicationContext mac;
    private MockMvc mockMvc;

    @Before
    public void setUp(){
        mockMvc = MockMvcBuilders.webAppContextSetup(mac).build();
    }

    @Test
    public void whenQuery() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user")
                //加参数
                .param("username","paramUsername")
                .param("age","18")
                .param("ageTo","60")    //UserQueryCondition
//                .param("size","15")
//                .param("page","3")
//                .param("sort","age,desc")   //分页
                //返回类型：json utf-8
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                //请求成功
                .andExpect(MockMvcResultMatchers.status().isOk())
                //返回数据长度： 3
                .andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(3))
                //获取返回数据
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetUserInfo() throws Exception {
        String result = mockMvc.perform(MockMvcRequestBuilders.get("/user/1")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username").value("tom"))
                .andReturn().getResponse().getContentAsString();
        System.out.println(result);
    }

    @Test
    public void whenGetUserInfoFail() throws Exception {    //测试 id 为 字符串
        mockMvc.perform(MockMvcRequestBuilders.get("/user/a")
                .contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }


}
