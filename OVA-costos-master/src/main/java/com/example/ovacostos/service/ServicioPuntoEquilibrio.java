package com.example.ovacostos.service;

import com.example.ovacostos.exception.DatosInvalidosException;
import com.example.ovacostos.model.PuntoEquilibrio;
import org.springframework.stereotype.Service;

@Service
public class ServicioPuntoEquilibrio {

    public double calcularPuntoEquilibrio(PuntoEquilibrio datos) {
        validarDatos(datos);
        return datos.calcularPuntoEquilibrio();
    }

    private void validarDatos(PuntoEquilibrio datos) {
        if(datos.getCostosFijos() <= 0 ||
                datos.getPrecioVenta() <= 0 ||
                datos.getCostosVariables() < 0) {
            throw new DatosInvalidosException("Todos los valores deben ser positivos");
        }

        if(datos.getCostosVariables() >= datos.getPrecioVenta()) {
            throw new DatosInvalidosException("El costo variable unitario debe ser menor al precio de venta");
        }
    }
}