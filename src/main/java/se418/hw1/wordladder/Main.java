package se418.hw1.wordladder;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

public class Main {
    public Set<String> dictionary;
    public String word1, word2;
    public static final Scanner scanner = new Scanner(System.in);

    //for the convenience of Junit test.
    public void getDictionary(String fileName) throws IOException{
        File inFile;
        inFile = new File(fileName);
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
        dictionary = new HashSet<String>();
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            dictionary.add(word);
        }
        bufferedReader.close();
    }

    public void getDictionary() throws IOException {
        String fileName = new String();
        File inFile;
        while (true) {
            System.out.println("Please input the file name of the dictionary.\n");
            if (scanner.hasNextLine()) {
                fileName = scanner.nextLine();
            }
            inFile = new File(fileName);
            if (inFile.exists())
                break;
            System.out.println("can not open the file. Try again.\n");
        }
        BufferedReader bufferedReader = new BufferedReader(new FileReader(inFile));
        dictionary = new HashSet<String>();
        String word;
        while ((word = bufferedReader.readLine()) != null) {
            dictionary.add(word);
        }
        bufferedReader.close();
    }

    public boolean get2words() throws IOException {
        System.out.println("Please input word 1 or input Enter to quit: \n");
        word1 = scanner.nextLine();
        if (word1.length() == 0) {
            System.out.println("exit!");
            scanner.close();
            return false;
        }
        System.out.println("Please input word 2 or input Enter to quit: \n");
        word2 = scanner.nextLine();
        if (word1.length() == 0) {
            System.out.println("exit!");
            scanner.close();
            return false;
        }
        word1 = word1.toLowerCase();
        word2 = word2.toLowerCase();
        return true;
    }

    public static void main(String[] args) throws IOException {
        Main test = new Main();
        test.getDictionary();
        WordLadder wordLadder = new WordLadder(test.dictionary);
        while (test.get2words()) {
            List<String> words = wordLadder.findWordLadder(test.word1, test.word2);
            if (words.isEmpty()) {
                System.out.println("no ladder found!");
            } else {
                System.out.println(words.toString());
            }
        }
    }
}