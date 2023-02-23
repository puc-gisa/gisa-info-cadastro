package br.com.gisa.gisainfocadastro.domain.associado.data;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

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
    @CreationTimestamp
    private LocalDateTime dataCriacao;

    @Column
    @UpdateTimestamp
    private LocalDateTime dataAtualizacao;

}
