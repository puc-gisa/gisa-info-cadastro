package br.com.gisa.gisainfocadastro.data;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "associado")
@NoArgsConstructor
public class AssociateEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 100)
    private String firstName;

    @Column(length = 300)
    private String lastName;

    @Column
    private LocalDate birthDate;

    @Column
    private String email;

}
