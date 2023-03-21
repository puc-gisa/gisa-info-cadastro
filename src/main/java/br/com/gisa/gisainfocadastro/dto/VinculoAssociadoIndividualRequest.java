package br.com.gisa.gisainfocadastro.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class VinculoAssociadoIndividualRequest {

    @NotNull
    private Integer codigoTipoPlano;

    @NotNull
    private Long idAssociado;

}
