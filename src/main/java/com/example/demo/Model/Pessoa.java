package com.example.demo.Model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
@Entity
@Table(name = "pessoa")
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;


    @Column(name = "nome", nullable = false)
    private String nome;

    public Pessoa(String nome){
        this.nome = nome;
    }

//oi
    // @Column(name = "cpf", nullable = false, length = 14)
    // private String cpf;

    // @Column(name = "email", nullable = false)
    // private String email;

    // @Column(name = "telefone", nullable = false)
    // private String telefone;

    // @Column(name = "nome_responsavel", nullable = false)
    // private String nome_responsavel;

    // @Column(name = "aprovado", nullable = false)
    // private String aprovado;

    // @Column(name = "data_nascimento", nullable = false)
    // private String data_nascimento;

    // @Column(name = "grau", nullable = false)
    // private String grau;


    // @ManyToOne
    // private Endereco endereco;

    // @ManyToMany
    // private List<Deficiencia> deficiencias;
    







}

    
    
    

