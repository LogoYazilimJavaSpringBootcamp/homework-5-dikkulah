package com.movie.repository;

import com.movie.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long>{
    List<Person> findByFirstNameContainingOrLastNameContaining(String name,String lastName);
}