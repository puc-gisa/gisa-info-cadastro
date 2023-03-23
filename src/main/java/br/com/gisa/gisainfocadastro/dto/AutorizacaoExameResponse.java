package br.com.gisa.gisainfocadastro.dto;

import br.com.gisa.gisainfocadastro.domain.SituacaoAutoricacaoExame;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AutorizacaoExameResponse {

    @JsonProperty("codigoSolicitacao")
    private Long id;

    private Long idAssociado;

    private String codigoExame;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataExame;

    private String crmMedicoSolicitante;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataSolicitacao;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataValidade;

    private String justificativa;

    @JsonIgnore
    private Integer codigoSituacao;

    @JsonProperty("resultado.situacao")
    public String situacao() {
        return SituacaoAutoricacaoExame.ofCodigo(codigoSituacao).name();
    }

}
