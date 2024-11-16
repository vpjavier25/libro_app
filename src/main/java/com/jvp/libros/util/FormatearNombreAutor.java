package com.jvp.libros.util;

public class FormatearNombreAutor {
    public static String formatearNombre(String nombre) {
        String[] nombreEnPartes = nombre.split(",");
        StringBuilder nombreFormateado = new StringBuilder();
        for (int i = 0; i < nombreEnPartes.length; i++) {
            nombreFormateado.append(nombreEnPartes[nombreEnPartes.length - 1 - i].trim()).append(" ");
        }
        return nombreFormateado.toString();
    }
}
