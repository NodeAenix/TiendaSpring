package org.example.tiendaspring.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "cliente")
public class Cliente {
    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Size(max = 50)
    @NotNull(message = "El nombre es obligatorio.")
    @Column(name = "nombre", nullable = false, length = 50)
    @NotBlank(message = "El nombre no puede estar vacío.")
    @Pattern(regexp = "^[A-Za-z]+$", message = "El nombre solo puede contener carácteres alfabéticos.")
    private String nombre;

    @Size(max = 50)
    @NotNull(message = "El apellido es obligatorio.")
    @Column(name = "apellido", nullable = false, length = 50)
    @NotBlank(message = "El apellido no puede estar vacío.")
    private String apellido;

    @Size(max = 50)
    @NotNull(message = "El nombre de usuario es obligatorio.")
    @Column(name = "nickname", nullable = false, length = 50)
    @NotBlank(message = "El nombre de usuario no puede estar vacío.")
    private String nickname;

    @Size(max = 255)
    @NotNull(message = "La contraseña es obligatoria.")
    @Column(name = "password", nullable = false)
    @NotBlank(message = "La contraseña no puede estar vacía.")
    @Pattern(regexp = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]){8,}$",
             message = "La contraseña debe tener mínimo 8 carácteres"
                     + "y debe incluir una minúscula, una mayúscula y un número.")
    private String password;

    @Size(max = 15)
    @Column(name = "telefono", length = 15)
    @Pattern(regexp = "^([69])\\d{8}$",
             message = "El teléfono debe tener 9 dígitos y comenzar por 6 o 9.")
    private String telefono;

    @Size(max = 100)
    @Column(name = "domicilio", length = 100)
    private String domicilio;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

}