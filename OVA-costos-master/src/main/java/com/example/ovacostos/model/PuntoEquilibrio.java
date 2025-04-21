package com.example.ovacostos.model;

import lombok.Data;

@Data
public class PuntoEquilibrio {
    private double costosFijos;
    private double precioVenta;
    private double costosVariables;
    private double unidadesVendidas;

    public double calcularPuntoEquilibrio() {
        double margenContribucion = precioVenta - costosVariables;
        if(margenContribucion <= 0) {
            throw new ArithmeticException("El margen de contribución debe ser positivo");
        }
        return costosFijos / margenContribucion;
    }
}