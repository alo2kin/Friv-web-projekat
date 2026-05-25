package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Igra;

public record StatistikaIgreDTO(
        String imeIgre,
        String imeKategorije,
        Long ukupnoVremeIgranjaIgre
) {
    public static StatistikaIgreDTO konvertuj(Object[] rezultatIzBaze) {
        Igra igra = (Igra) rezultatIzBaze[0];
        Long ukupnoVreme = (Long) rezultatIzBaze[1];

        return new StatistikaIgreDTO(
                igra.getIme(),
                igra.getKategorija().getIme(),
                ukupnoVreme != null ? ukupnoVreme: 0L
        );
    }
}
