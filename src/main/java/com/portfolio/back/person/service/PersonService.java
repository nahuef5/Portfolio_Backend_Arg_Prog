package com.portfolio.back.person.service;

import com.portfolio.back.exceptions.*;
import com.portfolio.back.person.entity.Person;
import com.portfolio.back.person.repo.PersonRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class PersonService {
    @Autowired
    PersonRepository personRepository;
    
    public Person createPerson(Person person){
        if(!personRepository.findAll().isEmpty()){
            throw new PersonExistsException("Solo se puede crear una persona");
        }
        return personRepository.save(person);
    }
    public Optional<Person>findByName(String name){
        return personRepository.findByName(name);
    }
    public Optional<Person>findById(Long id){
        return personRepository.findById(id);
    }
    public boolean notExistsPerson(){
        return personRepository.findAll().isEmpty();
    }
    
    public List<Person> getPerson(){
        return personRepository.findAll();
    } 
    public Person update(Long id, Person person) throws ResourceNotFoundException{
        Person per=personRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("La persona existente no tiene ese id"));
        per.setName(person.getName());
        per.setSurname(person.getSurname());
        per.setDescription(person.getDescription());
        per.setProfession(person.getProfession());
        return personRepository.save(per);
    }    
    public boolean existsById(Long id){
        return personRepository.existsById(id);
    }
}
