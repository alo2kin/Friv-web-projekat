package com.example.friv_projekat.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record IzmenaIgreDTO(
        @NotBlank(message = "Naziv je obavezan")
        String ime,

        @NotBlank(message = "Opis je obavezan")
        String opis,

        @NotBlank(message = "Putanja do slike je obavezna")
        String thumbnailPutanja,

        @NotNull(message = "Kategorija je obavezna")
        Long kategorijaId
) {
}
