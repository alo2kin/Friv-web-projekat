package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Igra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface IgraRepository extends JpaRepository<Igra, Long> {
    // za Ukupan broj dostupnih igrica na pocetnoj stranici
    Page<Igra> findByAktivnaTrue(Pageable pageable);

    // pretraga po nazivu
    Page<Igra> findByImeContainingIgnoreCase(
            String ime,
            Pageable pageable
    );

    // filter po kategoriji
    Page<Igra> findByKategorijaId(
            Long kategorijaId,
            Pageable pageable
    );

    // sortiranje igara po prosecnim ocenama
    @Query("""
        SELECT i
        FROM Igra i
        LEFT JOIN Recenzija r ON r.igra.id = i.id
        GROUP BY i
        ORDER BY COALESCE(AVG(r.ocena), 0) DESC
    """)
    Page<Igra> findAllOrderByProsecnaOcenaDesc(Pageable pageable);

    // provera da li igra vec postoji u bazi prilikom automatskog unoosenja
    boolean existsByIme(String ime);
}