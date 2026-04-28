package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Igra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IgraRepository extends JpaRepository<Igra, Long> {
    List<Igra> findByAktivnaTrue(); // za Ukupan broj dostupnih igrica na pocetnoj stranici
    List<Igra> findByImeContainingIgnoreCase(String ime);   // pretraga po nazivu
    List<Igra> findByKategorijaId(Long kategorijaId);       // filter po kategoriji

    // sortiranje igara po prosecnim ocenama
    @Query("SELECT i FROM Igra i LEFT JOIN Recenzija r ON r.igra.id = i.id " +
            "GROUP BY i.id ORDER BY AVG(r.ocena) DESC")
    List<Igra> findAllOrderByProsecnaOcenaDesc();

    // provera da li igra vec postoji u bazi prilikom automatskog unoosenja
    boolean existsByIme(String ime);
}