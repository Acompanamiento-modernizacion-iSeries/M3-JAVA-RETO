package models;

import java.util.ArrayList;

public class HistorialTransacciones {

    public static ArrayList<String> historialTransacciones = new ArrayList<>();

    public static ArrayList<String> ConsultarHistorialTransacciones() {
        return historialTransacciones;
    }

    public static void AsignarHistorialTransacciones(String historialTransacciones) {
        HistorialTransacciones.historialTransacciones.add(historialTransacciones);
    }


}
