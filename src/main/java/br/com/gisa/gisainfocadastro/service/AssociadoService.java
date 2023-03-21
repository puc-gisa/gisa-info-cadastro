package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.config.Amqp;
import br.com.gisa.gisainfocadastro.domain.AssociadoEntity;
import br.com.gisa.gisainfocadastro.domain.EnderecoEntity;
import br.com.gisa.gisainfocadastro.exceptions.NotFoundException;
import br.com.gisa.gisainfocadastro.exceptions.ValidationException;
import br.com.gisa.gisainfocadastro.repository.AssociadoRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AssociadoService {

    private final AssociadoRepository repository;
    private final ObjectMapper mapper;
    private final RabbitMessagingTemplate rabbitTemplate;

    public Optional<AssociadoEntity> findById(Long idAssociado) {
        return repository.findById(idAssociado);
    }

    public boolean exists(Long idAssociado) {
        return repository.existsById(idAssociado);
    }

    public List<AssociadoEntity> findAll() {
        return repository.findAll();
    }

    public AssociadoEntity create(AssociadoEntity associadoEntity) throws JsonProcessingException {
        validateNewAssociado(associadoEntity);
        associadoEntity.getEndereco().setAssociado(associadoEntity);
        associadoEntity.setDataCriacao(LocalDateTime.now());
        AssociadoEntity save = repository.save(associadoEntity);
        this.sendMessage(Amqp.ASSOCIADO_NOVO_EXCHANGE, save);
        return save;
    }

    public AssociadoEntity update(Long id, AssociadoEntity request) throws JsonProcessingException {
        AssociadoEntity associadoToSave = findById(id).orElseThrow(NotFoundException::new);
        log.info("Atualizando associado com id={}", id);

        associadoToSave.setNome(request.getNome());
        associadoToSave.setDataNascimento(request.getDataNascimento());
        EnderecoEntity enderecoToSave = associadoToSave.getEndereco();
        enderecoToSave.setLogradouro(request.getEndereco().getLogradouro());
        enderecoToSave.setNumero(request.getEndereco().getNumero());
        enderecoToSave.setComplemento(request.getEndereco().getComplemento());
        enderecoToSave.setBairro(request.getEndereco().getBairro());
        enderecoToSave.setCidade(request.getEndereco().getCidade());
        enderecoToSave.setEstado(request.getEndereco().getEstado());
        associadoToSave.setDataAtualizacao(LocalDateTime.now());

        AssociadoEntity updated = repository.save(associadoToSave);
        this.sendMessage(Amqp.ASSOCIADO_ATUALIZADO_EXCHANGE, updated);
        return updated;
    }

    private void sendMessage(String exchange, AssociadoEntity associadoEntity) throws JsonProcessingException {
        String payload = mapper.writeValueAsString(associadoEntity);
        log.info("Sending message to exchange={}, payload={}", exchange, payload);
        rabbitTemplate.convertAndSend(exchange, "", payload);
    }

    private void validateNewAssociado(AssociadoEntity associadoEntity) {
        this.validateUniqueCpf(associadoEntity.getCpf());
        this.validateUniqueEmail(associadoEntity.getEmail());
    }

    private void validateUniqueEmail(String email) {
        Optional<AssociadoEntity> associado = repository.findByEmail(email);
        associado.ifPresent(entity -> {
            throw new ValidationException("Já existe um associado com o email: " + email);
        });
    }

    private void validateUniqueCpf(String cpf) {
        Optional<AssociadoEntity> associado = repository.findByCpf(cpf);
        associado.ifPresent(entity -> {
            throw new ValidationException("Já existe um associado com o cpf: " + cpf);
        });
    }

}
