package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.*;
import com.example.friv_projekat.model.Statistika;
import com.example.friv_projekat.repository.StatistikaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StatistikaService {
    private final StatistikaRepository statistikaRepository;

    public StatistikaService(StatistikaRepository statistikaRepository) {
        this.statistikaRepository = statistikaRepository;
    }

    public void zavrsiSesijuIgre(Long korisnikId, Long igraId) {
        Statistika statistika = statistikaRepository
                .findTopByKorisnikIdAndIgraIdAndKrajnjeVremeIsNullOrderByPocetnoVremeDesc(korisnikId, igraId)
                .orElseThrow(() -> new RuntimeException("Sesija nije pronadjena."));

        statistika.setKrajnjeVreme(LocalDateTime.now());
    }

    public Long ukupnoVremeIgranjaZaKorisnika(Long korisnikId) {
        return statistikaRepository.sumUkupnoVremeIgranja(korisnikId);
    }

    public Page<IgraStatistikaDTO> najigranijeIgriceZaKorisnika(Long korisnikId, Pageable pageable) {
         return statistikaRepository.findNajigranijeIgriceKorisnika(korisnikId, pageable);
    }

    public long brojPokretanjaIgriZaKorisnika(Long korisnikId) {
        return statistikaRepository.countByKorisnikId(korisnikId);
    }

    public Page<KategorijaStatistikaDTO> ukupnoVremeIgranjaPoKategorijamaZaKorisnika(Long id, Pageable pageable) {
        return statistikaRepository.findVremePoKategorijama(id, pageable);
    }

    // 3.3 Upravljanje korisnicima
    //pregledati statistiku igranja korisnika
    public Page<StatistikaKorisnikaDTO> getStatistikaIgranjaKorisnika(Long korisnikId, Pageable pageable) {
        return statistikaRepository
                .findByKorisnikIdOrderByPocetnoVremeDesc(korisnikId, pageable)
                .map(StatistikaKorisnikaDTO::konvertuj);
    }

    //3.5. Monitoring
    // Sve sesije igranja
    public Page<StatistikaDTO> getSveSesijeIgranja(Pageable pageable) {
        return statistikaRepository.findAllByOrderByPocetnoVremeDesc(pageable)
                .map(StatistikaDTO::konvertuj);
    }


    // Statistiku igranja po igrama
    public Page<IgraStatistikaDTO> getStatistikaPoIgrama(Pageable pageable) {
        return statistikaRepository.findStatistikaPoIgrama(pageable);
    }


    // Najigranije igrice u sistemu
    public Page<IgraStatistikaDTO> getNajigranijeIgriceUSistemu(Pageable pageable) {
        return statistikaRepository.findNajigranijeIgriceUSistemu(pageable);
    }


    //3.6. Dashboard
    // Broj aktivnih korisnika (aktivnost u poslednjih 30 dana)
    public long getBrojAktivnihUPoslesnjih30Dana() {
        return statistikaRepository.countAktivniKorisniciUPoslednjih30Dana(LocalDateTime.now().minusDays(30));
    }

    // Najigranije igrice u poslednjih 30 dana
    public Page<IgraStatistikaDTO> getNajigranijeIgreUPoslednjih30Dana(Pageable pageable) {
        return statistikaRepository.findNajigranijeUPoslednjih30Dana(LocalDateTime.now().minusDays(30), pageable);
    }

    // Najaktivnije korisnike
    public Page<KorisnikStatistikaDTO> getNajaktivnijiKorisnici(Pageable pageable) {
        return statistikaRepository.findNajaktivnijiKorisnici(pageable);
    }
}
