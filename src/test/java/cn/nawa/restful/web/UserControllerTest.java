package cn.nawa.restful.web;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Autowired
    private MockMvc mvc;

    @Test
    public void getUserById() throws Exception{
        MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get("/v1/user/get/1")).andReturn();
        int status = mvcResult.getResponse().getStatus();
        String responseString = mvcResult.getResponse().getContentAsString();
        System.out.print("ret:" + responseString);
        Assert.assertEquals("req error!", 200, status);
    }

    @Test
    public void getUserList() {
    }

    @Test
    public void add() {
    }

    @Test
    public void delete() {
    }

    @Test
    public void update() {
    }
}