package com.portfolio.back.exp.controller;

import com.portfolio.back.exceptions.*;
import com.portfolio.back.exp.entity.Experience;
import com.portfolio.back.exp.service.ExperinceService;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/experience/")
@CrossOrigin(origins="http://localhost:8080")
public class ExpController {
    @Autowired
    ExperinceService expService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create/")
    public ResponseEntity<?>createExperience(@RequestBody Experience experience) throws AttributeException{
        expService.createExp(experience);
        String msj="Experiencia creada correctamente";
        return ResponseEntity.ok(new Mensaje(HttpStatus.CREATED, msj));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update/{id}/")
    public ResponseEntity<?>updateExp(@PathVariable("id")int id, @Valid @RequestBody Experience exp) throws ResourceNotFoundException, AttributeException{
        Experience e=expService.updateExperience(id, exp);
        String msj="La experiencia fue actualizada "+ e.getProject();
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK,msj));
    }  
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("delete/{id}/")
    public ResponseEntity<Mensaje>deleteExp(@PathVariable("id")int id) throws ResourceNotFoundException{
        expService.deleteExp(id);
        String msj="Experiencia eliminada";
        return new ResponseEntity(new Mensaje(HttpStatus.OK, msj), HttpStatus.OK);
    }
    @GetMapping("all/")
    @ResponseBody
    public ResponseEntity<List<Experience>>getAll(){
        List<Experience>exp=expService.getAllExp();
        return new ResponseEntity(exp, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detail/{company}")
    public ResponseEntity<Experience>getByCompany(@PathVariable("company")String company) throws ResourceNotFoundException{
        if(!expService.existsCompany(company))
            throw new ResourceNotFoundException("No existe esa Empresa");
        Experience getByCompany=expService.getByCompany(company).get();
        return new ResponseEntity(getByCompany, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detailById/{id}")
    public ResponseEntity<Experience>getById(@PathVariable("id")int id) throws ResourceNotFoundException{
        if(!expService.existsById(id))
            throw new ResourceNotFoundException("No existe experiencia con esa id");
        Experience getById=expService.getById(id).get();
        return new ResponseEntity(getById, HttpStatus.OK);
    }
}
