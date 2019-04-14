package org.liu.se418.wordLadder;


import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.liu.se418.user.message.request.LogInForm;
import org.liu.se418.user.message.request.SignUpForm;
import org.liu.se418.user.security.jwt.JwtAuthEntryPoint;
import org.liu.se418.user.security.jwt.JwtProvider;
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

import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class WordLadderControllerTest {
    private static final Logger logger = LoggerFactory.getLogger(JwtAuthEntryPoint.class);
    private MockMvc mockMvc;


    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private JwtProvider jwtProvider;

    private String token;

    @Before
    @Transactional
    public void setup() throws Exception {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(webApplicationContext)
                .apply(springSecurity())
                .build();

        SignUpForm signUpForm = new SignUpForm("liu", "test", "test@foxmail.com", Set.of("admin"), "password");
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
    public void loginTest() throws Exception {
        String username = jwtProvider.getUserNameFromJwtToken(token);
        Assert.assertTrue("username should be the same", username.equals("test"));
    }

//    @Test
//    public void testGetWordLadderPostWithToken() throws Exception {
//        JSONObject words = new JSONObject();
//        words.put("source","cat");
//        words.put("destination", "dog");
//        MvcResult mvcResult = mockMvc.perform(
//                MockMvcRequestBuilders.post("/api/wordLadder")
//                        .content(words.toString())
//                        .header("Authorization","Bearer " + token)
//        ).andReturn();
//        String wordLadderString = mvcResult.getResponse().getContentAsString();
//        ObjectMapper mapper = new ObjectMapper();
//        Map<String, Object> wordLadder = mapper.readValue(wordLadderString, Map.class);
//        Assert.assertEquals("From dog to cat should return List of length 4.", ((List<String>)wordLadder.get("wordLadder")).size(), 4);
//        Assert.assertTrue(((String)wordLadder.get("message")).equals("Succeed!"));
//    }

    @Test
    public void testGetWordLadderGetWithToken() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/api/wordLadder/search")
                        .param("source","dog")
                        .param("destination","cat")
                        .header("Content-Type","application/json")
                        .header("Authorization","Bearer " + token)
        ).andReturn();
        String wordLadderString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> wordLadder = mapper.readValue(wordLadderString, Map.class);
        Assert.assertEquals("From dog to cat should return List of length 4.", ((List<String>)wordLadder.get("wordLadder")).size(), 4);
        Assert.assertTrue(((String)wordLadder.get("message")).equals("Succeed!"));
    }

    @Test
    public void testGetWordLadderPostWithoutToken() throws Exception {
        JSONObject words = new JSONObject();
        words.put("source","cat");
        words.put("destination", "dog");
        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/wordLadder")
                        .content(words.toString())
        ).andExpect(status().isUnauthorized());
    }

    @Test
    public void testGetWordLadderGetWithoutToken() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/wordLadder/search")
                        .param("source","dog")
                        .param("destination","cat")
        ).andExpect(status().isUnauthorized());
    }



}


