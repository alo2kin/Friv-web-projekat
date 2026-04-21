package com.example.friv_projekat.model;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "statistika")
public class Statistika {
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
    private LocalDateTime pocetnoVreme;

    private LocalDateTime krajnjeVreme;

    public Statistika() {}

    public Statistika(Korisnik korisnik, LocalDateTime pocetnoVreme, Igra igra, LocalDateTime krajnjeVreme) {
        this.korisnik = korisnik;
        this.pocetnoVreme = pocetnoVreme;
        this.igra = igra;
        this.krajnjeVreme = krajnjeVreme;
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

    public LocalDateTime getPocetnoVreme() {
        return pocetnoVreme;
    }

    public void setPocetnoVreme(LocalDateTime pocetnoVreme) {
        this.pocetnoVreme = pocetnoVreme;
    }

    public LocalDateTime getKrajnjeVreme() {
        return krajnjeVreme;
    }

    public void setKrajnjeVreme(LocalDateTime krajnjeVreme) {
        this.krajnjeVreme = krajnjeVreme;
    }
}
