package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByEmail(String email);   // za login
    boolean existsByEmail(String email);            // za registraciju da li vec postoji
}