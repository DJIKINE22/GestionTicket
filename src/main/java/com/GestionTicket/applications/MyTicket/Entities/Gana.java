package com.GestionTicket.applications.MyTicket.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "gana")
public class Gana extends Compagnie{
    int id;
    String nom;
}
