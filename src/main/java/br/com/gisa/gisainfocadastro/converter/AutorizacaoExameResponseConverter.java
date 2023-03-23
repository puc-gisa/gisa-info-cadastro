package br.com.gisa.gisainfocadastro.converter;

import br.com.gisa.gisainfocadastro.domain.AutorizacaoExameEntity;
import br.com.gisa.gisainfocadastro.domain.SituacaoAutoricacaoExame;
import br.com.gisa.gisainfocadastro.dto.AutorizacaoExameResponse;
import org.modelmapper.AbstractConverter;

public class AutorizacaoExameResponseConverter extends AbstractConverter<AutorizacaoExameEntity, AutorizacaoExameResponse> {

    @Override
    protected AutorizacaoExameResponse convert(AutorizacaoExameEntity source) {
        AutorizacaoExameResponse result = new AutorizacaoExameResponse();
        result.setId(source.getId());
        result.setIdAssociado(source.getIdAssociado());
        result.setCodigoExame(source.getCodigoExame());
        result.setDataSolicitacao(source.getDataSolicitacao());
        result.setCrmMedicoSolicitante(source.getCrmMedicoSolicitante());
        result.setDataExame(source.getDataExame());

        AutorizacaoExameResponse.Response response = new AutorizacaoExameResponse.Response();
        response.setSituacao(SituacaoAutoricacaoExame.ofCodigo(source.getCodigoSituacao()).toString());
        response.setDataValidade(source.getDataValidade());
        response.setJustificativa(source.getJustificativa());
        result.setResponse(response);

        return result;
    }
}
