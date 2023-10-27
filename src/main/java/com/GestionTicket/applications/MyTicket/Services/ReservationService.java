package com.GestionTicket.applications.MyTicket.Services;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Entities.Reservation;
import com.GestionTicket.applications.MyTicket.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    @Autowired
    ReservationRepository reservationRepository;
    public void save(Reservation reservation){
        reservationRepository.save(reservation);
    }

    public List<Reservation> TousResev(){
        return reservationRepository.findAll();
    }
}
