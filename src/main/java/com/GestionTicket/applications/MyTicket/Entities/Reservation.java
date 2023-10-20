package com.GestionTicket.applications.MyTicket.Entities;

import jakarta.persistence.*;

import java.util.Date;
@Entity
@Table(name = "reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Date date_reser;

    @ManyToOne
    @JoinColumn(name = "billet_id")
    private Billet billet;

    @ManyToOne
    @JoinColumn(name = "passager_id")
    private Passager passagers;

    public Reservation() {
    }

    public Reservation(Date date_reser, Billet billet, Passager passagers) {
        this.date_reser = date_reser;
        this.billet = billet;
        this.passagers = passagers;
    }

    public Reservation(Long id, Date date_reser, Billet billet, Passager passagers) {
        this.id = id;
        this.date_reser = date_reser;
        this.billet = billet;
        this.passagers = passagers;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDate_reser() {
        return date_reser;
    }

    public void setDate_reser(Date date_reser) {
        this.date_reser = date_reser;
    }

    public Billet getBillet() {
        return billet;
    }

    public void setBillet(Billet billet) {
        this.billet = billet;
    }

    public Passager getPassagers() {
        return passagers;
    }

    public void setPassagers(Passager passagers) {
        this.passagers = passagers;
    }
}
