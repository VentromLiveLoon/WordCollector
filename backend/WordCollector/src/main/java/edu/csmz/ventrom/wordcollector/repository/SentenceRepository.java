package edu.csmz.ventrom.wordcollector.repository;

import edu.csmz.ventrom.wordcollector.models.Sentence;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SentenceRepository extends CrudRepository<Sentence,Long> {
//    Fuzzy query some sentence
    @Query("select s from Sentence s where instr( s.sentence,:word ) > 0")
    List<Sentence> findSentencesWithWord(@Param("word") String word);
}
