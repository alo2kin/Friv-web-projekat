package com.example.friv_projekat.dto;

import jakarta.validation.constraints.NotBlank;

public record NovaKategorijaDTO(
        @NotBlank(message = "Naziv kategorije je obavezan")
        String ime,

        @NotBlank(message = "Opis kategorije je obavezan")
        String opis
) {
}
