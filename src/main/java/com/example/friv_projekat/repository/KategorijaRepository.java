package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Kategorija;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KategorijaRepository extends JpaRepository<Kategorija, Long> {
    // provera da li postoji vec kategorija sa istim imenom kada admin dodaje novu
    boolean existsByIme(String ime);
}