package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Igra;
import org.springframework.data.domain.Page;

public record IgraStranicaDTO(
        String ime,
        String opis,
        String naizvKategorije,
        String slikaPutanja,
        String iframeURL,
        Double prosecnaOcena,
        Page<RecenzijaDTO> komentari
) {
    public static IgraStranicaDTO konvertujUStranicaDTO(
            Igra igra, Page<RecenzijaDTO> komentari
    ) {
        return new IgraStranicaDTO(
                igra.getIme(),
                igra.getOpis(),
                igra.getKategorija().getIme(),
                igra.getThumbnailPutanja(),
                igra.getURL(),
                igra.getProsecnaOcena(),
                komentari
        );
    }
}
