package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Igra;

public record IgraPregledDTO(
        String ime,
        String slikaPutanja,
        String nazivKategorije,
        Double prosecnaOcena
) {
    public static IgraPregledDTO konvertujUPregledDTO(Igra igra) {
        return new IgraPregledDTO(
                igra.getIme(),
                igra.getThumbnailPutanja(),
                igra.getKategorija().getIme(),
                igra.getProsecnaOcena()
        );
    }
}
