package com.example.friv_projekat.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Igra {
    @Entity
    @Table(name = "users")
    public class User {

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Long id;

        @Column(nullable = false)
        private String ime;

        @Column(nullable = false)
        private String prezime;

        @Column(nullable = false, unique = true)
        private String email;

        @Column(nullable = false)
        private String sifra;

        @Column(nullable = false)
        private LocalDate datumRodjenja;

        @Enumerated(EnumType.STRING)
        @Column(nullable = false)
        private Role role = Role.USER;

        private String profilnaSlikaPutanja;

        @Column(nullable = false)
        private LocalDateTime DatumRegistracije = LocalDateTime.now();

        @Column(nullable = false)
        private boolean blokiran = false;

        public User() {
        }

        public User(String ime, String prezime, String email, String sifra, LocalDate datumRodjenja) {
            this.ime = ime;
            this.prezime = prezime;
            this.email = email;
            this.sifra = sifra;
            this.datumRodjenja = datumRodjenja;
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

        public String getPrezime() {
            return prezime;
        }

        public void setPrezime(String prezime) {
            this.prezime = prezime;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getSifra() {
            return sifra;
        }

        public void setSifra(String sifra) {
            this.sifra = sifra;
        }

        public LocalDate getDatumRodjenja() {
            return datumRodjenja;
        }

        public void setDatumRodjenja(LocalDate datumRodjenja) {
            this.datumRodjenja = datumRodjenja;
        }

        public String getProfilnaSlikaPutanja() {
            return profilnaSlikaPutanja;
        }

        public void setProfilnaSlikaPutanja(String profilnaSlikaPutanja) {
            this.profilnaSlikaPutanja = profilnaSlikaPutanja;
        }

        public Role getRole() {
            return role;
        }

        public void setRole(Role role) {
            this.role = role;
        }

        public LocalDateTime getDatumRegistracije() {
            return DatumRegistracije;
        }

        public void setDatumRegistracije(LocalDateTime datumRegistracije) {
            DatumRegistracije = datumRegistracije;
        }

        public boolean isBlokiran() {
            return blokiran;
        }

        public void setBlokiran(boolean blokiran) {
            this.blokiran = blokiran;
        }
    }
