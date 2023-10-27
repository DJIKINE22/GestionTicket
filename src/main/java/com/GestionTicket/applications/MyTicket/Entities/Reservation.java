package com.GestionTicket.applications.MyTicket.Entities;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date date_depart;
    private  String destination;
    private String heure;
    private int nbre_place;


    @ManyToOne
    @JoinColumn(name = "compagnie")
    private Compagnie compagnie;
    @ManyToOne
    @JoinColumn(name = "passager")
    private Passager passager;

    public Reservation() {
    }

    public Reservation(Date date_depart, String destination, String heure, int nbre_place, Compagnie compagnie, Passager passager) {
        this.date_depart = date_depart;
        this.destination = destination;
        this.heure = heure;
        this.nbre_place = nbre_place;
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

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getNbre_place() {
        return nbre_place;
    }

    public void setNbre_place(int nbre_place) {
        this.nbre_place = nbre_place;
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
