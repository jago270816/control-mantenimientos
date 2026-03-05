package com.lavanderia.mantenimiento.service;

import com.lavanderia.mantenimiento.model.punto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class puntoService {

    public LocalDate calcularProximaLimpieza(punto punto) {

        int maquinas = punto.getNumero_maquinas();
        LocalDate ultima = punto.getFecha_ultima_limpieza();

        int dias;

        if (maquinas == 1) {
            dias = 20;
        } else if (maquinas <= 3) {
            dias = 15;
        } else {
            dias = 12;
        }

        return ultima.plusDays(dias);
    }

    public String estadoLimpieza(punto punto) {

        LocalDate hoy = LocalDate.now();
        LocalDate proxima = calcularProximaLimpieza(punto);

        if (hoy.isAfter(proxima)) {
            return "ROJO";
        }

        if (hoy.plusDays(3).isAfter(proxima)) {
            return "AMARILLO";
        }

        return "VERDE";
    }
    
    public LocalDate calcularProximoPreventivo(punto punto) {

    int maquinas = punto.getNumero_maquinas();
    LocalDate ultimo = punto.getFecha_ultimo_preventivo();

    int meses;

    if (maquinas <= 3) {
        meses = 5;
    } else {
        meses = 4;
    }

    return ultimo.plusMonths(meses);
    }
    
    public String estadoPreventivo(punto punto) {

    LocalDate hoy = LocalDate.now();
    LocalDate proximo = calcularProximoPreventivo(punto);

    if (hoy.isAfter(proximo)) {
        return "ROJO";
    }

    if (hoy.plusDays(7).isAfter(proximo)) {
        return "AMARILLO";
    }

    return "VERDE";
    }
    
}
