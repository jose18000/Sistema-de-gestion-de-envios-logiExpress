package com.logiexpress.model;

public class EnvioMaritimo {


public class EnvioMaritimo extends Envio {
    private String puertoDestino;

    public EnvioMaritimo(String origen, String destino, double peso, String prioridad, String puertoDestino) {
        super(origen, destino, peso, "Normal"); 
        this.puertoDestino = puertoDestino;
    }

    
    @Override
    public double calcularCosto() {
        double costoBase = peso * 2000;

        if (peso > 1000) {
            costoBase += 150000; 
        }

        return costoBase;
    }

    
    @Override
    public boolean validarDatos() {
        return peso > 0 &&
            origen != null && !origen.isEmpty() &&
            destino != null && !destino.isEmpty() &&
            puertoDestino != null && !puertoDestino.isEmpty();
    }

    
    @Override
    public String tiempoEstimado() {
        return "Tiempo estimado: 15 a 45 días (promedio 30 días)";
    }

    @Override
    public String toString() {
        return super.toString() +
            ", Tipo: Marítimo, Puerto: " + puertoDestino +
            ", " + tiempoEstimado() +
            ", Costo: $" + calcularCosto();
    }
}

}
