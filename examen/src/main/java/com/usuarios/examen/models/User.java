package com.usuarios.examen.models;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name="USER")

public class User {

    @NotEmpty(message = "nombre no puede ser vacio")        
    private String nombre;
    private String apellidos;
    private String Direcion; 
    
    @Id
    @Email(message = "formato no valido de correo")
    private String email;
    private LocalDate nacimiento; 
    private String  password;








/*
 * ./mvnw ...
 * actuatior
 */






}
