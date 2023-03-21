package br.com.gisa.gisainfocadastro.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "associado")
@NoArgsConstructor
public class AssociadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 255)
    private String nome;

    @Column
    private LocalDate dataNascimento;

    @Column(unique = true)
    private String cpf;

    @Column(unique = true)
    private String email;

    @Column
    private LocalDateTime dataCriacao;

    @Column
    private LocalDateTime dataAtualizacao;

    @OneToOne(mappedBy = "associado", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private EnderecoEntity endereco;

}
