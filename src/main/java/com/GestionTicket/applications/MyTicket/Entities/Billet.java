package com.GestionTicket.applications.MyTicket.Entities;


import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


@Entity
@Table(name = "billet")
public class Billet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_depart;
    private String destination;
    @ManyToOne
    @JoinColumn(name = "compagnie")
    private Compagnie compagnie;
    @ManyToOne
    @JoinColumn(name = "passager")
    private Passager passager;


    public Billet() {
    }

    public Billet(Date date_depart, String destination, Compagnie compagnie, Passager passager) {
        this.date_depart = date_depart;
        this.destination = destination;
        this.compagnie = compagnie;
        this.passager = passager;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_depart() {
        return date_depart;
    }

    public void setDate_depart(Date date_depart) {
        this.date_depart = date_depart;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Compagnie getCompagnie() {
        return compagnie;
    }

    public void setCompagnie(Compagnie compagnie) {
        this.compagnie = compagnie;
    }

    public Passager getPassager() {
        return passager;
    }

    public void setPassager(Passager passager) {
        this.passager = passager;
    }
}
