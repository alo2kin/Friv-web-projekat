package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Igra;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IgraRepository extends JpaRepository<Igra, Long> {
    // za Ukupan broj dostupnih igrica na pocetnoj stranici
    long countByAktivnaTrue();

    // Pregledati listu igrica dostupnih u sistemu
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

    // provera da li igra vec postoji u bazi prilikom automatskog unoosenja
    boolean existsByIme(String ime);

    // Provera da li postoji ijedna igra sa ovom kategorijom
    boolean existsByKategorija_Id(Long kategorijaId);
}