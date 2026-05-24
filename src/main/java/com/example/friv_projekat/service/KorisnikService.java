package com.example.friv_projekat.service;

import com.example.friv_projekat.dto.IzmenaProfilaDTO;
import com.example.friv_projekat.dto.KorisnikDTO;
import com.example.friv_projekat.dto.PrijavaDTO;
import com.example.friv_projekat.dto.RegistracijaDTO;
import com.example.friv_projekat.model.Korisnik;
import com.example.friv_projekat.repository.KorisnikRepository;
import org.jspecify.annotations.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {
    private final KorisnikRepository korisnikRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public KorisnikService(KorisnikRepository korisnikRepository, BCryptPasswordEncoder passwordEncoder) {
        this.korisnikRepository = korisnikRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Ukupan broj registrovanih korisnika
    public long getUkupanBrojKorisnika() {
        return korisnikRepository.count();
    }

    public KorisnikDTO registracija(@NonNull RegistracijaDTO dto) {
        if (korisnikRepository.existsByEmail(dto.email())) {
            throw new RuntimeException("Email adresa vec u upotrebi.");
        }

        String hesiranaSifra = passwordEncoder.encode(dto.sifra());
        Korisnik korisnik = new Korisnik(
                dto.ime(),
                dto.prezime(),
                dto.email(),
                hesiranaSifra,
                dto.datumRodjenja()
        );

        Korisnik sacuvaniKorisnik = korisnikRepository.save(korisnik);

        return KorisnikDTO.konvertujUDTO(sacuvaniKorisnik);
    }

    public KorisnikDTO prijava(@NonNull PrijavaDTO dto) {
        Korisnik korisnik = korisnikRepository.findByEmail(dto.email())
                .orElseThrow(() -> new RuntimeException("Email nije u upotrebi"));

        if (korisnik.isBlokiran()) {
            throw new RuntimeException("Blokiran od strane admina");
        }

        if (passwordEncoder.matches(dto.sifra(), korisnik.getSifra())) {
            throw new RuntimeException("Pogresna sifra!");
        }

        return KorisnikDTO.konvertujUDTO(korisnik);
    }

    public KorisnikDTO izmenaProfila(Long id, @NonNull IzmenaProfilaDTO dto) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(()->new RuntimeException("Korisnik nije pronadjen."));

        korisnik.setIme(dto.ime());
        korisnik.setPrezime(dto.prezime());
        if (dto.profilnaSlikaPutanja() != null && !dto.profilnaSlikaPutanja().isBlank()) {
            korisnik.setProfilnaSlikaPutanja(dto.profilnaSlikaPutanja());
        }

        Korisnik azuriraniKorisnik = korisnikRepository.save(korisnik);

        return KorisnikDTO.konvertujUDTO(azuriraniKorisnik);
    }

    public List<KorisnikDTO> getAllKorisnici() {
        return korisnikRepository.findAll().stream()
                .map(KorisnikDTO::konvertujUDTO)
                .toList();
    }

    public void promeniStatusBlokade(long id, boolean b) {
        Korisnik korisnik = korisnikRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Korisnik nije pronadjen!"));

        korisnik.setBlokiran(b);
        korisnikRepository.save(korisnik);
    }

    public List<KorisnikDTO> pretragaKorisnika(String tekst) {
        List<Korisnik> korisniks = korisnikRepository
                .findByImeContainingIgnoreCaseOrPrezimeContainingIgnoreCase(tekst, tekst);

        return korisniks.stream()
                .map(KorisnikDTO::konvertujUDTO)
                .toList();
    }


}
