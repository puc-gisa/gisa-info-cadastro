package br.com.gisa.gisainfocadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AssociadoRequest {
    public interface OnInsert {
    }

    @NotNull
    @Size(min = 2, max = 255)
    private String nome;

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    @Past
    private LocalDate dataNascimento;

    @NotBlank(groups = {OnInsert.class})
    @Length(max = 255, groups = {OnInsert.class})
    @Email(groups = {OnInsert.class})
    private String email;

    @NotNull(groups = {OnInsert.class})
    @Pattern(regexp = "\\d{11}", message = "deve conter 11 numeros", groups = {OnInsert.class})
    private String cpf;

    @Valid
    @NotNull
    private EnderecoRequest endereco;

}
