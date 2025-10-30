package com.logiexpress.model;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;
import java.time.LocalDate;


public class EnvioAereo extends Envio {

    private boolean esInternacional;

    /**
     * Constructor de EnvioAereo.
     *
     * @param origen          Ciudad de origen
     * @param destino         Ciudad de destino
     * @param peso            Peso del envío (kg)
     * @param prioridad       Prioridad (NORMAL o EXPRESS)
     * @param esInternacional Indica si el envío es internacional
     */
    public EnvioAereo(String origen, String destino, double peso, Prioridad prioridad, boolean esInternacional) {
        super(
            "ENV-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
            origen,
            destino,
            peso,
            prioridad,
            EstadoEnvio.PENDIENTE,
            LocalDate.now(),
            TipoEnvio.AEREO
        );
        this.esInternacional = esInternacional;
        validarReglasNegocio();
    }

    /**
     * Valida las restricciones específicas de los envíos aéreos.
     */
    private void validarReglasNegocio() {
        if (peso > 1000) {
            throw new IllegalArgumentException("El peso máximo permitido para envíos aéreos es de 1000 kg.");
        }
    }

    @Override
    double calcularCosto() {
        double costo = peso * 15000; // $15.000 por kilogramo

        if (esInternacional) {
            costo += 50000; // Recargo internacional fijo
        }

        if (prioridad == Prioridad.EXPRESS) {
            costo *= 1.8; // +80% del costo total
        }

        return costo;
    }

    @Override
    int calcularTiempoEntrega() {
        if (esInternacional) {
            return (3 + 7) / 2; // promedio de 5 días
        } else {
            return (1 + 3) / 2; // promedio de 2 días
        }
    }

    @Override
    String obtenerDetallesEspecificos() {
        return "Tipo de envío: Aéreo | Internacional: " + (esInternacional ? "Sí" : "No") +
            " | Costo: $" + calcularCosto() +
            " | Tiempo estimado: " + calcularTiempoEntrega() + " días";
    }

    public boolean isEsInternacional() {
        return esInternacional;
    }

    public void setEsInternacional(boolean esInternacional) {
        this.esInternacional = esInternacional;
    }

    @Override
    public String toString() {
        return "EnvioAereo{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", fechaEnvio=" + fechaEnvio +
                ", tipoEnvio=" + tipoEnvio +
                ", esInternacional=" + esInternacional +
                '}';
    }
}


