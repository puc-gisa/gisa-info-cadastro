package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.domain.PlanoSaudeIndividualEntity;
import br.com.gisa.gisainfocadastro.dto.VinculoAssociadoIndividualRequest;
import br.com.gisa.gisainfocadastro.dto.VinculoAssociadoIndividualResponse;
import br.com.gisa.gisainfocadastro.service.VinculoPlanoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/vinculo-plano")
@RequiredArgsConstructor
public class VinculoPlanoController {

    private final VinculoPlanoService service;

    private final ModelMapper mapper;

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody VinculoAssociadoIndividualRequest vinculoRequest) {
        PlanoSaudeIndividualEntity entity = mapper.map(vinculoRequest, PlanoSaudeIndividualEntity.class);
        PlanoSaudeIndividualEntity saved = service.create(entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{idAssociado}")
    public List<VinculoAssociadoIndividualResponse> getById(@PathVariable Long idAssociado) {
        List<PlanoSaudeIndividualEntity> planos = service.findByIdAssociado(idAssociado);
        return planos.stream()
            .map(a -> mapper.map(a, VinculoAssociadoIndividualResponse.class))
            .collect(Collectors.toList());
    }
}
