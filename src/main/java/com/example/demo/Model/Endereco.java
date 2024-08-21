package com.example.demo.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "endereco")
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "logradouro" ,nullable = false)
    private String nome;

    @Column(name = "cep", nullable = false, length = 9)
    private String cep;

    @Column(name = "numero", nullable = true)
    private String numero;

    @Column(name = "complemento", nullable = true)
    private String complemento;

    @ManyToOne
    @JoinColumn(name = "bairro_id")
    private Bairro bairro;
}