package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Statistika;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface StatistikaRepository extends JpaRepository<Statistika, Long> {
    // nalazi sesiju igre korisnika koja je u toku da bi upisali kranjnje vreme kad izadje
    Optional<Statistika> findTopByKorisnikIdAndIgraIdAndKrajnjeVremeIsNullOrderByPocetnoVremeDesc(Long korisnikId, Long igraId);

    // 2.5 statistika igranja
    // ukupno vreme igranja
    @Query("SELECT SUM(s.trajanjeUSekundama) FROM Statistika s WHERE s.korisnik.id = :korisnikId")
    Long sumUkupnoVremeIgranja(@Param("korisnikId") Long korisnikId);

    // najigranije igrice
    @Query("SELECT s.igra, SUM(s.trajanjeUSekundama) as ukupno FROM Statistika s " +
            "WHERE s.korisnik.id = :korisnikId " +
            "GROUP BY s.igra ORDER BY ukupno DESC")
    List<Object[]> findVremeIgranjaPoIgri(@Param("korisnikId") Long korisnikId);

    // broj pokretanja igrice za jednog korisnika
    long countByKorisnikId(Long korisnikId);

    // ukupno igranje po kategorijama, vraca listu nizova [ime kategorije, sumirano vreme]
    @Query("SELECT s.igra.kategorija.ime, SUM(s.trajanjeUSekundama) FROM Statistika s " +
            "WHERE s.korisnik.id = :korisnikId " +
            "GROUP BY s.igra.kategorija.ime")
    List<Object[]> sumVremePoKategorijama(@Param("korisnikId") Long korisnikId);
}