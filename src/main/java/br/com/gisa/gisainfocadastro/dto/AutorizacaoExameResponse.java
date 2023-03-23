package br.com.gisa.gisainfocadastro.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutorizacaoExameResponse {

    @JsonProperty(value = "codigoSolicitacao", index = 0)
    private Long id;

    @JsonProperty(index = 1)
    private Long idAssociado;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @JsonProperty(index = 2)
    private LocalDateTime dataSolicitacao;

    @JsonProperty(index = 3)
    private String codigoExame;

    @JsonFormat(pattern = "dd/MM/yyyy")
    @JsonProperty(index = 4)
    private LocalDate dataExame;

    @JsonProperty(index = 5)
    private String crmMedicoSolicitante;

    @JsonProperty(value = "response", index = Integer.MAX_VALUE)
    private Response response;

    @Data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    public static class Response {
        private String justificativa;

        private String situacao;

        @JsonFormat(pattern = "dd/MM/yyyy")
        private LocalDate dataValidade;
    }
}

