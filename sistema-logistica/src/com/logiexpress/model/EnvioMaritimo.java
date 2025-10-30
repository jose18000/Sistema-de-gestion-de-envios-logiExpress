package com.logiexpress.model;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;
import java.time.LocalDate;

/**
 * Representa un envío marítimo dentro del sistema LogiExpress.
 * Solo se permite para envíos internacionales.
 */
public class EnvioMaritimo extends Envio {

    private String puertoDestino;

    /**
     * Constructor de EnvioMaritimo.
     *
     * @param origen        Puerto o ciudad de origen
     * @param destino       País o ciudad destino
     * @param peso          Peso del envío (kg)
     * @param puertoDestino Nombre del puerto de destino
     */
    public EnvioMaritimo(String origen, String destino, double peso, String puertoDestino) {
        super(
            "ENV-" + java.util.UUID.randomUUID().toString().substring(0, 8).toUpperCase(),
            origen,
            destino,
            peso,
            Prioridad.NORMAL, // siempre normal
            EstadoEnvio.PENDIENTE,
            LocalDate.now(),
            TipoEnvio.MARITIMO
        );
        this.puertoDestino = puertoDestino;
        validarReglasNegocio();
    }

    /**
     * Valida las restricciones específicas del envío marítimo.
     */
    private void validarReglasNegocio() {
        if (peso <= 0) {
            throw new IllegalArgumentException("El peso del envío debe ser mayor a 0 kg.");
        }
        // Solo internacional (asumido)
        if (prioridad == Prioridad.EXPRESS) {
            throw new IllegalArgumentException("Los envíos marítimos no pueden tener prioridad EXPRESS.");
        }
    }

    @Override
    public double calcularCosto() {
        double costo = peso * 2000; // $2.000 por kilogramo
        if (peso > 1000) {
            costo += 150000; // recargo contenedor
        }
        return costo;
    }

    @Override
    public int calcularTiempoEntrega() {
        // 15–45 días, promedio de 30
        return 30;
    }

    @Override
    public String obtenerDetallesEspecificos() {
        return "Tipo de envío: Marítimo | Puerto destino: " + puertoDestino +
            " | Costo: $" + calcularCosto() +
            " | Tiempo estimado: " + calcularTiempoEntrega() + " días";
    }

    public String getPuertoDestino() {
        return puertoDestino;
    }

    public void setPuertoDestino(String puertoDestino) {
        this.puertoDestino = puertoDestino;
    }

    @Override
    public String toString() {
        return "EnvioMaritimo{" +
                "id='" + id + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                ", peso=" + peso +
                ", prioridad=" + prioridad +
                ", estado=" + estado +
                ", fechaEnvio=" + fechaEnvio +
                ", tipoEnvio=" + tipoEnvio +
                ", puertoDestino='" + puertoDestino + '\'' +
                '}';
    }
}


