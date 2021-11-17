package com.geekylikes.app.repositories;

import com.geekylikes.app.models.geekout.Geekout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GeekoutRepository extends JpaRepository<Geekout, Long> {
    List<Geekout> findByDeveloperId(Long id);

}
