package com.example.friv_projekat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "postignuca")
public class Postignuca {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "igra_id", nullable = false)
    private Igra igra;

    @Column(nullable = false)
    private String naziv;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column(nullable = false)
    private LocalDateTime vremeOstvarenja = LocalDateTime.now();

    public Postignuca() {}

    public Postignuca(Korisnik korisnik, Igra igra, String naziv, String opis) {
        this.korisnik = korisnik;
        this.igra = igra;
        this.naziv = naziv;
        this.opis = opis;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public Igra getIgra() {
        return igra;
    }

    public void setIgra(Igra igra) {
        this.igra = igra;
    }

    public String getNaziv() {
        return naziv;
    }

    public void setNaziv(String naziv) {
        this.naziv = naziv;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public LocalDateTime getVremeOstvarenja() {
        return vremeOstvarenja;
    }

    public void setVremeOstvarenja(LocalDateTime vremeOstvarenja) {
        this.vremeOstvarenja = vremeOstvarenja;
    }
}
