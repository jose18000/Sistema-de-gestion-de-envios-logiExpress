package com.logiexpress.model;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;
import java.time.LocalDate;

/**
 * Representa un envío terrestre dentro del sistema LogiExpress.
 * Aplica las reglas de negocio específicas para transporte nacional por carretera.
 */
public class EnvioTerrestre extends Envio {

    private String empresaTransporte; // Empresa encargada del transporte
    private double distanciaKm;       // Distancia aproximada en kilómetros

    /**
     * Constructor de EnvioTerrestre.
     *
     * @param origen            Ciudad de origen
     * @param destino           Ciudad de destino
     * @param peso              Peso del envío (kg)
     * @param prioridad         Prioridad del envío (NORMAL o EXPRESS)
     * @param empresaTransporte Nombre de la empresa transportadora
     * @param distanciaKm       Distancia entre origen y destino en kilómetros
     */
    public EnvioTerrestre(String origen, String destino, double peso, Prioridad prioridad,
                          String empresaTransporte, double distanciaKm) {
        super(
            "ENV-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
            origen,
            destino,
            peso,
            prioridad,
            EstadoEnvio.PENDIENTE,
            LocalDate.now(),
            TipoEnvio.TERRESTRE
        );
        this.empresaTransporte = empresaTransporte;
        this.distanciaKm = distanciaKm;
        validarReglasNegocio();
    }

    /**
     * Valida las reglas de negocio específicas de los envíos terrestres.
     */
    private void validarReglasNegocio() {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso del envío debe ser mayor que 0 kg.");
        }
        if (distanciaKm <= 0) {
            throw new IllegalArgumentException("La distancia debe ser mayor que 0 km.");
        }
        if (peso > 5000) {
            throw new IllegalArgumentException("El peso máximo permitido para envíos terrestres es de 5000 kg.");
        }
    }

    @Override
    double calcularCosto() {
        // Costo base por kilómetro y peso
        double costoBase = (peso * 1000) + (distanciaKm * 50); // $1.000 por kg + $50 por km

        // Recargo por prioridad EXPRESS
        if (prioridad == Prioridad.EXPRESS) {
            costoBase *= 1.25; // +25% del costo total
        }

        // Descuento por peso grande (más de 3000 kg)
        if (peso > 3000) {
            costoBase *= 0.9; // -10% de descuento
        }

        return costoBase;
    }

    @Override
    int calcularTiempoEntrega() {
        // Estimación: 1 día por cada 500 km
        int dias = (int) Math.ceil(distanciaKm / 500);

        // Prioridad EXPRESS reduce el tiempo un 30%
        if (prioridad == Prioridad.EXPRESS) {
            dias = Math.max(1, (int) Math.ceil(dias * 0.7));
        }

        return dias;
    }

    @Override
    String obtenerDetallesEspecificos() {
        return "Tipo de envío: Terrestre | Empresa: " + empresaTransporte +
               " | Distancia: " + distanciaKm + " km" +
               " | Costo: $" + calcularCosto() +
               " | Tiempo estimado: " + calcularTiempoEntrega() + " días";
    }

    public String getEmpresaTransporte() {
        return empresaTransporte;
    }

    public void setEmpresaTransporte(String empresaTransporte) {
        this.empresaTransporte = empresaTransporte;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public void setDistanciaKm(double distanciaKm) {
        this.distanciaKm = distanciaKm;
    }

    @Override
    public String toString() {
        return "EnvioTerrestre{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", fechaEnvio=" + fechaEnvio +
                ", tipoEnvio=" + tipoEnvio +
                ", empresaTransporte='" + empresaTransporte + '\'' +
                ", distanciaKm=" + distanciaKm +
                '}';
    }
}