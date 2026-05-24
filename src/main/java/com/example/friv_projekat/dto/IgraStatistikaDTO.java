package com.example.friv_projekat.dto;

import com.example.friv_projekat.model.Igra;

public record IgraStatistikaDTO(
        Igra igra,
        Integer ukupnoSekundi
) {
}
