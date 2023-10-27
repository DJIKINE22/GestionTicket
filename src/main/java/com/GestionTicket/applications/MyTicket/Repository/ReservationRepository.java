package com.GestionTicket.applications.MyTicket.Repository;

import com.GestionTicket.applications.MyTicket.Entities.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation,Long> {
}
