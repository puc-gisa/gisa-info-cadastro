package br.com.gisa.gisainfocadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AutorizacaoExameRequest {

    @NotNull
    private Long idAssociado;

    @NotBlank
    @Size(max = 20)
    private String codigoExame;

    @NotNull
    @Future
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataExame;

    @NotBlank
    @Pattern(regexp = "\\d+-\\w{2}", message = "CRM inválido")
    @Size(max = 20)
    private String crmMedicoSolicitante;

}
