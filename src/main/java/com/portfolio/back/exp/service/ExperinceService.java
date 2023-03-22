package com.portfolio.back.exp.service;
import com.portfolio.back.exceptions.*;
import com.portfolio.back.exp.entity.Experience;
import com.portfolio.back.exp.repo.ExpRepository;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class ExperinceService {
    @Autowired
    ExpRepository expRepository;
    public Experience createExp(Experience exp)throws AttributeException{
        if(exp.getCompany().isEmpty() )
            throw new AttributeException("Los campos no pueden estar vacios");
        if(expRepository.existsByCompany(exp.getCompany()))
            throw new AttributeException("Ya existe esa empresa");
        return expRepository.save(exp);
    }
    public void deleteExp(int id)throws ResourceNotFoundException{
        if(!expRepository.existsById(id))
            throw new ResourceNotFoundException("No existe esa id");
        expRepository.deleteById(id);
    }
    public List<Experience> getAllExp(){
        return expRepository.findAll();
    }
    public Optional<Experience> getByCompany(String company){
        return expRepository.findByCompany(company);
    }
    public Optional<Experience>getById(int id){
        return expRepository.findById(id);
    }
    public boolean existsById(int id){
        return expRepository.existsById(id);
    }
    public boolean existsCompany(String company){
        return expRepository.existsByCompany(company);
    }
    public Experience updateExperience(int id, Experience experience) throws ResourceNotFoundException, AttributeException{
        Experience exp=expRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("No existe una experiencia con esa id"));
        if(expRepository.existsByCompany(experience.getCompany()) &&
                expRepository.findByCompany(experience.getCompany()).get().getId_exp()!=id)
            throw new AttributeException("Ya existe esa experiencia");
        if(experience.getCompany().isBlank()|| experience.getCompany().isEmpty()
                       || experience.getProject().isBlank() || experience.getProject().isEmpty())
            throw new AttributeException("Los campos Proyecto y Empresa no deben estar vacios");
        exp.setCompany(experience.getCompany());
        exp.setDetails(experience.getDetails());
        exp.setProject(experience.getProject());
        exp.setProjectDescription(experience.getProjectDescription());
        return expRepository.save(exp);
    } 
}
