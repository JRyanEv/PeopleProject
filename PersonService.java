package com.example.demo.person;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

//Service Layer
@Service
public class PersonService {

    private final PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @Autowired
    public List<Person> getPerson() {
        return personRepository.findAll();
    }

    public void addNewPerson(Person person) {
        Optional<Person> personByEmail = personRepository.findPersonByEmail(person.getEmail());

        if(personByEmail.isPresent()){
            throw new IllegalStateException("email Already in Use");
        }
        personRepository.save(person);
        System.out.println(person);
    }

    public void deletePerson(Long personId) {
        boolean exists = personRepository.existsById(personId);
        if(!exists){
            throw new IllegalStateException("No Person with ID: " + personId + " Found." );
        }
        personRepository.deleteById(personId);
    }

    //PUT update name and email in system
    //JPQL Query
    //using setters from Entity
    @Transactional
    public void updatePerson(Long personId, String name, String email){
        Person person = personRepository.findById(personId)
                .orElseThrow(() -> new IllegalStateException(
                        "No Person with ID: " + personId + " Found." ));
        if (name != null && name.length() > 0 && !Objects.equals(person.getName(), name)){
            person.setName(name);
        }
        if (name != null && name.length() > 0 && !Objects.equals(person.getEmail(), email)){
            Optional<Person> personOptional = personRepository.findPersonByEmail(email);
            if(personOptional.isPresent()){
                throw new IllegalStateException("Email Already in Use");
            }
            person.setName(name);
        }
    }
}
