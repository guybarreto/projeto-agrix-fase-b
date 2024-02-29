package com.betrybe.agrix.solution;

import com.betrybe.agrix.ebytr.staff.entity.Person;
import com.betrybe.agrix.ebytr.staff.exception.PersonNotFoundException;
import com.betrybe.agrix.ebytr.staff.repository.PersonRepository;
import com.betrybe.agrix.ebytr.staff.service.PersonService;
import com.betrybe.agrix.ebytr.staff.security.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("PersonService Tests")
public class PersonServiceTest {

  @Mock
  private PersonRepository personRepository;

  @InjectMocks
  private PersonService personService;

  @Test
  @DisplayName("Get Person by ID - Success")
  void testGetPersonById() {
    Long personId = 1L;
    Person person = new Person();
    person.setId(personId);
    person.setUsername("JohnDoe");
    person.setPassword("password");
    person.setRole(Role.USER);

    when(personRepository.findById(personId)).thenReturn(Optional.of(person));

    Person result = personService.getPersonById(personId);

    assertEquals(person, result);
  }

  @Test
  @DisplayName("Get Person by ID - Not Found")
  void testGetPersonByIdNotFound() {
    Long personId = 1L;

    when(personRepository.findById(personId)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonById(personId));
  }

  @Test
  @DisplayName("Get Person by Username - Success")
  void testGetPersonByUsername() {
    String username = "JohnDoe";
    Person person = new Person();
    person.setId(1L);
    person.setUsername(username);
    person.setPassword("password");
    person.setRole(Role.USER);

    when(personRepository.findByUsername(username)).thenReturn(Optional.of(person));

    Person result = personService.getPersonByUsername(username);

    assertEquals(person, result);
  }

  @Test
  @DisplayName("Get Person by Username - Not Found")
  void testGetPersonByUsernameNotFound() {
    String username = "JohnDoe";

    when(personRepository.findByUsername(username)).thenReturn(Optional.empty());

    assertThrows(PersonNotFoundException.class, () -> personService.getPersonByUsername(username));
  }

  @Test
  @DisplayName("Create Person")
  void testCreatePerson() {
    Person personToCreate = new Person();
    personToCreate.setUsername("JohnDoe");
    personToCreate.setPassword("password");
    personToCreate.setRole(Role.USER);

    Person createdPerson = new Person();
    createdPerson.setId(1L);
    createdPerson.setUsername("JohnDoe");
    createdPerson.setPassword("password");
    createdPerson.setRole(Role.USER);

    when(personRepository.save(any())).thenReturn(createdPerson);

    Person result = personService.create(personToCreate);

    assertNotNull(result.getId());
    assertEquals(createdPerson.getUsername(), result.getUsername());
    assertEquals(createdPerson.getPassword(), result.getPassword());
    assertEquals(createdPerson.getRole(), result.getRole());
  }
}
