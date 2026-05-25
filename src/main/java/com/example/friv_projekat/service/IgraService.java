package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.*;
import com.example.friv_projekat.model.Igra;
import com.example.friv_projekat.model.Kategorija;
import com.example.friv_projekat.repository.IgraRepository;
import com.example.friv_projekat.repository.KategorijaRepository;
import com.example.friv_projekat.repository.RecenzijaRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IgraService {
    private final IgraRepository igraRepository;
    private final RecenzijaRepository recenzijaRepository;
    private final KategorijaRepository kategorijaRepository;

    @Autowired
    public IgraService(IgraRepository igraRepository, RecenzijaRepository recenzijaRepository, KategorijaRepository kategorijaRepository) {
        this.igraRepository = igraRepository;
        this.recenzijaRepository = recenzijaRepository;
        this.kategorijaRepository = kategorijaRepository;
    }

    public long getUkupanBrojDostupnihIgara() {
        return igraRepository.countByAktivnaTrue();
    }

    public Page<IgraPregledDTO> pretragaPoNazivu(String ime, Pageable pageable) {
        return igraRepository.findByImeContainingIgnoreCase(ime, pageable)
                .map(IgraPregledDTO::konvertujUPregledDTO);
    }

    public Page<IgraPregledDTO> filterPoKategoriji(Long kategorijaId, Pageable pageable) {
        return igraRepository.findByKategorijaId(kategorijaId, pageable)
                .map(IgraPregledDTO::konvertujUPregledDTO);
    }

    public Page<IgraPregledDTO> sveAktivneIgra(Pageable pageable) {
        return igraRepository.findByAktivnaTrue(pageable)
                .map(IgraPregledDTO::konvertujUPregledDTO);
    }

    // sortiranje igara po prosecnim ocenama
    // u controller @PageableDefault(size = 10, sort = "prosecnaOcena", direction = Sort.Direction.DESC) Pageable pageable
    public Page<IgraPregledDTO> sortiraneIgrePoOcenama(Pageable pageable) {
        return igraRepository.findByAktivnaTrue(pageable)
                .map(IgraPregledDTO::konvertujUPregledDTO);
    }

    public IgraStranicaDTO getIgraById(Long id, Pageable pageable) {
        Igra igra = igraRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Igra nije pronadjena!"));

        Page<RecenzijaDTO> komentari =
                recenzijaRepository.findByIgraId(id, pageable)
                        .map(RecenzijaDTO::kovertujUDTO);

        return IgraStranicaDTO.konvertujUStranicaDTO(igra, komentari);
    }

    public IgraAdminDTO izmenaIgre(Long id, @NonNull IzmenaIgreDTO dto) {
        Igra igra = igraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Igra nije pronadjena"));

        Kategorija kategorija = kategorijaRepository.findById(dto.kategorijaId())
                .orElseThrow(() -> new RuntimeException("Kategorija nije pronadjena."));

        igra.setIme(dto.ime());
        igra.setOpis(dto.opis());
        igra.setKategorija(kategorija);
        igra.setThumbnailPutanja(dto.thumbnailPutanja());

        return IgraAdminDTO.konvertujUAdminDTO(igra);
    }

    public void izmenaAktivnostiIgre(Long id, boolean b) {
        Igra igra = igraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Igra nije pronadjena!"));

        igra.setAktivna(b);
        igraRepository.save(igra);
    }

    public IgraAdminDTO dodavanjeNoveIgre(@NonNull NovaIgraDTO dto) {
        Kategorija kategorija = kategorijaRepository.findById(dto.kategorijaId())
                .orElseThrow(() -> new RuntimeException("Kategorija nije pronadjena."));

        Igra igra = new Igra(
                dto.ime(),
                dto.opis(),
                dto.iframeURL(),
                dto.thumbnailPutanja(),
                kategorija
        );

        // nije bila jos nijedna recenzija
        igra.setProsecnaOcena(0.0);

        return IgraAdminDTO.konvertujUAdminDTO(igra);
    }

    //3.6 Ukupan broj igrica
    public long getUkupanBrojSvihIgara() {
        return igraRepository.count();
    }
}
