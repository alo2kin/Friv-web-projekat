package com.example.friv_projekat.dto;

public record StatistikaKategorijeDTO(
        String nazivKategorije,
        Long ukupnoSekundi
) {
    public static StatistikaKategorijeDTO konvertuj(Object[] rezultatBaze) {
        Long ukupnoVreme = (Long) rezultatBaze[1];

        return new StatistikaKategorijeDTO(
                (String) rezultatBaze[0],
                ukupnoVreme != null ? ukupnoVreme: 0L
        );
    }
}
