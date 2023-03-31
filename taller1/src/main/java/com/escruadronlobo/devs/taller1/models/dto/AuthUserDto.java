package com.escruadronlobo.devs.taller1.models.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class AuthUserDto {
    @NotEmpty(message = "Debe poner su código")
    @Pattern(regexp = "^[A-Z]{2}\\d{6}$", flags = Pattern.Flag.MULTILINE, message="El usuario no cumple el formato necesario")
    private String userCode;

    @NotEmpty(message = "La contraseña no puede ir vacia")
    @Size(min= 8, max=10, message = "La contraseña debe tener al menos 8 caracteres")
    private String password;

}