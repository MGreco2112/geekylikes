package com.geekylikes.app.repositories;

import com.geekylikes.app.models.developer.Developer;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Long> {
    List<Developer> findAllByCohort(Integer cohort, Sort sort);
    List<Developer> findAllByLanguages(Long id);


//    @Query("SELECT * FROM developer WHERE cohort = ?1 AND ?2 in languages")
//    Developer findByCohortAndLanguage(Integer cohort, String language);
}
