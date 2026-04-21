package com.example.friv_projekat.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "igre")
public class Igra {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long idIgre;

    @Column
    private String imeIgre;

    @Column(columnDefinition = "TEXT")
    private String opisIgre;

    @Column
    private String igraURL;

    private String thumbnailPutanja;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Kategorija_id")
    private Kategorija kategorija;

    @Column(nullable = false)
    private LocalDateTime datumDodavanja;

    @Column(nullable = false)
    private boolean aktivna = true;

    public Igra() {}

    public Igra(String imeIgre, String opisIgre, String igraURL, String thumbnailPutanja, Kategorija kategorija, LocalDateTime datumDodavanja, boolean aktivna) {
        this.imeIgre = imeIgre;
        this.opisIgre = opisIgre;
        this.igraURL = igraURL;
        this.thumbnailPutanja = thumbnailPutanja;
        this.kategorija = kategorija;
        this.datumDodavanja = datumDodavanja;
        this.aktivna = aktivna;
    }

    public Long getIdIgre() {
        return idIgre;
    }

    public void setIdIgre(Long idIgre) {
        this.idIgre = idIgre;
    }

    public String getImeIgre() {
        return imeIgre;
    }

    public void setImeIgre(String imeIgre) {
        this.imeIgre = imeIgre;
    }

    public String getOpisIgre() {
        return opisIgre;
    }

    public void setOpisIgre(String opisIgre) {
        this.opisIgre = opisIgre;
    }

    public String getIgraURL() {
        return igraURL;
    }

    public void setIgraURL(String igraURL) {
        this.igraURL = igraURL;
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
