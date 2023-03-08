package br.com.gisa.gisainfocadastro.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import static br.com.gisa.gisainfocadastro.domain.TipoCliente.EMPRESARIAL;
import static br.com.gisa.gisainfocadastro.domain.TipoCliente.INDIVIDUAL;

@Getter
@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum TipoPlano {
    ENFERMARIA(1, "Enfermaria", INDIVIDUAL, EMPRESARIAL),
    APARTAMENTO(2, "Apartamento", INDIVIDUAL, EMPRESARIAL),
    VIP(3, "Vip", INDIVIDUAL);

    private final int codigo;
    private final String descricao;
    private final Set<TipoCliente> tipoClientes;

    TipoPlano(int codigo, String descricao, TipoCliente... tipoClientes) {
        this.codigo = codigo;
        this.descricao = descricao;
        this.tipoClientes = Arrays.stream(tipoClientes)
            .collect(Collectors.toSet());
    }

    public static Optional<TipoPlano> ofCodigo(int codigo) {
        return Arrays.stream(values())
            .filter(tipo -> codigo == tipo.codigo)
            .findFirst();
    }

    public static List<TipoPlano> ofTipoCliente(TipoCliente tipoCliente) {
        return Arrays.stream(values())
            .filter(tipo -> tipo.tipoClientes.contains(tipoCliente))
            .collect(Collectors.toList());
    }
}
