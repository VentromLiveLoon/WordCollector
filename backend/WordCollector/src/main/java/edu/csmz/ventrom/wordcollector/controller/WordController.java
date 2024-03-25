package edu.csmz.ventrom.wordcollector.controller;

import edu.csmz.ventrom.wordcollector.dto.WordDto;
import edu.csmz.ventrom.wordcollector.models.Word;
import edu.csmz.ventrom.wordcollector.repository.WordRepository;
import edu.csmz.ventrom.wordcollector.service.WordService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@Slf4j
public class WordController {
    @Autowired
    WordService wordService;
//    Get previous word by WordDto's id
    @PostMapping("/word/pre")
    public WordDto wordPre(@RequestBody WordDto word){
        log.info(word.toString());
        word = wordService.wordPre(word);
        log.info(word.toString());
        return word;
    }
//    Get next word by WordDto's id
    @PostMapping("/word/next")
    public WordDto wordNext(@RequestBody WordDto word){
        log.info(word.toString());
        word = wordService.wordNext(word);
        log.info(word.toString());
        return word;
    }
//    Add wordDto to the server
    @PostMapping("/word/add")
    public String wordAdd(@RequestBody WordDto word){
        log.info(word.toString());
        String status =  wordService.wordAdd(word);
        log.info(status);
        return status;
    }
//    Delete a word from server
    @PostMapping("/word/delete")
    public String wordDelete(@RequestBody WordDto word){
        log.info(word.toString());
        String status = wordService.wordDelete(word);
        log.info(status);
        return status;
    }
//    List 10 word to client
    @PostMapping("/word/list")
    public List<Word> wordList(@RequestBody Word word){

        List<Word> wordlist = wordService.wordList(word);
        return wordlist;
    }
//    Search input suggest word list
    @PostMapping("/word/suggest")
    public List<Word> wordSuggest(@RequestBody Word word){
        List<Word> wordlist = wordService.wordSuggest(word);
        return wordlist;
    }
//    Search word
    @PostMapping("/word/search")
    public WordDto wordSearch(@RequestBody Word word){
        return wordService.wordSearch(word);
    }
}
