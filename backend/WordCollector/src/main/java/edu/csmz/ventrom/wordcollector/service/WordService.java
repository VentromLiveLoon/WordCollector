package edu.csmz.ventrom.wordcollector.service;

import edu.csmz.ventrom.wordcollector.dto.WordDto;
import edu.csmz.ventrom.wordcollector.models.Sentence;
import edu.csmz.ventrom.wordcollector.models.User;
import edu.csmz.ventrom.wordcollector.models.Word;
import edu.csmz.ventrom.wordcollector.repository.SentenceRepository;
import edu.csmz.ventrom.wordcollector.repository.WordRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class WordService {
    @Autowired
    WordRepository wordRepository;
    @Autowired
    SentenceRepository sentenceRepository;

    public WordDto wordPre(WordDto wordDto){
        long id = wordDto.getId();
        Word preword = wordRepository.findPreWordById(id);
        if (preword==null){
            log.info("Not find id::",wordDto.getId());
            return wordDto;
        }
        List<Sentence> sentences = sentenceRepository.findSentencesWithWord(" "+preword.getWord()+" ");
        if (sentences!=null){
            wordDto.setSentences(sentences);
        }
        wordDto.setId(preword.getId());
        wordDto.setWord(preword.getWord());
        wordDto.setRoot(preword.getRoot());
        wordDto.setPrefix(preword.getPrefix());
        wordDto.setSubfix(preword.getSubfix());
        wordDto.setInterpretation(preword.getInterpretation());
        wordDto.setExplain(wordDto.getExplain());

        return wordDto;
    }

    public WordDto wordNext(WordDto wordDto){
        long id = wordDto.getId();
        Word preword = wordRepository.findNextWordById(id);
        if (preword==null){
            log.info("Not find id::",wordDto.getId());
            return wordDto;
        }
        List<Sentence> sentences = sentenceRepository.findSentencesWithWord(" "+preword.getWord()+" ");
        if (sentences!=null){
            wordDto.setSentences(sentences);
        }
        wordDto.setId(preword.getId());
        wordDto.setWord(preword.getWord());
        wordDto.setRoot(preword.getRoot());
        wordDto.setPrefix(preword.getPrefix());
        wordDto.setSubfix(preword.getSubfix());
        wordDto.setInterpretation(preword.getInterpretation());
        wordDto.setExplain(wordDto.getExplain());

        return wordDto;
    }
    public String wordDelete(WordDto wordDto){
        wordRepository.deleteById(wordDto.getId());
        return "ok";
    }
    public String wordAdd(WordDto wordDto){
        if (checkWordDtoIsNull(wordDto)==false){
            return "Word dto have some void";
        }
        Word word = getWordFromWordDto(wordDto);
        List<Sentence> sentences = getSentenceFromWordDto(wordDto);
        try {
            wordRepository.save(word);
        }catch (Exception e){
            return "This word is already saved!!";
        }
        try {
            sentences.forEach(sentence -> {
                sentenceRepository.save(sentence);
            });
        }catch (Exception e){
            return "This sentence may be already exists!!";
        }
        return "ok";
    }
    public List<Word> wordList(Word word){
        List<Word> wordList = wordRepository.findWordsByIdLimite(word.getId());
        if (wordList.isEmpty()){
            return null;
        }
        return wordList;
    }
    public List<Word> wordSuggest(Word word){
        List<Word> words = wordRepository.findWordsWithWord(word.getWord());
        return words;
    }
    public WordDto wordSearch(Word word){
        Word wordById = wordRepository.findWordById(word.getId());
        if (wordById==null){
            return null;
        }
        List<Sentence> sentences = sentenceRepository.findSentencesWithWord(" "+wordById.getWord()+" ");
        return packageWordAndSentences(wordById,sentences);
    }

    private WordDto packageWordAndSentences(Word word,List<Sentence> sentences){
        WordDto wordDto = new WordDto();
        wordDto.setId(word.getId());
        wordDto.setWord(word.getWord());
        wordDto.setRoot(word.getRoot());
        wordDto.setPrefix(word.getPrefix());
        wordDto.setSubfix(word.getSubfix());
        wordDto.setInterpretation(word.getInterpretation());
        wordDto.setExplain(wordDto.getExplain());
        wordDto.setSentences(sentences);
        return wordDto;
    }
    private String wordFindInSentenceFromat(String word){

        return "";
    }
    private boolean checkWordDtoIsNull(WordDto wordDto){
        if (wordDto.getWord().equals("")){
            return false;
        }else if(wordDto.getPrefix().equals("")){
            return false;
        }else if (wordDto.getInterpretation().equals("")){
            return false;
        }else if (wordDto.getRoot().equals("")) {
            return false;
        }else if(wordDto.getSubfix().equals("")){
            return false;
        }else if(wordDto.getExplain().equals("")){
            return false;
        }else{
            return true;
        }
    }
    private Word getWordFromWordDto(WordDto wordDto){

        Word word = new Word();
        word.setId(wordDto.getId());
        word.setWord(wordDto.getWord());
        word.setRoot(wordDto.getRoot());
        word.setExplain(wordDto.getExplain());
        word.setPrefix(wordDto.getPrefix());
        word.setSubfix(wordDto.getSubfix());
        word.setInterpretation(wordDto.getInterpretation());
        return  word;
    }

    private List<Sentence> getSentenceFromWordDto(WordDto wordDto){
        List<Sentence> sentences = wordDto.getSentences();
        return sentences;
    }
}
