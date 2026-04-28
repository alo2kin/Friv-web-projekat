package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Recenzija;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
    List<Recenzija> findByIgraId(Long igraId);  // za komentare ispod igre
}