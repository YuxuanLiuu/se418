package org.liu.se418;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = WordLadderApplication.class)
public class WordLadderControllerTest {
    protected MockMvc mockMvc;

    @Autowired
    protected WebApplicationContext webApplicationContext;

    @Before
    public void before() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetWordLadderPost() throws Exception {
        JSONObject words = new JSONObject();
        words.put("source","cat");
        words.put("destination", "dog");
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.post("/getWordLadder")
                        .content(words.toString())
                        .header("Content-Type","application/json")
        ).andReturn();
        String wordLadderString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> wordLadder = mapper.readValue(wordLadderString, Map.class);
        Assert.assertEquals("From dog to cat should return List of length 4.", ((List<String>)wordLadder.get("wordLadder")).size(), 4);
        Assert.assertTrue(((String)wordLadder.get("message")).equals("Succeed!"));
    }

    @Test
    public void testGetWordLadderGet() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders.get("/getWordLadder")
                        .param("source","dog")
                        .param("destination","cat")
        ).andReturn();
        String wordLadderString = mvcResult.getResponse().getContentAsString();
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> wordLadder = mapper.readValue(wordLadderString, Map.class);
        Assert.assertEquals("From dog to cat should return List of length 4.", ((List<String>)wordLadder.get("wordLadder")).size(), 4);
        Assert.assertTrue(((String)wordLadder.get("message")).equals("Succeed!"));
    }
}
