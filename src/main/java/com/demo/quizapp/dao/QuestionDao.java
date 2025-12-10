package com.demo.quizapp.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.demo.quizapp.model.Question;

@Repository
public interface QuestionDao extends JpaRepository<Question, Integer> { // <Table name (class name == table name), primary key type>
    
    List<Question> findByCategory(String category);
    
    String query = """  
                    SELECT * FROM Question q
                    WHERE q.category = :category
                    ORDER BY RANDOM()
                    LIMIT :numOfQuestion
                """;

    @Query(value = query, nativeQuery = true)
    List<Question> findRandomQuestionsByCategory(@Param("category") String category,
                                                @Param("numOfQuestion") int numOfQuestion);

}
