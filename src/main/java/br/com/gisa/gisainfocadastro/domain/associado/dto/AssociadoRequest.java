package br.com.gisa.gisainfocadastro.domain.associado.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AssociadoRequest {

    @NotNull
    @Size(min = 2, max = 255)
    private String nome;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    @Length(max = 255)
    @Email
    private String email;

    @NotNull
    @Pattern(regexp = "\\d{11}", message = "deve conter somente numeros")
    private String cpf;

}
