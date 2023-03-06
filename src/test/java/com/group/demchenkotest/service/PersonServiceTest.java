package com.group.demchenkotest.service;

import com.group.demchenkotest.data.Message;
import com.group.demchenkotest.data.Person;
import com.group.demchenkotest.dto.BaseResponse;
import com.group.demchenkotest.dto.ResponseDto;
import com.group.demchenkotest.repo.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonService(personRepository);
    }

    @Test
    void ItShouldReturnNotFoundMessage() {
        //GIVEN
        Message message = Message.NOT_FOUND;
        long personId = 1;


        given(personRepository.findPersonById(personId)).willReturn(Optional.empty());

        //WHEN
        BaseResponse baseResponse = personService.findPersonById(personId);

        //THEN
        assertNotNull(baseResponse);
        assertThat(baseResponse.getMessage()).isEqualTo(message);
    }

    @Test
    void ItShouldReturnResponseDTO() {
        //GIVEN
        Message message = Message.SUCCESS;
        long personId = 1;
        String firstName = "Dmytro";
        String lastName = "Demchenko";
        LocalDate dof = LocalDate.of(1999, 7, 23);
        int age = 23;

        Person person = new Person();
        person.setId(personId);
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setDof(dof);


        given(personRepository.findPersonById(person.getId())).willReturn(Optional.of(person));


        //WHEN
        BaseResponse baseResponse = personService.findPersonById(person.getId());


        //THEN
        ResponseDto responseDto = (ResponseDto) baseResponse;
        assertNotNull(responseDto);
        assertThat(responseDto.getFirstName()).isEqualTo(person.getFirstName());
        assertThat(responseDto.getLastName()).isEqualTo(person.getLastName());
        assertThat(responseDto.getAge()).isEqualTo(age);
        assertThat(responseDto.getMessage()).isEqualTo(message);

    }
}