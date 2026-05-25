package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.OtkljucajPostignuceDTO;
import com.example.friv_projekat.model.Igra;
import com.example.friv_projekat.model.Korisnik;
import com.example.friv_projekat.model.Postignuce;
import com.example.friv_projekat.repository.IgraRepository;
import com.example.friv_projekat.repository.KorisnikRepository;
import com.example.friv_projekat.repository.PostignuceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PostignuceServis {

    private final PostignuceRepository postignuceRepository;
    private final KorisnikRepository korisnikRepository;
    private final IgraRepository igraRepository;

    @Autowired
    public PostignuceServis(
            PostignuceRepository postignuceRepository,
            KorisnikRepository korisnikRepository,
            IgraRepository igraRepository
    ) {
        this.postignuceRepository = postignuceRepository;
        this.korisnikRepository = korisnikRepository;
        this.igraRepository = igraRepository;
    }

    @Transactional
    public void otkljucajPostignuce(Long korisnikId, OtkljucajPostignuceDTO dto) {
        boolean vecPostoji = postignuceRepository.existsByKorisnikIdAndIgraIdAndNaziv(
                korisnikId, dto.igraId(), dto.nazivPostignuca()
        );

        if (!vecPostoji) {
            Postignuce postignuce = new Postignuce();

            Korisnik korisnik = korisnikRepository.getReferenceById(korisnikId);
            Igra igra = igraRepository.getReferenceById(dto.igraId());

            postignuce.setKorisnik(korisnik);
            postignuce.setIgra(igra);
            postignuce.setNaziv(dto.nazivPostignuca());
            postignuce.setOpis(dto.opisPostignuca());

            postignuceRepository.save(postignuce);
        }
    }

    public List<Postignuce> getPostignucaKorisnika(Long korisnikId) {
        return postignuceRepository.findByKorisnikId(korisnikId);
    }
}