package com.klymenko.resttest.controller;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.klymenko.resttest.dto.PersonDTO;
import com.klymenko.resttest.entity.Person;
import com.klymenko.resttest.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class RestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PersonService personService;

    @Autowired
    private ObjectMapper objectMapper;

    private final List<Person> personList = Arrays.asList(
            new Person(1, "Vanya", "Osi", LocalDate.of(1996, 5, 9)),
            new Person(2, "Bob", "Hunix", LocalDate.of(2016, 1, 27)),
            new Person(3, "Olesia", "Klimova", LocalDate.of(1980, 3, 16)),
            new Person(4, "Olena", "Obramova", LocalDate.of(1970, 7, 7))
    );

    @BeforeEach
    public void setUp() {
        personService.deleteAll();
        personService.addAll(personList);
    }

    @Test
    @Transactional
    public void getPersonByIdTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/person/" + personList.get(0).getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        PersonDTO personDTO = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<PersonDTO>() {
        });
        assertEquals(personList.get(0).getName(), personDTO.getName());
        assertEquals(personList.get(0).getSurname(), personDTO.getSurname());
        assertEquals(Period.between(personList.get(0).getBirthdate(), LocalDate.now()).getYears(), personDTO.getAge());
    }


    @Test
    @Transactional
    public void getAllTest() throws Exception {
        MvcResult result = mockMvc.perform(get("/person"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andReturn();
        List<PersonDTO> personDTOList = objectMapper.readValue(result.getResponse().getContentAsString(), new TypeReference<List<PersonDTO>>() {
        });
        assertEquals(personList.size(), personDTOList.size());
    }
}
