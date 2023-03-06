package com.group.demchenkotest.api;

import com.group.demchenkotest.dto.BaseResponse;
import com.group.demchenkotest.service.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/person")
public class PersonController {

    private final PersonService personService;


    @GetMapping("/find/{personId}")
    public ResponseEntity<BaseResponse> findPersonById(@PathVariable("personId") Long personId) {
        return new ResponseEntity<>(personService.findPersonById(personId), HttpStatus.OK);
    }

}
