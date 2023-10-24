package com.GestionTicket.applications.MyTicket.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "nour")
public class Nour extends Compagnie{
    int id;
    String lieu;
}
