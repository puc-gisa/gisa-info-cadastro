package br.com.gisa.gisainfocadastro.dto;

import lombok.Data;

@Data
public class EnderecoResponse {

    private String logradouro;

    private Integer numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String estado;

    private String cep;
}
