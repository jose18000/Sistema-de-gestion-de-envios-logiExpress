

import com.logiexpress.enums.*;
import com.logiexpress.model.*;
import com.logiexpress.service.GestorEnvios;

import java.util.Scanner;



public class App {
    public static void main(String[] args) {
        GestorEnvios gestor = new GestorEnvios();
        Scanner scanner = new Scanner(System.in);

        // Datos de prueba
        gestor.agregarEnvio(new EnvioTerrestre("Bogotá", "Medellín", 1200, Prioridad.NORMAL, "RápidoCargo", 400));
        gestor.agregarEnvio(new EnvioAereo("Bogotá", "Miami", 800, Prioridad.EXPRESS, true));
        gestor.agregarEnvio(new EnvioMaritimo("Cartagena", "Barcelona", 2500, "Puerto de Barcelona"));

        int opcion;
        do {
            System.out.println("\n📦 Menú LogiExpress");
            System.out.println("1. Crear nuevo envío");
            System.out.println("2. Listar todos los envíos");
            System.out.println("3. Buscar envío por ID");
            System.out.println("4. Ver costo total");
            System.out.println("5. Filtrar por tipo");
            System.out.println("6. Generar reporte");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // limpiar buffer

            switch (opcion) {
                case 1:
                    crearEnvioInteractivo(scanner, gestor);
                    break;
                case 2:
                    gestor.listarTodosLosEnvios();
                    break;
                case 3:
                    System.out.print("Ingrese el ID del envío: ");
                    String id = scanner.nextLine();  
                    Envio encontrado = gestor.buscarEnvioPorId(id);
                    System.out.println(encontrado != null ? encontrado : "❌ Envío no encontrado.");
                    break;
                case 4:
                    System.out.println("💰 Costo total de envíos: $" + gestor.calcularCostoTotal());
                    break;
                case 5:
                    System.out.print("Ingrese tipo (TERRESTRE, AEREO, MARITIMO): ");
                    String tipoStr = scanner.nextLine().toUpperCase();
                    try {
                        TipoEnvio tipo = TipoEnvio.valueOf(tipoStr);
                        gestor.filtrarPorTipo(tipo).forEach(System.out::println);
                    } catch (IllegalArgumentException e) {
                        System.out.println("❌ Tipo inválido.");
                    }
                    break;
                case 6:
                    gestor.generarReporte();
                    break;
                case 7:
                    System.out.println("👋 Gracias por usar LogiExpress. ¡Hasta pronto!");
                    break;
                default:
                    System.out.println("⚠️ Opción inválida. Intente de nuevo.");
            }
        } while (opcion != 7);

        scanner.close();
    }

    private static void crearEnvioInteractivo(Scanner scanner, GestorEnvios gestor) {
        System.out.print("Seleccione tipo de envío (TERRESTRE, AEREO, MARITIMO): ");
        String tipoStr = scanner.nextLine().toUpperCase();

        try {
            TipoEnvio tipo = TipoEnvio.valueOf(tipoStr);

            System.out.print("Origen: ");
            String origen = scanner.nextLine();
            System.out.print("Destino: ");
            String destino = scanner.nextLine();
            System.out.print("Peso (kg): ");
            double peso = Double.parseDouble(scanner.nextLine());

            switch (tipo) {
                case TERRESTRE:
                    System.out.print("Prioridad (NORMAL/EXPRESS): ");
                    Prioridad prioridadT = Prioridad.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Empresa de transporte: ");
                    String empresa = scanner.nextLine();
                    System.out.print("Distancia (km): ");
                    double distancia = Double.parseDouble(scanner.nextLine());
                    gestor.agregarEnvio(new EnvioTerrestre(origen, destino, peso, prioridadT, empresa, distancia));
                    break;

                case AEREO:
                    System.out.print("Prioridad (NORMAL/EXPRESS): ");
                    Prioridad prioridadA = Prioridad.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("¿Es internacional? (true/false): ");
                    boolean internacional = Boolean.parseBoolean(scanner.nextLine());
                    gestor.agregarEnvio(new EnvioAereo(origen, destino, peso, prioridadA, internacional));
                    break;

                case MARITIMO:
                    System.out.print("Puerto destino: ");
                    String puerto = scanner.nextLine();
                    gestor.agregarEnvio(new EnvioMaritimo(origen, destino, peso, puerto));
                    break;
            }

            System.out.println("✅ Envío creado exitosamente.");

        } catch (Exception e) {
            System.out.println("❌ Error al crear el envío: " + e.getMessage());
        }
    }
}



    


