package org.liu.se418.Actuator;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liu.se418.user.message.request.LogInForm;
import org.liu.se418.user.message.request.SignUpForm;
import org.liu.se418.user.security.jwt.JwtAuthEntryPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.Map;
import java.util.Set;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ActuatorTest {


    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;


    private String token;

    @Before
    @Transactional
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        SignUpForm signUpForm = new SignUpForm("liu", "testActuator", "testActuator@foxmail.com", Set.of("admin"), "password");
        ObjectMapper mapper1 = new ObjectMapper();
        String jsonString = mapper1.writeValueAsString(signUpForm);
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/signup")
                        .content(jsonString)
                        .header("Content-Type", "application/json")
        ).andReturn();

        LogInForm logInForm = new LogInForm("test", "password");
        ObjectMapper mapper2 = new ObjectMapper();
        jsonString = mapper2.writeValueAsString(logInForm);
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/api/auth/signin")
                        .content(jsonString)
                        .header("Content-Type", "application/json")
        ).andReturn();

        String responseString = mvcResult.getResponse().getContentAsString();
        Map<String, String> responseMap = mapper2.readValue(responseString, Map.class);
        token = responseMap.get("accessToken");
    }


    @Test
    public void testHealth() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/actuator/health")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk()).andReturn();

        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/actuator/health")
        ).andExpect(status().isUnauthorized()).andReturn();
    }


    @Test
    public void testInfo() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/actuator/info")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk()).andReturn();
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/actuator/info")
        ).andExpect(status().isUnauthorized()).andReturn();
    }


    @Test
    public void testBeans() throws Exception {
        MvcResult result = mockMvc.perform(
                MockMvcRequestBuilders.get("/actuator/beans")
                        .header("Content-Type", "application/json")
                        .header("Authorization", "Bearer " + token)
        ).andExpect(status().isOk()).andReturn();
        mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/actuator/beans")
        ).andExpect(status().isUnauthorized()).andReturn();

    }
}