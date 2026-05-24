package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Korisnik;

public record KorisnikDTO(
        long id,
        String ime,
        String prezime,
        String email,
        String profilnaSlikaPutanja,
        String uloga
) {
    public static KorisnikDTO konvertujUDTO(Korisnik korisnik) {
        return new KorisnikDTO(
                korisnik.getId(),
                korisnik.getIme(),
                korisnik.getPrezime(),
                korisnik.getEmail(),
                korisnik.getProfilnaSlikaPutanja(),
                korisnik.getRole().toString()
        );
    }
}
