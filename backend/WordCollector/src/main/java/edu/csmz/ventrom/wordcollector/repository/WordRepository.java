package edu.csmz.ventrom.wordcollector.repository;

import edu.csmz.ventrom.wordcollector.dto.WordDto;
import edu.csmz.ventrom.wordcollector.models.Word;
import lombok.extern.java.Log;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface WordRepository extends CrudRepository<Word,Long> {
    Word findWordById(Long id);
    Word findWordByWord(String word);
//  Fuzzy query
//    @Query("select w from Word w where instr(w.word,:word) > 0 order by w.word desc limit 10")
//    List<Word> findWordsWithWord(@Param("word") String word);
    @Query("select w from Word w where w.word like :word% order by w.word desc limit 10")
    List<Word> findWordsWithWord(@Param("word") String word);

    @Query("select w from Word w where w.id<:id order by w.id desc limit 1")
    Word findPreWordById(@Param("id") long id);

    @Query("select w from Word w where w.id>:id order by w.id asc limit 1")
    Word findNextWordById(@Param("id") long id);

    @Query("select w from Word w where w.id>:id order by w.id asc limit 10")
    List<Word> findWordsByIdLimite(@Param("id") long id);
}
