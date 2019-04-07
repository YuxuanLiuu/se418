package org.liu.se418;

import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.*;
import net.sf.json.JSONObject;

import java.io.IOException;


@RestController
public class WordLadderController {

    @RequestMapping(value = "/getWordLadder", method = RequestMethod.POST)
    public WordLadder getWordLadder(@RequestBody JSONObject words) throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
        String word1 = words.get("source").toString();
        String word2 = words.get("destination").toString();
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }

    @RequestMapping(value = "/getWordLadder", method = RequestMethod.GET)
    public WordLadder getWordLadder(@RequestParam("source") String word1, @RequestParam("destination") String word2) throws IOException {
        ClassPathResource dictionary = new ClassPathResource("SmallDict.txt");
        WordLadder wordLadder = new WordLadder(dictionary.getFile().getAbsolutePath());
        wordLadder.findWordLadder(word1, word2);
        return wordLadder;
    }
}