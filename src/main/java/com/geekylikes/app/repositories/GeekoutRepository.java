package com.geekylikes.app.repositories;

import com.geekylikes.app.models.Geekout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

@Repository
public interface GeekoutRepository extends JpaRepository<Geekout, Long> {

}
