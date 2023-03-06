package com.group.demchenkotest.service;

import com.group.demchenkotest.data.Message;
import com.group.demchenkotest.data.Person;
import com.group.demchenkotest.dto.BaseResponse;
import com.group.demchenkotest.dto.ResponseDto;
import com.group.demchenkotest.exception.PersonByIdNotFoundException;
import com.group.demchenkotest.repo.PersonRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;


@Service
@Slf4j
@AllArgsConstructor
public class PersonService {

    private final PersonRepository personRepository;

    public BaseResponse findPersonById(Long personId) {

        try {

            log.info("Try to find person with id: {}", personId);

            Person person = personRepository.findPersonById(personId)
                    .orElseThrow(() -> new PersonByIdNotFoundException("Person with id: " + personId + ", not found"));

            ResponseDto responseDto = new ResponseDto();
            responseDto.setFirstName(person.getFirstName());
            responseDto.setLastName(person.getLastName());
            responseDto.setAge(Period.between(person.getDof(), LocalDate.now()).getYears());
            responseDto.setMessage(Message.SUCCESS);

            return responseDto;

        }catch (PersonByIdNotFoundException e) {
            return new BaseResponse(Message.NOT_FOUND);
        }
    }

}
