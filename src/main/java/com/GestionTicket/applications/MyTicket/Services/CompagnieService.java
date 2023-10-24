package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import com.GestionTicket.applications.MyTicket.Repository.CompagnieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompagnieService {
    CompagnieRepository compagnieRepository;
    @Autowired
    public CompagnieService(CompagnieRepository compagnieRepository) {
        this.compagnieRepository = compagnieRepository;
    }

    public  void  create(Compagnie compagnie){

        compagnieRepository.save(compagnie);
    }
    public List<Compagnie> allCompa(){

        return  this.compagnieRepository.findAll();
    }

    public Compagnie getCompaById(Long id) {
        Optional< Compagnie > optional = compagnieRepository.findById(id);
        Compagnie compagnie = null;
        if (optional.isPresent()) {
            compagnie = optional.get();
        } else {
            throw new RuntimeException(" Compagnie not found for id :: " + id);
        }
        return compagnie;
    }
    public void deleteCompaById(Long id) {
        compagnieRepository.deleteById( id);
    }


    public Page<Compagnie> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1,pageSize,sort);
        return this.compagnieRepository.findAll(pageable);
    }
}
