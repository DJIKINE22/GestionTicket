package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Repository.BilletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BilletService {
    BilletRepository billetRepository;
    @Autowired
    public BilletService(BilletRepository billetRepository) {
        this.billetRepository = billetRepository;
    }
    public  void  create(Billet billet){

        billetRepository.save(billet);
    }


    public Billet getBilletById(Long id) {
        Optional<Billet> optional = billetRepository.findById(id);
        Billet billet = null;
        if (optional.isPresent()) {
            billet = optional.get();
        } else {
            throw new RuntimeException(" Compagnie not found for id :: " + id);
        }
        return billet;
    }
    public void deleteBilletById(Long id) {
        billetRepository.deleteById( id);
    }
    public List<Billet> TousBillets(){
        return billetRepository.findAll();
    }


    public Page<Billet> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1,pageSize,sort);
        return this.billetRepository.findAll(pageable);
    }
}
