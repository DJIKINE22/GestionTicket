package com.GestionTicket.applications.MyTicket.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "compagnie")
public class Compagnie {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nom;
    private String lieu;

    public Compagnie() {
    }

    public Compagnie(String nom, String lieu) {
        this.nom = nom;
        this.lieu = lieu;
    }

    public Compagnie(Long id, String nom, String lieu) {
        this.id = id;
        this.nom = nom;
        this.lieu = lieu;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getLieu() {
        return lieu;
    }

    public void setLieu(String lieu) {
        this.lieu = lieu;
    }
}
