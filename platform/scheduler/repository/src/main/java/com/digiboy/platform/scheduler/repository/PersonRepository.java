package com.digiboy.platform.scheduler.repository;

import com.digiboy.platform.scheduler.to.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
}
