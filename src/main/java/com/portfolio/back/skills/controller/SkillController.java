package com.portfolio.back.skills.controller;

import com.portfolio.back.exceptions.*;
import com.portfolio.back.skills.entity.Skill;
import com.portfolio.back.skills.service.SkillService;
import java.util.*;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.access.prepost.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skills/")
@CrossOrigin(origins="http://localhost:8080")
public class SkillController {
    @Autowired
    SkillService skillService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("create/")
    public ResponseEntity<?>createSkill(@Valid @RequestBody Skill skill) throws AttributeException{
        skillService.createSkill(skill);
        String msj="Skill creada exitosamente";
        return ResponseEntity.ok(new Mensaje(HttpStatus.CREATED, msj));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("update/{id}/")
    public ResponseEntity<Mensaje>updateSkill(@PathVariable("id")int id, @Valid @RequestBody Skill skill) throws ResourceNotFoundException, AttributeException{
        Skill ski=skillService.updateSkill(id, skill);
        String msj=ski.getSkill() + " fue actualizado";
        return ResponseEntity.ok(new Mensaje(HttpStatus.OK, msj));
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("delete/{id}/")
    public ResponseEntity<Mensaje>deleteSkill(@PathVariable("id") int id) throws ResourceNotFoundException{
        skillService.delete(id);
        String msj="Skill eliminada";
        return new ResponseEntity(new Mensaje(HttpStatus.OK, msj), HttpStatus.OK);
    }
    @GetMapping("all/")
    @ResponseBody
    public ResponseEntity<List<Skill>> getAll(){
        List<Skill>skills=skillService.getAllSkill();
        return new ResponseEntity(skills, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detail/{skill}/")
    public ResponseEntity<Skill> getBySkill(@PathVariable("skill")String skill) throws ResourceNotFoundException{
        if(!skillService.existsSkill(skill))
            throw new ResourceNotFoundException("No existe esa habilidad");
        Skill getBySkill=skillService.findBySkill(skill).get();
        return new ResponseEntity(getBySkill, HttpStatus.OK);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("detailById/{id}/")
    public ResponseEntity<Skill>getById(@PathVariable("id")int id) throws ResourceNotFoundException{
        if(!skillService.existsById(id))
            throw new ResourceNotFoundException("No existe una habilidad con esa id");
        Skill getById=skillService.getSkillById(id).get();
        return new ResponseEntity(getById, HttpStatus.OK);
    }
}
