package com.example.ovacostos.controller;

import com.example.ovacostos.model.PuntoEquilibrio;
import com.example.ovacostos.service.ServicioPuntoEquilibrio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControladorPuntoEquilibrio {

    private final ServicioPuntoEquilibrio servicio;

    @Autowired
    public ControladorPuntoEquilibrio(ServicioPuntoEquilibrio servicio) {
        this.servicio = servicio;
    }

    @PostMapping("/calcular")
    public ResultadoCalculo calcularPuntoEquilibrio(@RequestBody PeticionPuntoEquilibrio peticion) {
        PuntoEquilibrio datos = new PuntoEquilibrio();
        datos.setCostosFijos(peticion.costosFijos());
        datos.setPrecioVenta(peticion.precioVenta());
        datos.setCostosVariables(peticion.costosVariables());

        double puntoEquilibrio = servicio.calcularPuntoEquilibrio(datos);
        return new ResultadoCalculo(puntoEquilibrio);
    }

    private record PeticionPuntoEquilibrio(double costosFijos, double precioVenta, double costosVariables) {}
    private record ResultadoCalculo(double puntoEquilibrio) {}
}