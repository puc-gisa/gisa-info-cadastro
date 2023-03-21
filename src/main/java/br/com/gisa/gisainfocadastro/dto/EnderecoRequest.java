package br.com.gisa.gisainfocadastro.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class EnderecoRequest {

    @NotBlank
    @Size(min = 2, max = 255)
    private String logradouro;

    @NotNull
    @Pattern(regexp = "\\d+", message = "deve conter somente numeros")
    private String numero;

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

    @NotNull
    @Pattern(regexp = "\\d{8}", message = "deve conter somente numeros")
    private String cep;

}
