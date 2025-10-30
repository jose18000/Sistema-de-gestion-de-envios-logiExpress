package com.logiexpress.model;
import com.logiexpress.enums.Prioridad;
import com.logiexpress.enums.TipoEnvio;
/**
 * Clase que representa un envío terrestre por carretera.
 * Extiende de la clase abstracta Envio.
 * 
 * Reglas de negocio:
 * - Costo base: $5.000 por kilogramo
 * - Costo por distancia: $500 por cada 100 km
 * - Recargo prioridad express: +50% del costo total
 * - Tiempo de entrega: 1 día por cada 500 km (mínimo 1 día)
 * - Peso máximo: 5.000 kg
 */
public class EnvioTerrestre extends Envio {
    private double distanciaKm; {
    
        
    }

    

}
