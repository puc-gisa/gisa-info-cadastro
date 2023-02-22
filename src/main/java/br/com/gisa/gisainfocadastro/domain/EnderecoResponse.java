package br.com.gisa.gisainfocadastro.domain;

import lombok.Data;

@Data
public class EnderecoResponse {

    private Long id;

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}
