package com.logiexpress.model;

import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.Prioridad; // Añade esta línea
import com.logiexpress.enums.TipoEnvio; // Importar TipoEnvio
import java.time.LocalDate;  // Importar LocalDate
import java.util.UUID;
public abstract class Envio {
    //Atributos
    public String id;
    public String origen;
    public String destino;
    public double peso; //en kilogramos
    public Prioridad prioridad;
    public EstadoEnvio estado;
    public LocalDate fechaEnvio;
    public TipoEnvio tipoEnvio;

    public Envio(){
        this.id = "";
    }

    //Constructor
    public Envio(String id, String origen, String destino, double peso, Prioridad prioridad, EstadoEnvio estado,
            LocalDate fechaEnvio, TipoEnvio tipoEnvio) {
        this.id = id;
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.prioridad = prioridad;
        this.estado = estado;
        this.fechaEnvio = fechaEnvio;
        this.tipoEnvio = tipoEnvio;
    }

    // Métodos abstractos
    abstract double calcularCosto();

    abstract int calcularTiempoEntrega();

    abstract String obtenerDetallesEspecificos();

    //Métodos concretos
    public String generarNumeroSeguimiento(){
        return "ENV-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    public void actualizarEstado(EstadoEnvio nuevoEstadoEnvio){
        this.estado = nuevoEstadoEnvio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public EstadoEnvio getEstado() {
        return estado;
    }

    public void setEstado(EstadoEnvio estado) {
        this.estado = estado;
    }

    public LocalDate getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDate fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }

    public TipoEnvio getTipoEnvio() {
        return tipoEnvio;
    }

    public void setTipoEnvio(TipoEnvio tipoEnvio) {
        this.tipoEnvio = tipoEnvio;
    }

    
}
