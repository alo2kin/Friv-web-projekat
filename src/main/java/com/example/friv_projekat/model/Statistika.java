package com.example.friv_projekat.model;

import jakarta.persistence.*;

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
    private LocalDateTime pocetnoVreme = LocalDateTime.now();

    private LocalDateTime krajnjeVreme;

    public Statistika() {}

    public Statistika(Korisnik korisnik, Igra igra) {
        this.korisnik = korisnik;
        this.igra = igra;
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

    public long getTrajanjeUSekundama() {
        if (pocetnoVreme == null || krajnjeVreme == null) {
            return 0;
        }
        return java.time.Duration.between(pocetnoVreme, krajnjeVreme).getSeconds();
    }

}
