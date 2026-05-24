package com.example.friv_projekat.repository;

import com.example.friv_projekat.dto.IgraStatistikaDTO;
import com.example.friv_projekat.dto.KategorijaStatistikaDTO;
import com.example.friv_projekat.dto.KorisnikStatistikaDTO;
import com.example.friv_projekat.model.Statistika;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    @Query("""
        SELECT COALESCE(SUM(s.trajanjeUSekundama), 0)
        FROM Statistika s
        WHERE s.korisnik.id = :korisnikId
    """)
    Long sumUkupnoVremeIgranja(@Param("korisnikId") Long korisnikId);

    // najigranije igrice
    @Query("""
        SELECT new com.example.friv_projekat.dto.IgraStatistikaDTO(
            s.igra, COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        WHERE s.korisnik.id = :korisnikId
        GROUP BY s.igra
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<IgraStatistikaDTO> findNajigranijeIgriceKorisnika(
            @Param("korisnikId") Long korisnikId,
            Pageable pageable
    );

    // broj pokretanja igrice za jednog korisnika
    long countByKorisnikId(Long korisnikId);

    // ukupno igranje po kategorijama, vraca page dto[ime kategorije, sumirano vreme]
    @Query("""
        SELECT new com.example.friv_projekat.dto.KategorijaStatistikaDTO(
            s.igra.kategorija.ime,
            COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        WHERE s.korisnik.id = :korisnikId
        GROUP BY s.igra.kategorija.ime
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<KategorijaStatistikaDTO> findVremePoKategorijama(
            @Param("korisnikId") Long korisnikId,
            Pageable pageable
    );

    // 3.5 monitoring
    // sve sesije igranja
    Page<Statistika> findAllByOrderByPocetnoVremeDesc(Pageable pageable);

    // najigranije igrice u sistemu
    @Query("""
        SELECT new com.example.friv_projekat.dto.IgraStatistikaDTO(
            s.igra, COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        GROUP BY s.igra
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<IgraStatistikaDTO> findNajigranijeIgriceUSistemu(Pageable pageable);

    // Statistika igranja po igrama
    @Query("""
        SELECT new com.example.friv_projekat.dto.IgraStatistikaDTO(
            s.igra, COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        GROUP BY s.igra
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<IgraStatistikaDTO> findStatistikaPoIgrama(Pageable pageable);

    // 3.6 Dashboard
    // Najigranije igrice u poslednjih 30 dana
    @Query("""
        SELECT new com.example.friv_projekat.dto.IgraStatistikaDTO(
            s.igra, COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        WHERE s.pocetnoVreme >= :datum
        GROUP BY s.igra
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<IgraStatistikaDTO> findNajigranijeUPoslednjih30Dana(
            @Param("datum") LocalDateTime datum,
            Pageable pageable
    );

    // najaktivniji korisnici
    @Query("""
        SELECT new com.example.friv_projekat.dto.KorisnikStatistikaDTO(
            s.korisnik,
            COALESCE(SUM(s.trajanjeUSekundama), 0)
        )
        FROM Statistika s
        GROUP BY s.korisnik
        ORDER BY SUM(s.trajanjeUSekundama) DESC
    """)
    Page<KorisnikStatistikaDTO> findNajaktivnijiKorisnici(Pageable pageable);

    // Broj aktivnih korisnika (aktivnost u poslednjih 30 dana)
    @Query("""
        SELECT COUNT(DISTINCT s.korisnik.id)
        FROM Statistika s
        WHERE s.pocetnoVreme >= :pre30Dana
    """)
    long countAktivniKorisniciUPoslednjih30Dana(@Param("pre30Dana") LocalDateTime datum);

    // 3.3 Upravljanje korisnicima
    //pregledati statistiku igranja korisnika
    Page<Statistika> findByKorisnikIdOrderByPocetnoVremeDesc(Long korisnikId, Pageable pageable);
}