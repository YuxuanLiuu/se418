package org.liu.se418.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
//import net.sf.json.JSONObject;
import org.liu.se418.user.message.request.WordLadderForm;
import org.liu.se418.user.model.User;
import org.liu.se418.wordLadder.WordLadder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

@RestController
public class WordLadderController {
    @Autowired
    private WordLadder wordLadder;

    
    @RequestMapping(value = "/api/wordLadder/search", method = RequestMethod.GET)
    public WordLadder getWordLadder(@RequestParam("source") String word1, @RequestParam("destination") String word2) throws IOException {
        //ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        //WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }

//    @RequestMapping(value = "/api/wordLadder", method = RequestMethod.POST)
//    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
//    public WordLadder getWordLadder(@RequestBody WordLadderForm words) throws IOException, JSONException {
//        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
//        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
//        String word1 = words.getSource();
//        String word2 = words.getDestination();
//        wordLadder.findWordLadder(word1, word2);
//        return wordLadder;
//    }

   /* @RequestMapping(value = "/api/wordLadder", method = RequestMethod.POST)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public WordLadder getWordLadder(@RequestBody WordLadderForm words) throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
        String word1 = words.getSource();
        String word2 = words.getDestination();
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }*/

}
