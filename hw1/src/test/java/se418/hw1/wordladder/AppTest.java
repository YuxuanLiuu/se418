package se418.hw1.wordladder;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */

    private Main test;
    private WordLadder wordLadder;
    @Before 
    public void setup() throws IOException {
        test = new Main();
        test.getDictionary("EnglishWords.txt");
        wordLadder = new WordLadder(test.dictionary);
    }

    @Test
    public void testSameWords() {
        for(int i = 0; i < 50; i++){
            String word = test.dictionary.iterator().next();
            List<String> ladder = wordLadder.findWordLadder(word, word);
            
            assertTrue("the case of passing the same words failed", 
            ladder.size() == 1 && ladder.get(0).equals(word));
        }
        
    }

   @Test
    public void testWordsOfDifferentLength(){
        List<String> shortWords = test.dictionary.stream().
        filter(w -> w.length() < 7).
        collect(Collectors.toList()).subList(0, 50);
        
        List<String> longWords = test.dictionary.stream().
        filter(w -> w.length() >= 7).
        collect(Collectors.toList()).subList(0, 50);

        for(int i = 0; i < 50; i++){
            List<String> ladder = wordLadder.
            findWordLadder(longWords.get(i), shortWords.get(i));

            assertTrue("the case of passing words of defferent length failed",
            ladder.isEmpty());
        }
    }

    //the test results come from http://www.keithschwarz.com/interesting/code/word-ladder/WordLadders.html
    //Because of the use of BSF, the length of the ladder we get should be the same as the test results.
    @Test
    public void testWordsHavingLadder(){
        List<String> sourceWords      = Arrays.asList("cat", "there", "player");
        List<String> destinationWords = Arrays.asList("dog", "words", "ladder");
        List<Integer> expectedLength  = Arrays.asList(    4,      10,       10);

        for(int i = 0; i < sourceWords.size(); i++){
            List<String> ladder = wordLadder.
            findWordLadder(sourceWords.get(i), destinationWords.get(i));

            assertTrue("general case failed", ladder.size() == expectedLength.get(i));
        }
    }
}