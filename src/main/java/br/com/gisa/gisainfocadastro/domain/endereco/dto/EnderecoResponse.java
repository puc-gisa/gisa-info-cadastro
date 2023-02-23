package br.com.gisa.gisainfocadastro.domain.endereco.dto;

import lombok.Data;

@Data
public class EnderecoResponse {

    private Long idAssociado;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}
