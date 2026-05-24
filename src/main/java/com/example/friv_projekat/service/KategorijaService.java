package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.NovaKategorijaDTO;
import com.example.friv_projekat.model.Kategorija;
import com.example.friv_projekat.repository.IgraRepository;
import com.example.friv_projekat.repository.KategorijaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

@Service
public class KategorijaService {
    private final KategorijaRepository kategorijaRepository;
    private final IgraRepository igraRepository;

    public KategorijaService(KategorijaRepository kategorijaRepository, IgraRepository igraRepository) {
        this.kategorijaRepository = kategorijaRepository;
        this.igraRepository = igraRepository;
    }

    public Kategorija dodavanjeNoveKategorije(@NonNull NovaKategorijaDTO dto) {
        if (kategorijaRepository.existsByIme(dto.ime())){
            throw new RuntimeException(String.format("Kategorija sa imenom: %s vec postoji.", dto.ime()));
        }

        Kategorija kategorija = new Kategorija(dto.ime(), dto.opis());
        kategorijaRepository.save(kategorija);
        return kategorija;
    }

    public void brisanjeKategorije(Long id) {
        if (igraRepository.existsByKategorija_Id(id)) {
            throw new RuntimeException("Nije moguće obrisati kategoriju jer u njoj postoje igrice! Prvo premestite igrice.");
        }
        kategorijaRepository.deleteById(id);
    }

    public Kategorija izmenaKategorije(Long id, @NonNull NovaKategorijaDTO dto) {
        Kategorija kategorija = kategorijaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Kategorija nije pronadjena."));

        kategorija.setIme(dto.ime());
        kategorija.setOpis(dto.opis());
        return kategorija;
    }
}
