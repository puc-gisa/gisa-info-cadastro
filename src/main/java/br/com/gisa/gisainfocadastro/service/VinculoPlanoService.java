package br.com.gisa.gisainfocadastro.service;

import br.com.gisa.gisainfocadastro.domain.PlanoSaudeIndividualEntity;
import br.com.gisa.gisainfocadastro.domain.TipoCliente;
import br.com.gisa.gisainfocadastro.domain.TipoPlano;
import br.com.gisa.gisainfocadastro.exceptions.ValidationException;
import br.com.gisa.gisainfocadastro.repository.PlanoSaudeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class VinculoPlanoService {

    private final PlanoSaudeRepository repository;
    private final AssociadoService associadoService;
    private final TipoPlanoService tipoPlanoService;

    public PlanoSaudeIndividualEntity create(PlanoSaudeIndividualEntity entity) {
        validate(entity);
        entity.setDataInicio(LocalDate.now());
        entity.setDataFim(null);
        return repository.save(entity);
    }

    public List<PlanoSaudeIndividualEntity> findByIdAssociado(Long idAssociado) {
        return repository.findByIdAssociado(idAssociado);
    }

    private void validate(PlanoSaudeIndividualEntity associadoEntity) {
        this.validateAssociado(associadoEntity.getIdAssociado());
        this.validateNovoPlano(associadoEntity.getIdAssociado());
        this.validateTipoPlanoIndividual(associadoEntity.getCodigoTipoPlano());
    }

    private void validateTipoPlanoIndividual(Integer codigoTipoPlano) {
        Optional<TipoPlano> tipoPlano = tipoPlanoService.findByCodigoTipoPlano(codigoTipoPlano);
        if (tipoPlano.isEmpty()) {
            throw new ValidationException("Codigo do plano não encontrado!");
        }

        tipoPlano.ifPresent(tipo -> {
                if (!tipo.getTipoClientes().contains(TipoCliente.INDIVIDUAL)) {
                    throw new ValidationException("Plano deve ser individual!");
                }
            }
        );
    }

    private void validateAssociado(Long idAssociado) {
        if (!associadoService.exists(idAssociado)) {
            throw new ValidationException("Associado não encontrado!");
        }
    }

    private void validateNovoPlano(Long idAssociado) {
        List<PlanoSaudeIndividualEntity> planos = findByIdAssociado(idAssociado);

        planos.stream()
            .filter(PlanoSaudeIndividualEntity::isAtivo)
            .findAny()
            .ifPresent(entity -> {
                throw new ValidationException("Já existe um plano vinculado!");
            });
    }

    public Optional<PlanoSaudeIndividualEntity> findPlanoAtivo(Long idAssociado) {
        List<PlanoSaudeIndividualEntity> planos = findByIdAssociado(idAssociado);

        return planos.stream()
            .filter(PlanoSaudeIndividualEntity::isAtivo)
            .findAny();
    }
}
