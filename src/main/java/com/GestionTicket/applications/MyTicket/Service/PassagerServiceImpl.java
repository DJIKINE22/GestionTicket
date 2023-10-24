package com.GestionTicket.applications.MyTicket.Service;

import com.GestionTicket.applications.MyTicket.Entities.User;
import com.GestionTicket.applications.MyTicket.Entity.Passager;
import com.GestionTicket.applications.MyTicket.Reposirtory.PassagerRepository;
import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class PassagerServiceImpl implements PassagerService {
    @Autowired
    private PassagerRepository passagerRepository;

    @Override
    public List<Passager> getAllPassager() {
        return passagerRepository.findAll();
    }

    @Override
    public void savePassager(Passager passager) {
        this.passagerRepository.save(passager);
    }

    @Override
    public Passager getPassagerById(long id) {
        Optional< Passager > optional = passagerRepository.findById(id);
        Passager passager = null;
        if (optional.isPresent()) {
            passager = optional.get();
        } else {
            throw new RuntimeException(" Pain not found for id :: " + id);
        }
        return passager;
    }

    @Override
    public void deletePassagerById(long id) {
        this.passagerRepository.deleteById(id);
    }

    public Page<Passager> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();
        Pageable pageable = PageRequest.of(pageNo -1,pageSize,sort);
        return this.passagerRepository.findAll(pageable);
    }

}

