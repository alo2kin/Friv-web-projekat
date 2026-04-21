package com.example.friv_projekat.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "kategorije")
public class Kategorija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String ime;

    private String opis;

    @OneToMany(mappedBy = "kategorija", fetch = FetchType.LAZY)
    private List<Igra> igrice;

    public Kategorija(){}

    public Kategorija(String ime, String opis) {
        this.ime = ime;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIme() {
        return ime;
    }

    public void setIme(String ime) {
        this.ime = ime;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public List<Igra> getIgrice() {
        return igrice;
    }

    public void setIgrice(List<Igra> igrice) {
        this.igrice = igrice;
    }
}
