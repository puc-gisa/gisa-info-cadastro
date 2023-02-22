package br.com.gisa.gisainfocadastro.controller;

import br.com.gisa.gisainfocadastro.data.EnderecoEntity;
import br.com.gisa.gisainfocadastro.domain.EnderecoRequest;
import br.com.gisa.gisainfocadastro.domain.EnderecoResponse;
import br.com.gisa.gisainfocadastro.service.EnderecoService;
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
@RequestMapping("/enderecos")
@RequiredArgsConstructor
public class EnderecoController {

    private final EnderecoService service;

    private final ModelMapper mapper;

    @GetMapping("/{id}")
    public ResponseEntity<EnderecoResponse> getById(@PathVariable Long id) {
        Optional<EnderecoEntity> endereco = service.findById(id);
        return endereco.map(entity ->
            ok(mapper.map(entity, EnderecoResponse.class))).orElseGet(
            () -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody EnderecoRequest request) {
        EnderecoEntity entity = mapper.map(request, EnderecoEntity.class);
        EnderecoEntity saved = service.create(entity);

        URI location = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path("/{id}")
            .buildAndExpand(saved.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody EnderecoRequest enderecoRequest) {
        EnderecoEntity entity = mapper.map(enderecoRequest, EnderecoEntity.class);
        service.update(id, entity);

        return ResponseEntity.ok().build();
    }

}
