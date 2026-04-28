package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Igra;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IgraRepository extends JpaRepository<Igra, Long> {
    List<Igra> findByAktivnaTrue(); // za Ukupan broj dostupnih igrica na pocetnoj stranici
}