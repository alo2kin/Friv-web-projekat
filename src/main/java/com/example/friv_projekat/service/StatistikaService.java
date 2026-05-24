package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.IgraStatistikaDTO;
import com.example.friv_projekat.dto.KategorijaStatistikaDTO;
import com.example.friv_projekat.model.Statistika;
import com.example.friv_projekat.repository.StatistikaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class StatistikaService {
//    Korisnik ima pristup ličnoj statistici igranja.
//    Prikazati:
//● Ukupno vreme igranja po kategorijama
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
}
