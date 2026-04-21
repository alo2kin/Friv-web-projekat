package com.example.friv_projekat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "igre")
public class Igra {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ime;

    @Column(columnDefinition = "TEXT")
    private String opis;

    @Column
    private String URL;

    private String thumbnailPutanja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "kategorija_id")
    private Kategorija kategorija;
    
    @Column(nullable = false)
    private LocalDateTime datumDodavanja = LocalDateTime.now();

    @Column(nullable = false)
    private boolean aktivna = true;

    public Igra() {}

    public Igra(String ime, String opis, String URL, String thumbnailPutanja, Kategorija kategorija, boolean aktivna) {
        this.ime = ime;
        this.opis = opis;
        this.URL = URL;
        this.thumbnailPutanja = thumbnailPutanja;
        this.kategorija = kategorija;
        this.aktivna = aktivna;
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

    public String getURL() {
        return URL;
    }

    public void setURL(String URL) {
        this.URL = URL;
    }

    public String getThumbnailPutanja() {
        return thumbnailPutanja;
    }

    public void setThumbnailPutanja(String thumbnailPutanja) {
        this.thumbnailPutanja = thumbnailPutanja;
    }

    public Kategorija getKategorija() {
        return kategorija;
    }

    public void setKategorija(Kategorija kategorija) {
        this.kategorija = kategorija;
    }

    public boolean isAktivna() {
        return aktivna;
    }

    public void setAktivna(boolean aktivna) {
        this.aktivna = aktivna;
    }

    public LocalDateTime getDatumDodavanja() {
        return datumDodavanja;
    }

    public void setDatumDodavanja(LocalDateTime datumDodavanja) {
        this.datumDodavanja = datumDodavanja;
    }
}
