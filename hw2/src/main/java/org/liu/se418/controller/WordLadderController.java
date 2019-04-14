package org.liu.se418.controller;


import org.liu.se418.wordLadder.WordLadder;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class WordLadderController {
    @RequestMapping(value = "/api/wordLadder/search", method = RequestMethod.GET)
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public WordLadder getWordLadder(@RequestParam("source") String word1, @RequestParam("destination") String word2) throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }

}
