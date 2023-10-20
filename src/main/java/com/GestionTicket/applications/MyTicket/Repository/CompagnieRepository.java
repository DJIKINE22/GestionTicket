package com.GestionTicket.applications.MyTicket.Repository;

import com.GestionTicket.applications.MyTicket.Entities.Compagnie;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface CompagnieRepository extends JpaRepository<Compagnie,Long> {
    Page<Compagnie> findAll(Pageable pageable);
    @Query("SELECT c FROM Compagnie c where c.nom like %?1%")
    public List<Compagnie> findAll(String keyword);
}
