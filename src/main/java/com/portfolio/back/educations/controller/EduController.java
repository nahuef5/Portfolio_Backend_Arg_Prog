package com.portfolio.back.educations.controller;

import com.portfolio.back.educations.entity.Education;
import com.portfolio.back.educations.service.EduService;
import com.portfolio.back.exceptions.*;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/education/")
@CrossOrigin(origins="http://localhost:8080")
public class EduController {
    @Autowired
    EduService eduServ;    
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create/")
    public ResponseEntity<?>create(@Valid @RequestBody Education education) throws AttributeException, ResourceNotFoundException{
            eduServ.createEducation(education);
            String msj="Educacion creada y guardada";
            return  ResponseEntity.ok(new Mensaje(HttpStatus.CREATED, msj));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update/{id}/")
    public ResponseEntity<Mensaje> update(@PathVariable ("id")int id,@Valid @RequestBody Education education) throws ResourceNotFoundException, AttributeException{
        Education edu = eduServ.update(id, education);
        String msj = edu.getInstitution() +" fue actualizado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, msj));
    }
    @GetMapping("all/")
    @ResponseBody
    public ResponseEntity<List<Education>>getAll(){
        List<Education>edus=eduServ.getAllEducation();
        return ResponseEntity.ok(edus);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping ("delete/{id}/")
    public ResponseEntity<?>delete(@PathVariable("id")int id) throws ResourceNotFoundException{
        eduServ.delete(id);
        String msj="Edu eliminada";
        return new ResponseEntity(new Mensaje(HttpStatus.OK, msj), HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping ("detail/{insti}/")
    @ResponseBody
    public ResponseEntity<Education>getByInstitution(@PathVariable("insti")String insti) throws ResourceNotFoundException{
       if(!eduServ.existsInstitution(insti))
            throw new ResourceNotFoundException("No existe esa institucion");
        Education edu=eduServ.findByInstitution(insti).get();
        return new ResponseEntity(edu, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detailById/{id}/")
    public ResponseEntity<Education>getEducationById(@PathVariable ("id")int id) throws ResourceNotFoundException{
        if(!eduServ.existsById(id))
            throw new ResourceNotFoundException("No existe una educacion con esa id");
        Education edu=eduServ.findById(id).get();
        return new ResponseEntity(edu, HttpStatus.OK);
    }   
}