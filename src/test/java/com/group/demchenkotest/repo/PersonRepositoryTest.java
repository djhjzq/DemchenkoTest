package com.group.demchenkotest.repo;

import com.group.demchenkotest.data.Person;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class PersonRepositoryTest {

    @Autowired
    private PersonRepository personRepository;

    @AfterEach
    void tearDown() {
        personRepository.deleteAll();
    }

    @Test
    void itShouldFindPersonById() {
        //GIVEN
        String firstName = "Dmytro";
        String lastName = "Demchenko";
        LocalDate dof = LocalDate.of(1999, 7, 23);

        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDof(dof);

        personRepository.save(person);

        //WHEN
        Optional<Person> foundPerson = personRepository.findPersonById(person.getId());

        //THEN
        assertThat(foundPerson.isPresent()).isTrue();
        assertThat(foundPerson.get().getFirstName()).isEqualTo(person.getFirstName());
        assertThat(foundPerson.get().getLastName()).isEqualTo(person.getLastName());
        assertThat(foundPerson.get().getDof()).isEqualTo(person.getDof());

    }

    @Test
    void itShouldNotFindPersonById() {
        //GIVEN
        long personId = 1L;
        //WHEN
        Optional<Person> foundPerson = personRepository.findPersonById(personId);
        //THEN
        assertThat(foundPerson.isPresent()).isFalse();
    }
}