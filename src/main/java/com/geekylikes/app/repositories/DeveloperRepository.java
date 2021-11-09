package com.geekylikes.app.repositories;

import com.geekylikes.app.models.Developer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findAllByCohort(Integer cohort, Sort sort);


//    @Query("SELECT * FROM developer WHERE cohort = ?1 AND ?2 in languages")
//    Developer findByCohortAndLanguage(Integer cohort, String language);
}
