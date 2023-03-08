package br.com.gisa.gisainfocadastro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.Optional;

@Getter
@AllArgsConstructor
public enum TipoCliente {
    INDIVIDUAL(1),
    EMPRESARIAL(2);

    private final int codigo;

    public static Optional<TipoCliente> ofCodigo(int codigo) {
        return Arrays.stream(values())
            .filter(tipo -> codigo == tipo.codigo)
            .findFirst();
    }
}
