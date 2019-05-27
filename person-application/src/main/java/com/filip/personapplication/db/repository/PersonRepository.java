package com.filip.personapplication.db.repository;

import com.filip.personapplication.db.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Integer> {

}
