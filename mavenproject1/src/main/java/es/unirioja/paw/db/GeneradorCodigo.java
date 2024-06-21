/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package es.unirioja.paw.db;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;

/**
 *
 * @author ASUS
 */
public class GeneradorCodigo {

    private static final int LONGITUD_PARTE_NUMERICA = 6;
    private static final int LONGITUD_PARTE_ALFANUMERICA = 2;
    private static final String CARACTERES_ALFANUMERICOS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    private static final int MAX_INTENTOS_GENERACION = 10000; // Evitar un bucle infinito en caso de que sea imposible generar un código no repetido

    private Set<String> codigosGenerados;
    private Random random;

    public GeneradorCodigo() {
        codigosGenerados = new HashSet<>();
        random = new Random();
    }

    public String generarCodigo() {
        for (int intento = 0; intento < MAX_INTENTOS_GENERACION; intento++) {
            String codigo = generarParteNumerica() + "-" + generarParteAlfanumerica();
            if (!codigosGenerados.contains(codigo)) {
                codigosGenerados.add(codigo);
                return codigo;
            }
        }
        throw new IllegalStateException("No se pudo generar un código no repetido después de " + MAX_INTENTOS_GENERACION + " intentos");
    }

    private String generarParteNumerica() {
        return String.format("%0" + LONGITUD_PARTE_NUMERICA + "d", random.nextInt((int) Math.pow(10, LONGITUD_PARTE_NUMERICA)));
    }

    private String generarParteAlfanumerica() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < LONGITUD_PARTE_ALFANUMERICA; i++) {
            int index = random.nextInt(CARACTERES_ALFANUMERICOS.length());
            sb.append(CARACTERES_ALFANUMERICOS.charAt(index));
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        GeneradorCodigo generador = new GeneradorCodigo();
        for (int i = 0; i < 5; i++) {
            System.out.println(generador.generarCodigo());
        }
    }
}