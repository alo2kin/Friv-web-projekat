package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Igra;

public record IgraAdminDTO(
        String ime,
        String opis,
        String thumbnailPutanja,
        String url,
        String nazivKategorije
) {
    public static IgraAdminDTO konvertujUAdminDTO(Igra igra) {
        return new IgraAdminDTO(
                igra.getIme(),
                igra.getOpis(),
                igra.getThumbnailPutanja(),
                igra.getURL(),
                igra.getKategorija().getIme()
        );
    }
}
