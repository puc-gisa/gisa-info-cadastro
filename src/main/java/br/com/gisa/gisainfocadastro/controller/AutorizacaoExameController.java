package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.domain.AutorizacaoExameEntity;
import br.com.gisa.gisainfocadastro.dto.AutorizacaoExameRequest;
import br.com.gisa.gisainfocadastro.dto.AutorizacaoExameResponse;
import br.com.gisa.gisainfocadastro.service.AutorizacaoExameService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/autorizacao-exame")
@AllArgsConstructor
public class AutorizacaoExameController {

    private final AutorizacaoExameService service;

    private final ModelMapper modelMapper;

    @PostMapping("/")
    public ResponseEntity<AutorizacaoExameResponse> requestAuthorization(@RequestBody @Valid AutorizacaoExameRequest exameRequest) throws JsonProcessingException {
        AutorizacaoExameEntity entity = modelMapper.map(exameRequest, AutorizacaoExameEntity.class);
        AutorizacaoExameEntity saved = service.requestAuthorization(entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).body(modelMapper.map(saved, AutorizacaoExameResponse.class));
    }

    @GetMapping("/{idSolicitacao}")
    public ResponseEntity<AutorizacaoExameResponse> getById(@PathVariable Long idSolicitacao) {
        Optional<AutorizacaoExameEntity> solicatacao = service.findByIdSolicitacao(idSolicitacao);
        return solicatacao.map(entity ->
                ok(modelMapper.map(entity, AutorizacaoExameResponse.class)))
            .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/associado/{idAssociado}")
    public List<AutorizacaoExameResponse> getByIdAssociado(@PathVariable Long idAssociado) {
        List<AutorizacaoExameEntity> solicatacoes = service.findByIdAssociado(idAssociado);
        return solicatacoes.stream()
            .map(entity -> modelMapper.map(entity, AutorizacaoExameResponse.class))
            .collect(Collectors.toList());
    }

}
