package com.portfolio.back.person.controller;

import com.portfolio.back.exceptions.Mensaje;
import com.portfolio.back.exceptions.PersonExistsException;
import com.portfolio.back.exceptions.ResourceNotFoundException;
import com.portfolio.back.person.entity.Person;
import com.portfolio.back.person.service.PersonService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/me/")
@CrossOrigin(origins="http://localhost:8080") 
public class PersonController {
    @Autowired
    PersonService personService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create/")
    public ResponseEntity<Person> createPerson(@Valid @RequestBody Person person){
        try{
            Person createdPerson= personService.createPerson(person);
            return new ResponseEntity(createdPerson, HttpStatus.CREATED);
        }
        catch(PersonExistsException e){
            String msj=""+e;
            return new ResponseEntity(msj, HttpStatus.FORBIDDEN);
        }
    }
    @GetMapping("read/")
    public ResponseEntity<List<Person>>readPerson(){
        if(personService.notExistsPerson()){
            String msj="No existe ninguna persona";
            return new ResponseEntity(msj, HttpStatus.NOT_FOUND);
        }
        List<Person> per=personService.getPerson();
        return new ResponseEntity(per, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update/{id}/")
    public ResponseEntity<?> updatePerson(@PathVariable ("id") Long id,@Valid @RequestBody Person person) throws ResourceNotFoundException{
        Person per= personService.update(id, person);
        String msj="Se actualizo la persona: "+ per.getName();
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, msj));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detailById/{id}/")
    public ResponseEntity<Person>getPersonById(@PathVariable ("id")Long id) throws ResourceNotFoundException{
        Person person=personService.findById(id).get();
        return new ResponseEntity(person, HttpStatus.OK);
    }
}
