package com.klymenko.resttest.service;

import com.klymenko.resttest.dto.PersonDTO;
import com.klymenko.resttest.entity.Person;
import com.klymenko.resttest.repository.PersonRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonService personService;

    @Test
    public void getPersonByIdTest() {
        Person person = new Person(1, "Vanya", "Osi", LocalDate.of(1996, 5, 9));
        Optional<Person> optionalPerson = Optional.of(person);
        when(personRepository.findById(1L)).thenReturn(optionalPerson);
        PersonDTO personRes = personService.getPersonById(1L);
        assertEquals(personRes.getName(), person.getName());
        assertEquals(personRes.getSurname(), person.getSurname());
        assertEquals(personRes.getAge(), Period.between(person.getBirthdate(), LocalDate.now()).getYears());
    }

    @Test
    public void getAllTest(){
        List<Person> personList = Arrays.asList(
                new Person(0, "Vanya", "Osi", LocalDate.of(1996, 5, 9)),
                new Person(1, "Bob", "Hunix", LocalDate.of(2016, 1, 27)),
                new Person(2, "Olesia", "Klimova", LocalDate.of(1980, 3, 16)),
                new Person(3, "Olena", "Obramova", LocalDate.of(1970, 7, 7))
        );
        when(personRepository.findAll()).thenReturn(personList);
        List<PersonDTO> personDTOList = personService.getAll();
        assertEquals(personList.size(), personDTOList.size());
        for (int i = 0; i < personList.size(); i++) {
            assertEquals(personDTOList.get(i).getName(), personList.get(i).getName());
            assertEquals(personDTOList.get(i).getSurname(), personList.get(i).getSurname());
            assertEquals(personDTOList.get(i).getAge(), Period.between(personList.get(i).getBirthdate(), LocalDate.now()).getYears());
        }
    }
}
