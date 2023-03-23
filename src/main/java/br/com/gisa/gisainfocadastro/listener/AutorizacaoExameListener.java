package br.com.gisa.gisainfocadastro.listener;

import br.com.gisa.gisainfocadastro.config.Amqp;
import br.com.gisa.gisainfocadastro.domain.AutorizacaoExameEntity;
import br.com.gisa.gisainfocadastro.listener.message.AutorizacaoExameResponseMessage;
import br.com.gisa.gisainfocadastro.repository.AutorizacaoExameRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.function.Consumer;

@Component
@Slf4j
@AllArgsConstructor
public class AutorizacaoExameListener {

    private AutorizacaoExameRepository repository;
    private ObjectMapper mapper;

    @RabbitListener(queues = Amqp.RESPOSTA_AUTORIZACAO_EXAME_QUEUE)
    public void onMessage(String payload) throws JsonProcessingException {
        log.info("Receiving message={}", payload);
        AutorizacaoExameResponseMessage message = mapper.readValue(payload, AutorizacaoExameResponseMessage.class);
        Optional<AutorizacaoExameEntity> entity = repository.findById(message.getId());

        Runnable onError = () -> log.error("Not found entity for message={}", payload);

        Consumer<AutorizacaoExameEntity> onSuccess = toSave -> {
            toSave.setCodigoSituacao(message.getCodigoSituacao());
            toSave.setJustificativa(message.getJustificativa());
            toSave.setDataValidade(message.getDataValidade());
            repository.save(toSave);
        };

        entity.ifPresentOrElse(onSuccess, onError);
    }

}
