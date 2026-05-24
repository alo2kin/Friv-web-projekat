package com.example.friv_projekat.dto;

import jakarta.validation.constraints.NotBlank;

public record IzmenaProfilaDTO (
    @NotBlank(message = "Ime ne sme biti prazno")
    String ime,

    @NotBlank(message = "Prezime ne sme biti prazno")
    String prezime,

    String profilnaSlikaPutanja
){
}
