package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Recenzija;

import java.time.LocalDateTime;

public record RecenzijaDTO(
        String imeKorisnika,
        String prezimeKorisnika,
        int ocena,
        String komentar,
        LocalDateTime datumObjave
) {
    public static RecenzijaDTO kovertujUDTO(Recenzija recenzija) {
        return new RecenzijaDTO(
                recenzija.getKorisnik().getIme(),
                recenzija.getKorisnik().getPrezime(),
                recenzija.getOcena(),
                recenzija.getKomentar(),
                recenzija.getDatumObjave()
        );
    }
}
