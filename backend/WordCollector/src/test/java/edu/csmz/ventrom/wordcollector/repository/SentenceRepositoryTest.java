package edu.csmz.ventrom.wordcollector.repository;

import edu.csmz.ventrom.wordcollector.models.Sentence;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.Console;
import java.util.List;
import java.util.Optional;

@SpringBootTest
public class SentenceRepositoryTest {
    @Autowired
    SentenceRepository sentenceRepository;
    @Test
    void saveSentences(){
//        Insert data to h2 database
//        Sentence sentence = new Sentence();
//        sentence.setSentence("This is my first wordword in this app");
//        sentence.setTranslated("这是我的第一个句子在这个app里面");
//        Sentence save = sentenceRepository.save(sentence);
//        System.out.println(save);
//
//        Sentence sentence2 = new Sentence();
//        sentence2.setSentence("This is my second word in this app");
//        sentence2.setTranslated("这是我的第二个句子在这个app里面");
//        Sentence save2 = sentenceRepository.save(sentence2);
//        System.out.println(save2);
//
//        Sentence sentence3 = new Sentence();
//        sentence3.setSentence("This is my third word in this app");
//        sentence3.setTranslated("这是我的第三个句子在这个app里面");
//        Sentence save3 = sentenceRepository.save(sentence3);
//        System.out.println(save3);
    }

    @Test
    void testFindSentencesWithWord(){
//        A word ,it front and back shoud have a blank
//        List<Sentence> word = sentenceRepository.findSentencesWithWord(" word ");
//        System.out.println(word.toString());
    }
    @Test
    void testSelectById(){
//        Select by id
//        Optional<Sentence> sentence1 = sentenceRepository.findById((long) 1);
//        Optional<Sentence> sentence2 = sentenceRepository.findById((long) 2);
//        Optional<Sentence> sentence3 = sentenceRepository.findById((long) 3);
//
//        System.out.println(sentence1.toString());
//        System.out.println(sentence2.toString());
//        System.out.println(sentence3.toString());
    }
}
