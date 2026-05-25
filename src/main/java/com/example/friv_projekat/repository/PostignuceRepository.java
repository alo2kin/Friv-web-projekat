package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Postignuce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostignuceRepository extends JpaRepository<Postignuce, Long> {
    List<Postignuce> findByKorisnikId(Long korisnikId); // lista postignuca korisniku

    boolean existsByKorisnikIdAndIgraIdAndNaziv(Long korisnikId, Long igraId, String naziv);
}