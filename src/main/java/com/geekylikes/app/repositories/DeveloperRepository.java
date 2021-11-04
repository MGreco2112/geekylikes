package com.geekylikes.app.repositories;

import com.geekylikes.app.models.Developer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface DeveloperRepository extends JpaRepository<Developer, Long> {


}
