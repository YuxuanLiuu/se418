package se418.hw1.wordladder;

import java.util.List;
import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Set;
import java.util.HashSet;
import java.util.ArrayList;

/**
 * Hello world!
 *
 */
public class WordLadder {
    private Set<String> dictionary = new HashSet<String>();
    private Set<String> seenWords = new HashSet<String>();

    public WordLadder(Set<String> d) {
        // d.forEach(word -> word.toLowerCase());
        dictionary = d;
    }

    private List<String> oneLetterDifferFrom(String word) {
        List<String> words = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            for (char j = 'a'; j <= 'z'; j++) {
                if (word.charAt(i) != j) {
                    char[] letters = word.toCharArray();
                    letters[i] = j;
                    String wordCandidate = new String(letters);
                    if (dictionary.contains(wordCandidate) && !seenWords.contains(wordCandidate)) {
                        words.add(wordCandidate);
                    }
                }
            }
        }
        return words;
    }

    public List<String> findWordLadder(String word1, String word2) {
        List<String> wordLadder = new ArrayList<String>();

        //not in the dictionary
        if(!dictionary.contains(word1) || !dictionary.contains(word2)){
            return new ArrayList<String>();
        }

        // different lengths
        if (word1.length() != word2.length()) {
            return new ArrayList<String>();
        }

        wordLadder.add(word1);
        // same words
        if (word1.equals(word2)) {
            return wordLadder;
        }

        // general situation
        Queue<List<String>> wordLadderCandidates = new ArrayDeque<List<String>>();
        wordLadderCandidates.add(wordLadder);
        List<String> currentWordLadder = new ArrayList<String>();
        seenWords.add(word1);
        while (!wordLadderCandidates.isEmpty()) {
            currentWordLadder = wordLadderCandidates.poll();
            String lastWord = currentWordLadder.get(currentWordLadder.size() - 1);
            List<String> wordCandidates = oneLetterDifferFrom(lastWord);
            for (String wordCandidate : wordCandidates) {
                if (wordCandidate.equals(word2)) {
                    currentWordLadder.add(wordCandidate);
                    return currentWordLadder;
                }
                List<String> currentWordLadderCopy = new ArrayList<String>();
                currentWordLadderCopy.addAll(currentWordLadder);
                currentWordLadderCopy.add(wordCandidate);
                wordLadderCandidates.add(currentWordLadderCopy);
            }
        }
        return new ArrayList<String>();
    }
}
