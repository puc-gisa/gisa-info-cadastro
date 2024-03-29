package br.com.gisa.gisainfocadastro.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;

@Getter
@AllArgsConstructor
public enum SituacaoAutoricacaoExame {
    FALHA(0),
    PENDENTE(1),
    AUTORIZADO(2),
    NEGADO(3),
    EM_ANALISE(4);

    private final int codigo;

    public static SituacaoAutoricacaoExame ofCodigo(Integer codigo) {
        return Arrays.stream(values())
            .filter(tipo -> codigo == tipo.codigo)
            .findFirst()
            .orElse(FALHA);
    }
}
