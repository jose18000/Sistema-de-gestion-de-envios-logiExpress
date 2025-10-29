package com.logiexpress.model;

public class EnvioAereo {

    
public class EnvioAereo extends Envio {
    private boolean esInternacional;

    public EnvioAereo(String origen, String destino, double peso, String prioridad, boolean esInternacional) {
        super(origen, destino, peso, prioridad);
        this.esInternacional = esInternacional;
    }

    
    @Override
    public double calcularCosto() {
        double costoBase = peso * 15000;

        if (esInternacional) {
            costoBase += 50000; 
        }

        if ("Express".equalsIgnoreCase(prioridad)) {
            costoBase *= 1.8; 
        }

        return costoBase;
    }

    
    @Override
    public boolean validarDatos() {
        return peso > 0 && peso <= 1000 &&
            origen != null && !origen.isEmpty() &&
            destino != null && !destino.isEmpty();
    }

    
    @Override
    public String tiempoEstimado() {
        if (esInternacional) {
            return "Tiempo estimado: 3 a 7 días";
        } else {
            return "Tiempo estimado: 1 a 3 días";
        }
    }

    @Override
    public String toString() {
        return super.toString() +
            ", Tipo: Aéreo, Internacional: " + esInternacional +
            ", " + tiempoEstimado() +
            ", Costo: $" + calcularCosto();
    }
}

}
