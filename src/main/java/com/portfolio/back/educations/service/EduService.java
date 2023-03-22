package com.portfolio.back.educations.service;

import com.portfolio.back.educations.entity.Education;
import com.portfolio.back.educations.repo.EduRepository;
import com.portfolio.back.exceptions.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EduService {
    @Autowired
    EduRepository eduRepo;
    public void delete(int id)throws ResourceNotFoundException{
        if(!eduRepo.existsById(id))
            throw new ResourceNotFoundException("No existe una educacion con esa id");
        eduRepo.deleteById(id);
    }
    public Education createEducation(Education education) throws AttributeException{
        if(education.getInstitution().isEmpty() || education.getInstitution().isBlank()
                || education.getDescription().isBlank() || education.getDescription().isEmpty())
            throw new AttributeException("No pueden estar vacios los campos que representan atributos");
        if(eduRepo.existsByInstitution(education.getInstitution()))
            throw new AttributeException("Ya esiste esa educacion");
        return eduRepo.save(education);
    }
    public Education update(int id, Education education) throws ResourceNotFoundException, AttributeException{
        Education edu = eduRepo.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe ese id"));
        if(eduRepo.existsByInstitution(education.getInstitution()) && 
                eduRepo.findByInstitution(education.getInstitution()).get().getId_educ()!=id)
                throw new AttributeException("Ya existe esa institucion");
        if(education.getInstitution().isEmpty() || education.getInstitution().isBlank()
                || education.getDescription().isBlank()||education.getDescription().isEmpty())
            throw new AttributeException("No pueden estar vacios los campos que representan atributos");
        edu.setInstitution(education.getInstitution());
        edu.setDescription(education.getDescription());
        return eduRepo.save(edu);
    }
    public Optional<Education>findByInstitution(String institution){
        return eduRepo.findByInstitution(institution);
    }
    public Optional<Education>findById(int id){
        return eduRepo.findById(id);
    }
    public boolean existsById(int id){
        return eduRepo.existsById(id);
    }
    public boolean existsInstitution(String institution){
        return eduRepo.existsByInstitution(institution);
    }
    public List<Education>getAllEducation(){
        return eduRepo.findAll();
    }
}
