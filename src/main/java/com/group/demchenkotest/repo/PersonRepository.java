package com.group.demchenkotest.repo;

import com.group.demchenkotest.data.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {

    @Query(value = "SELECT p FROM Person p WHERE p.id=  :personId")
    Optional<Person> findPersonById(@Param("personId") Long personId);

}