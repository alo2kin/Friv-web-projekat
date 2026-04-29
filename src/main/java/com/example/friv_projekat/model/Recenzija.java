package com.example.friv_projekat.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "recenzije")
public class Recenzija {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int ocena; // u service ogranicimo na 1-5

    @Column(columnDefinition = "TEXT")
    private String komentar;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "korisnik_id", nullable = false)
    private Korisnik korisnik;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "igra_id", nullable = false)
    private Igra igra;

    @Column(nullable = false)
    private LocalDateTime datumObjave = LocalDateTime.now();

    public Recenzija() {}

    public Recenzija(Korisnik korisnik, Igra igra, int ocena, String komentar) {
        this.korisnik = korisnik;
        this.igra = igra;
        this.ocena = ocena;
        this.komentar = komentar;
    }

    public LocalDateTime getDatumObjave() {
        return datumObjave;
    }

    public void setDatumObjave(LocalDateTime datumObjave) {
        this.datumObjave = datumObjave;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Igra getIgra() {
        return igra;
    }

    public void setIgra(Igra igra) {
        this.igra = igra;
    }

    public String getKomentar() {
        return komentar;
    }

    public void setKomentar(String komentar) {
        this.komentar = komentar;
    }

    public Korisnik getKorisnik() {
        return korisnik;
    }

    public void setKorisnik(Korisnik korisnik) {
        this.korisnik = korisnik;
    }

    public int getOcena() {
        return ocena;
    }

    public void setOcena(int ocena) {
        this.ocena = ocena;
    }
}
