package com.logiexpress.model;

import com.logiexpress.enums.EstadoEnvio;
public class Envio {
    public String id;
    public String origen;
    public String destino;
    public double peso;
    public Prioridad prioridad;
    public EstadoEnvio estado;
    public LocalDate fechaEnvio;
    public TipoEnvio tipoEnvio;

    public Envio(){
        this.id = "";
    }

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

    public double calcularCosto(){
        double costo = 
    }
}
