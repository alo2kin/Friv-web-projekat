package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Statistika;

public record StatistikaKorisnikaDTO(
        String imeIgrice,
        String kategorijaIgrice,
        long trajanjeUSekundama,
        java.time.LocalDateTime datumIgranja
) {
    public static StatistikaKorisnikaDTO konvertuj(Statistika s) {
        return new StatistikaKorisnikaDTO(
                s.getIgra().getIme(),
                s.getIgra().getKategorija().getIme(),
                s.getTrajanjeUSekundama(),
                s.getPocetnoVreme()
        );
    }
}
