package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Korisnik;

public record KorisnikStatistikaDTO(
        Korisnik korisnik,
        Integer ukupnoSekundi
) {
}
