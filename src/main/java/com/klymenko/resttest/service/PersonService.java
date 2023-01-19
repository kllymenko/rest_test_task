package com.klymenko.resttest.service;

import com.klymenko.resttest.dto.PersonDTO;
import com.klymenko.resttest.entity.Person;
import com.klymenko.resttest.exception.NotFoundException;
import com.klymenko.resttest.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    public PersonDTO getPersonById(Long id) {
        try {
            Person person = personRepository.findById(id).orElse(null);
            if (person == null) {
                throw new NotFoundException();
            }
            LocalDate today = LocalDate.now();
            int age = Period.between(person.getBirthdate(), today).getYears();
            return new PersonDTO(person.getName(), person.getSurname(), age);
        } catch (NotFoundException e) {
            e.printStackTrace();
            throw new NotFoundException();
        }
    }

    public List<PersonDTO> getAll() {
        Iterable<Person> personList = personRepository.findAll();
        List<PersonDTO> personDTOList = new ArrayList<>();
        for (Person p : personList) {
            personDTOList.add(new PersonDTO(p.getName(), p.getSurname(), Period.between(p.getBirthdate(), LocalDate.now()).getYears()));
        }
        return personDTOList;
    }

    public void addAll(List<Person> personList) {
        personRepository.saveAll(personList);
    }

    public void deleteAll() {
        personRepository.deleteAll();
    }
}
