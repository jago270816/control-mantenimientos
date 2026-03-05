
package com.lavanderia.mantenimiento.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

@Entity
@Table(name="puntos")

public class punto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;

    private String direccion;

    private int numero_maquinas;

    private java.time.LocalDate fecha_ultima_limpieza;

    private java.time.LocalDate fecha_ultimo_preventivo;

    public punto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getNumero_maquinas() {
        return numero_maquinas;
    }

    public void setNumero_maquinas(int numero_maquinas) {
        this.numero_maquinas = numero_maquinas;
    }

    public java.time.LocalDate getFecha_ultima_limpieza() {
        return fecha_ultima_limpieza;
    }

    public void setFecha_ultima_limpieza(java.time.LocalDate fecha_ultima_limpieza) {
        this.fecha_ultima_limpieza = fecha_ultima_limpieza;
    }

    public java.time.LocalDate getFecha_ultimo_preventivo() {
        return fecha_ultimo_preventivo;
    }

    public void setFecha_ultimo_preventivo(java.time.LocalDate fecha_ultimo_preventivo) {
        this.fecha_ultimo_preventivo = fecha_ultimo_preventivo;
    }
}

    
    




    
