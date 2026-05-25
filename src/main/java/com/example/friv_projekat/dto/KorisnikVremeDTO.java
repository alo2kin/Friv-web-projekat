package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Korisnik;

public record KorisnikVremeDTO(
        String ime,
        String prezime,
        String email,
        Long ukupnoSekundi
) {
    public static KorisnikVremeDTO konvertuj(Object[] rezultatIzBaze) {
        Korisnik korisnik = (Korisnik) rezultatIzBaze[0];
        Long ukupnoSekundi = (Long) rezultatIzBaze[1];
         return new KorisnikVremeDTO(
                 korisnik.getIme(),
                 korisnik.getPrezime(),
                 korisnik.getEmail(),
                 ukupnoSekundi != null ? ukupnoSekundi: 0L
         );
    }
}
