package com.jvp.libros.modelo;

public enum IdiomaNombre {
    // Idiomas más comunes
    ESPAÑOL("es", "Español"),
    INGLES("en", "Inglés"),
    FRANCES("fr", "Francés"),
    ALEMAN("de", "Alemán"),
    ITALIANO("it", "Italiano"),
    PORTUGUES("pt", "Portugués"),
    RUSO("ru", "Ruso"),
    CHINO("zh", "Chino"),
    JAPONES("ja", "Japonés"),
    ARABE("ar", "Árabe"),
    HINDI("hi", "Hindi"),
    COREANO("ko", "Coreano"),
    SUECO("sv", "Sueco"),
    NORUEGO("no", "Noruego"),
    FINLANDES("fi", "Finlandés"),
    HOLANDES("nl", "Holandés"),
    TURCO("tr", "Turco"),
    POLACO("pl", "Polaco"),
    GRIEGO("el", "Griego");

    private final String codigo;
    private final String nombre;

    IdiomaNombre(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public static IdiomaNombre fromCodigo(String codigo) {
        for (IdiomaNombre idioma : values()) {
            if (idioma.codigo.equalsIgnoreCase(codigo)) {
                return idioma;
            }
        }
        throw new IllegalArgumentException("Código de idioma no reconocido: " + codigo);
    }
}
