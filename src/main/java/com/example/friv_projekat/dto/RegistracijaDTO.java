package com.example.friv_projekat.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record RegistracijaDTO(
        @NotBlank(message = "Polje ime ne sme biti prazno")
        String ime,

        @NotBlank(message = "Polje prezime ne sme biti prazno")
        String prezime,

        @NotBlank(message = "Polje email ne sme biti prazno")
        @Email(message = "Format email adrese nije validan")
        String email,

        @NotBlank(message = "Polje sifra ne sme biti prazno")
        @Size(min = 6, message = "Sifra mora imati najmanje 6 karaktera")
        String sifra,

        @NotBlank(message = "Datum rodjena nije unet")
        LocalDate datumRodjenja
) {
}
