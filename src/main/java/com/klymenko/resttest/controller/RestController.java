package com.klymenko.resttest.controller;


import com.klymenko.resttest.dto.PersonDTO;
import com.klymenko.resttest.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@org.springframework.web.bind.annotation.RestController
@RequestMapping("person")
public class RestController {
    @Autowired
    private PersonService personService;

    @GetMapping
    public List<PersonDTO> getAll(){
        return personService.getAll();
    }

    @GetMapping("{id}")
    public PersonDTO getPersonById(@PathVariable long id){
        return personService.getPersonById(id);
    }
}
