package com.logiexpress.service;

import com.logiexpress.model.Envio;
import com.logiexpress.enums.EstadoEnvio;
import com.logiexpress.enums.TipoEnvio;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase encargada de gestionar los envíos registrados en el sistema LogiExpress.
 */
public class GestorEnvios {

    private final List<Envio> listaEnvios;

    public GestorEnvios() {
        this.listaEnvios = new ArrayList<>();
    }

    /**
     * Agrega un nuevo envío a la lista.
     * @param envio Envío a registrar
     */
    public void agregarEnvio(Envio envio) {
        if (envio == null) {
            throw new IllegalArgumentException("El envío no puede ser nulo.");
        }
        listaEnvios.add(envio);
    }

    /**
     * Lista todos los envíos registrados.
     */
    public void listarTodosLosEnvios() {
        if (listaEnvios.isEmpty()) {
            System.out.println("No hay envíos registrados.");
            return;
        }
        for (Envio envio : listaEnvios) {
            System.out.println(envio);
            System.out.println("Costo: $" + envio.calcularCosto());
            System.out.println("Tiempo estimado: " + envio.calcularTiempoEntrega() + " días");
            System.out.println("Detalles: " + envio.obtenerDetallesEspecificos());
            System.out.println("--------------------------------------------------");
        }
    }

    /**
     * Busca un envío por su ID.
     * @param id Identificador del envío
     * @return Envío encontrado o null si no existe
     */
    public Envio buscarEnvioPorId(String id) {
        for (Envio envio : listaEnvios) {
            if (envio.getId().equalsIgnoreCase(id)) {
                return envio;
            }
        }
        return null;
    }

    /**
     * Calcula el costo total de todos los envíos registrados.
     * @return Suma total de costos
     */
    public double calcularCostoTotal() {
        double total = 0;
        for (Envio envio : listaEnvios) {
            total += envio.calcularCosto();
        }
        return total;
    }

    /**
     * Filtra los envíos por tipo (TERRESTRE, AEREO, MARITIMO).
     * @param tipo Tipo de envío
     * @return Lista de envíos filtrados
     */
    public List<Envio> filtrarPorTipo(TipoEnvio tipo) {
        List<Envio> resultado = new ArrayList<>();
        for (Envio envio : listaEnvios) {
            if (envio.getTipoEnvio() == tipo) {
                resultado.add(envio);
            }
        }
        return resultado;
    }

    /**
     * Filtra los envíos por estado (PENDIENTE, EN_TRANSITO, etc.).
     * @param estado Estado del envío
     * @return Lista de envíos filtrados
     */
    public List<Envio> filtrarPorEstado(EstadoEnvio estado) {
        List<Envio> resultado = new ArrayList<>();
        for (Envio envio : listaEnvios) {
            if (envio.getEstado() == estado) {
                resultado.add(envio);
            }
        }
        return resultado;
    }

    /**
     * Genera un reporte con estadísticas generales del sistema.
     */
    public void generarReporte() {
        System.out.println("📊 Reporte General de Envíos");
        System.out.println("Total de envíos registrados: " + listaEnvios.size());
        System.out.println("Costo total acumulado: $" + calcularCostoTotal());
        System.out.println("Costo promedio por envío: $" +
            (listaEnvios.isEmpty() ? 0 : calcularCostoTotal() / listaEnvios.size()));

        System.out.println("\nEnvíos por tipo:");
        for (TipoEnvio tipo : TipoEnvio.values()) {
            long count = listaEnvios.stream().filter(e -> e.getTipoEnvio() == tipo).count();
            System.out.println("- " + tipo + ": " + count);
        }

        System.out.println("\nEnvíos por estado:");
        for (EstadoEnvio estado : EstadoEnvio.values()) {
            long count = listaEnvios.stream().filter(e -> e.getEstado() == estado).count();
            System.out.println("- " + estado + ": " + count);
        }
    }
}