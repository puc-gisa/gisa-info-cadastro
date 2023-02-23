package br.com.gisa.gisainfocadastro.domain.endereco.controller;

import br.com.gisa.gisainfocadastro.domain.endereco.data.EnderecoEntity;
import br.com.gisa.gisainfocadastro.domain.endereco.dto.EnderecoRequest;
import br.com.gisa.gisainfocadastro.domain.endereco.dto.EnderecoResponse;
import br.com.gisa.gisainfocadastro.domain.endereco.service.EnderecoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/associados/{idAssociado}/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    private final ModelMapper mapper;

    @GetMapping
    public ResponseEntity<EnderecoResponse> getById(@PathVariable Long idAssociado) {
        Optional<EnderecoEntity> endereco = service.findByIdAssociado(idAssociado);
        return endereco.map(entity ->
            ok(mapper.map(entity, EnderecoResponse.class))).orElseGet(
            () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@PathVariable Long idAssociado, @Valid @RequestBody EnderecoRequest request) {
        EnderecoEntity entity = mapper.map(request, EnderecoEntity.class);
        EnderecoEntity saved = service.create(idAssociado, entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Void> update(@PathVariable Long idAssociado, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        EnderecoEntity entity = mapper.map(enderecoRequest, EnderecoEntity.class);
        service.update(idAssociado, entity);

        return ResponseEntity.ok().build();
    }

}
