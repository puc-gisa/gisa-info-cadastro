package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.config.Amqp;
import br.com.gisa.gisainfocadastro.domain.AutorizacaoExameEntity;
import br.com.gisa.gisainfocadastro.domain.PlanoSaudeIndividualEntity;
import br.com.gisa.gisainfocadastro.domain.SituacaoAutoricacaoExame;
import br.com.gisa.gisainfocadastro.exceptions.ValidationException;
import br.com.gisa.gisainfocadastro.repository.AutorizacaoExameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
@AllArgsConstructor
public class AutorizacaoExameService {

    private final VinculoPlanoService planoService;
    private final AutorizacaoExameRepository repository;
    private final ObjectMapper mapper;
    private final RabbitMessagingTemplate rabbitTemplate;

    public AutorizacaoExameEntity requestAuthorization(AutorizacaoExameEntity request) throws JsonProcessingException {
        Optional<PlanoSaudeIndividualEntity> planoAtivo = planoService.findPlanoAtivo(request.getIdAssociado());
        if (planoAtivo.isEmpty()) throw new ValidationException(("Não existe plano ativo!"));

        request.setDataSolicitacao(LocalDateTime.now());
        request.setCodigoSituacao(SituacaoAutoricacaoExame.PENDENTE.getCodigo());
        AutorizacaoExameEntity entity = repository.save(request);
        this.sendMessage(Amqp.SOLICITACAO_AUTORIZACAO_EXAME_EXCHANGE, entity);
        return entity;
    }

    public Optional<AutorizacaoExameEntity> findByIdSolicitacao(Long idSolicitacao) {
        return repository.findById(idSolicitacao);
    }

    public List<AutorizacaoExameEntity> findByIdAssociado(Long idAssociado) {
        return repository.findByIdAssociadoOrderByDataSolicitacaoDesc(idAssociado);
    }

    private void sendMessage(String exchange, AutorizacaoExameEntity entity) throws JsonProcessingException {
        String payload = mapper.writeValueAsString(entity);
        log.info("Sending message to exchange={}, payload={}", exchange, payload);
        rabbitTemplate.convertAndSend(exchange, "", payload);
    }

}
