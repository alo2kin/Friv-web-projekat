package com.example.friv_projekat.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record NovaRecenzijaDTO(
        @Min(value = 1, message = "Ocena mora biti najmanje 1")
        @Max(value = 5, message = "Ocena može biti najviše 5")
        int ocena,

        @NotBlank(message = "Komentar ne sme biti prazan")
        @Size(max = 500, message = "Komentar može imati najviše 500 karaktera")
        String komentar
) {
}
