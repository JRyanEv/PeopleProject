package com.example.demo.person;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;

import static java.time.Month.*;

@Configuration
public class PersonConfig {

    @Bean
    CommandLineRunner commandLineRunner(PersonRepository repository){
        return args -> {
            Person first_person = new Person(
                    1L,
                    "First Person",
                    "first_person@gmail.com",
                    LocalDate.of(2000, APRIL, 15)
            );

            Person second_person = new Person(
                    2L,
                    "Second Person",
                    "second_person@gmail.com",
                    LocalDate.of(1999, MAY, 31)
            );

            Person ryan = new Person(
                    3L,
                    "J Ryan",
                    "email@gmail.com",
                    LocalDate.of(1998, JANUARY, 1)
            );

            repository.saveAll(
                    List.of(first_person, second_person, ryan)
            );
        };
    }
}
