package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Korisnik;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface KorisnikRepository extends JpaRepository<Korisnik, Long> {
    Optional<Korisnik> findByEmail(String email);   // za login
    boolean existsByEmail(String email);            // za registraciju da li vec postoji

    // admin moze da pretrazi korisnika po imenu i prezimenu kad (od)blokira
    List<Korisnik> findByImeContainingIgnoreCaseOrPrezimeContainingIgnoreCase(String ime, String prezime);
}