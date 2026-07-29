package com.example.demo.utils;

public class CpfUtils {

    private static final int TAMANHO_CPF = 11;

    private CpfUtils() {
    }

    public static boolean isValido(String cpf) {
        if (cpf == null) {
            return false;
        }

        String digitos = cpf.replaceAll("\\D", "");

        if (digitos.length() != TAMANHO_CPF) {
            return false;
        }

        if (todosDigitosIguais(digitos)) {
            return false;
        }

        int primeiroVerificador = calcularDigito(digitos, 9);
        int segundoVerificador = calcularDigito(digitos, 10);

        return primeiroVerificador == Character.getNumericValue(digitos.charAt(9))
                && segundoVerificador == Character.getNumericValue(digitos.charAt(10));
    }

    private static boolean todosDigitosIguais(String digitos) {
        for (int i = 1; i < digitos.length(); i++) {
            if (digitos.charAt(i) != digitos.charAt(0)) {
                return false;
            }
        }
        return true;
    }

    private static int calcularDigito(String digitos, int quantidadeConsiderada) {
        int soma = 0;
        int peso = quantidadeConsiderada + 1;

        for (int i = 0; i < quantidadeConsiderada; i++) {
            soma += Character.getNumericValue(digitos.charAt(i)) * peso;
            peso--;
        }

        int resto = soma % 11;
        int digito = 11 - resto;

        return digito >= 10 ? 0 : digito;
    }
}
