package com.GestionTicket.applications.MyTicket.Repository;

import com.GestionTicket.applications.MyTicket.Entities.Billet;
import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BilletRepository extends JpaRepository<Billet,Long> {


    Page<Billet> findAll(Pageable pageable);
}
