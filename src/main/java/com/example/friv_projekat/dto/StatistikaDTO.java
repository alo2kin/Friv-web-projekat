package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Statistika;

import java.time.LocalDateTime;

public record StatistikaDTO(
        String imeKorisnika,
        String emailKorisnika,
        String imeIgrice,
        LocalDateTime pocetnoVreme,
        LocalDateTime krajnjeVreme,
        Integer trajanjeUSekundama
) {
    public static StatistikaDTO konvertuj(Statistika s) {
        return new StatistikaDTO(
                s.getKorisnik().getIme(),
                s.getKorisnik().getEmail(),
                s.getIgra().getIme(),
                s.getPocetnoVreme(),
                s.getKrajnjeVreme(),
                s.getTrajanjeUSekundama()
        );
    }
}
