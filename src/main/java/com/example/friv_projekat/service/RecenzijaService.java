package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.NovaRecenzijaDTO;
import com.example.friv_projekat.model.Igra;
import com.example.friv_projekat.model.Korisnik;
import com.example.friv_projekat.model.Recenzija;
import com.example.friv_projekat.repository.IgraRepository;
import com.example.friv_projekat.repository.KorisnikRepository;
import com.example.friv_projekat.repository.RecenzijaRepository;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class RecenzijaService {

    private final RecenzijaRepository recenzijaRepository;
    private final IgraRepository igraRepository;
    private final KorisnikRepository korisnikRepository;

    public RecenzijaService(RecenzijaRepository recenzijaRepository, IgraRepository igraRepository, KorisnikRepository korisnikRepository) {
        this.recenzijaRepository = recenzijaRepository;
        this.igraRepository = igraRepository;
        this.korisnikRepository = korisnikRepository;
    }

    @Transactional
    public void ostaviRecenziju(Long korisnikId, Long igraId, @NonNull NovaRecenzijaDTO dto) {
        Korisnik korisnik = korisnikRepository.findById(korisnikId)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronađen"));
        Igra igra = igraRepository.findById(igraId)
                .orElseThrow(() -> new RuntimeException("Igra nije pronađena"));

        Recenzija novaRecenzija = new Recenzija(
                korisnik, igra, dto.ocena(), dto.komentar()
        );

        recenzijaRepository.save(novaRecenzija);

        double noviProsek = recenzijaRepository.getAverageOcenaByIgraId(igraId);

        // azuriramo prosek
        igra.setProsecnaOcena(noviProsek);
        igraRepository.save(igra);
    }
}
