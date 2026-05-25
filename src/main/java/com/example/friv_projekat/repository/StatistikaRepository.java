package com.example.friv_projekat.repository;

import com.example.friv_projekat.model.Statistika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface StatistikaRepository extends JpaRepository<Statistika, Long> {
    // nalazi sesiju igre korisnika koja je u toku da bi upisali kranjnje vreme kad izadje
    Optional<Statistika>
    findTopByKorisnikIdAndIgraIdAndKrajnjeVremeIsNullOrderByPocetnoVremeDesc(
            Long korisnikId,
            Long igraId
    );

    // 2.5 statistika igranja
    // ukupno vreme igranja
    Long sumTrajanjeUSekundamaByKorisnikId(@Param("korisnikId") Long korisnikId);

    // najigranije igrice
    @Query("""
        SELECT s.igra, SUM(s.trajanjeUSekundama)
        FROM Statistika s
        WHERE s.korisnik.id = :korisnikId
        GROUP BY s.igra
    """)
    Page<Object[]> findNajigranijeIgriceKorisnika(
            @Param("korisnikId") Long korisnikId,
            Pageable pageable
    );

    // broj pokretanja igrice za jednog korisnika
    long countByKorisnikId(Long korisnikId);

    // ukupno igranje po kategorijama, vraca page niz[ime kategorije, sumirano vreme]
    @Query("""
        SELECT s.igra.kategorija.ime, SUM(s.trajanjeUSekundama) as ukupnoVreme
        FROM Statistika s
        WHERE s.korisnik.id = :korisnikId
        GROUP BY s.igra.kategorija.ime
    """)
    Page<Object[]> findVremePoKategorijama(
            @Param("korisnikId") Long korisnikId,
            Pageable pageable
    );

    // 3.5 monitoring
    // sve sesije igranja, povlaci igru i korisnika jer u statdto ima getime
    @EntityGraph(attributePaths = {"korisnik", "igra"})
    @Query("SELECT s FROM Statistika s")
    Page<Statistika> findAllUcitajSveSesije(Pageable pageable);

    // najigranije igrice u sistemu
    // Statistika igranja po igrama
    @Query("""
        SELECT s.igra, SUM(s.trajanjeUSekundama) as ukupnoVreme
        FROM Statistika s
        GROUP BY s.igra
    """)
    Page<Object[]> findStatistikaPoIgrama(Pageable pageable);

    // 3.6 Dashboard
    // Najigranije igrice u poslednjih 30 dana
    @Query("""
        SELECT s.igra, SUM(s.trajanjeUSekundama) as ukupnoVreme
        FROM Statistika s
        WHERE s.pocetnoVreme >= :datum
        GROUP BY s.igra
    """)
    Page<Object[]> findNajigranijeUPoslednjih30Dana(
            @Param("datum") LocalDateTime datum,
            Pageable pageable
    );

    // najaktivniji korisnici
    @Query("""
        SELECT s.korisnik, SUM(s.trajanjeUSekundama) as ukupnoVreme
        FROM Statistika s
        GROUP BY s.korisnik
    """)
    Page<Object[]> findNajaktivnijiKorisnici(Pageable pageable);

    // Broj aktivnih korisnika (aktivnost u poslednjih 30 dana)
    long countDistinctKorisnikIdByPocetnoVremeGreaterThanEqual(@Param("pre30Dana") LocalDateTime datum);

    // 3.3 Upravljanje korisnicima
    //pregledati statistiku igranja korisnika
    @EntityGraph(attributePaths = {"igra"}) // povalci igru jer je fetch lazy
    Page<Statistika> findByKorisnikIdOrderByPocetnoVremeDesc(Long korisnikId, Pageable pageable);
}