package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Recenzija;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RecenzijaRepository extends JpaRepository<Recenzija, Long> {
    // za komentare ispod igre
    Page<Recenzija> findByIgraId(
            Long igraId,
            Pageable pageable
    );

    @Query("SELECT AVG(r.ocena) FROM Recenzija r WHERE r.igra.id = :igraId")
    Double getAverageOcenaByIgraId(@Param("igraId") Long igraId);
}