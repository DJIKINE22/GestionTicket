package com.GestionTicket.applications.MyTicket.Repository;

import com.GestionTicket.applications.MyTicket.Entities.Passager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PassagerRepository  extends JpaRepository<Passager, Long> {
}
