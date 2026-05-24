package com.example.friv_projekat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record NovaIgraDTO(
        @NotBlank(message = "Naziv igrice je obavezan")
        String ime,

        @NotBlank(message = "Opis igrice je obavezan")
        String opis,

        @NotBlank(message = "Putanja do HTML fajla je obavezna")
        String iframeURL,

        @NotBlank(message = "Putanja do slike je obavezna")
        String thumbnailPutanja,

        @NotNull(message = "Morate izabrati kategoriju")
        Long kategorijaId
) {
}
