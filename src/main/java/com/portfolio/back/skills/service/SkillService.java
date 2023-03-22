package com.portfolio.back.skills.service;

import com.portfolio.back.exceptions.AttributeException;
import com.portfolio.back.exceptions.ResourceNotFoundException;
import com.portfolio.back.skills.entity.Skill;
import com.portfolio.back.skills.repo.SkillRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class SkillService {
    @Autowired
    SkillRepository skillRepository;
    public Optional<Skill>findBySkill(String Skill)throws ResourceNotFoundException{
        if(!skillRepository.existsBySkill(Skill))
            throw new ResourceNotFoundException("No existe esa habilidad");
        return skillRepository.findBySkill(Skill);
    }
    public boolean existsSkill(String skill){
        return skillRepository.existsBySkill(skill);
    }
    public Optional<Skill> getSkillById(int id){
        return skillRepository.findById(id);
    }
    public boolean existsById(int id){
        return skillRepository.existsById(id);
    }
    public List<Skill>getAllSkill(){
        return skillRepository.findAll();
    }
    public Skill createSkill(Skill skill) throws AttributeException{
        if(skill.getSkill().isEmpty())
            throw new AttributeException("Campo vacio");
        if(skill.getPercentage().isNaN())
            throw new AttributeException("Porcentaje debe ser numérico");
        if(skillRepository.existsBySkill(skill.getSkill()))
            throw new AttributeException("ya existe esa skill");            
        return skillRepository.save(skill);
    }
    public Skill updateSkill (int id, Skill skill)throws ResourceNotFoundException, AttributeException{
        Skill ski=skillRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No existe ese id"));
        if(skillRepository.existsBySkill(skill.getSkill()) 
                && skillRepository.findBySkill(skill.getSkill()).get().getId_skill() !=id)
                throw new AttributeException("Ya existe esa habilidad");
        if(skill.getSkill().isEmpty())
            throw new AttributeException("Campos vacios");
        ski.setSkill(skill.getSkill());
        ski.setPercentage(skill.getPercentage());
        return skillRepository.save(ski);
    }
    public void delete(int id)throws ResourceNotFoundException{
        if(!skillRepository.existsById(id))
            throw new ResourceNotFoundException("No existe habilidad con esa id");
        skillRepository.deleteById(id);
    }
}
