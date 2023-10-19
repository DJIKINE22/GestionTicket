package com.GestionTicket.applications.MyTicket.Service;

import com.GestionTicket.applications.MyTicket.Entity.Passager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PassagerService {
    List<Passager> getAllPassager();
    void savePassager(Passager passager);
    Passager getPassagerById(long id);
    void deletePassagerById(long id);
}
