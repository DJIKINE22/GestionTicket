package com.GestionTicket.applications.MyTicket.Entities;

import com.GestionTicket.applications.MyTicket.Entity.Passager;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date dateDepart;
    private String destination;

    @ManyToOne
    @JoinColumn(name = "compagnie_id")
    private Compagnie compagnie;
    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passagers;

    public Billet() {
    }

    public Billet(Date dateDepart, String destination, Compagnie compagnie, Passager passager) {
        this.dateDepart = dateDepart;
        this.destination = destination;
        this.compagnie = compagnie;
        this.passagers = passagers;
    }

    public Billet(Long id, Date dateDepart, String destination, Compagnie compagnie, Passager passagers) {
        this.id = id;
        this.dateDepart = dateDepart;
        this.destination = destination;
        this.compagnie = compagnie;
        this.passagers = passagers;
    }
}
