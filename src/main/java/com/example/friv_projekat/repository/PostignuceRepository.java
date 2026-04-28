package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Postignuce;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostignuceRepository extends JpaRepository<Postignuce, Long> {
    List<Postignuce> findByKorisnikId(Long korisnikId);
}