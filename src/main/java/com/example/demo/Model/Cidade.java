package com.example.demo.Model;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.ManyToAny;

import jakarta.annotation.Generated;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cidade")
public class Cidade {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "ativo",nullable = false)
    @ColumnDefault("true")
    private boolean ativo = true;

    @ManyToOne
    @JoinColumn(name = "estado_id")
    private Estado estado;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;
 


}
