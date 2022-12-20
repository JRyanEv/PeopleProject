package com.example.demo.person;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//this interface is responsible for the Data Access Layer
@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    //SELECT * FROM person WHERE email = ?
    @Query("SELECT s FROM Person s WHERE s.email = ?1")
    Optional<Person> findPersonByEmail(String email);
}

