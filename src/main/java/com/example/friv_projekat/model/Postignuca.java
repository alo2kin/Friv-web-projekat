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
    private String imePostignuca;

    @Column(columnDefinition = "TEXT")
    private String opisPostignuca;

    @Column(nullable = false)
    private LocalDateTime ostvarenTada = LocalDateTime.now();

    public Postignuca() {}

    public Postignuca(Korisnik korisnik, Igra igra, String imePostignuca, String opisPostignuca, LocalDateTime ostvarenTada) {
        this.korisnik = korisnik;
        this.igra = igra;
        this.imePostignuca = imePostignuca;
        this.opisPostignuca = opisPostignuca;
        this.ostvarenTada = ostvarenTada;
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

    public String getName() {
        return imePostignuca;
    }

    public void setName(String imePostignuca) {
        this.imePostignuca = imePostignuca;
    }

    public String getOpisPostignuca() {
        return opisPostignuca;
    }

    public void setOpisPostignuca(String opisPostignuca) {
        this.opisPostignuca = opisPostignuca;
    }

    public LocalDateTime getOstvarenTada() {
        return ostvarenTada;
    }

    public void setOstvarenTada(LocalDateTime ostvarenTada) {
        this.ostvarenTada = ostvarenTada;
    }
}
