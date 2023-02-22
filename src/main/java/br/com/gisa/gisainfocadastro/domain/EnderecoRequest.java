package br.com.gisa.gisainfocadastro.domain;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class EnderecoRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    private String logradouro;

    private Integer numero;

    @Size(max = 255)
    private String complemento;

    @NotBlank
    @Size(max = 255)
    private String bairro;

    @NotBlank
    @Size(max = 255)
    private String cidade;

    @NotBlank
    @Size(max = 2)
    private String estado;

    @NotBlank
    @Size(min = 8, max = 8)
    private String cep;

}
