package com.example.demo.Form.Pessoa;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;


   
    @NotNull(message = "Preencha o campo nome")
    @Past(message = "Data invalida!")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate nascimento;


    @NotNull(message ="Preeencha o campo sexo.")
    private int sexo; 

    @NotNull(message = "Preencha o campo")
    private Deficiencia deficiencia;
    private List<Deficiencia> listDeficiencia;

   

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
        this.nascimento = pessoa.getNascimento();
        this.sexo = pessoa.getSexo().getCodigo();

        this.deficiencia = pessoa.getDeficiencia();
        this.cep = pessoa.getEndereco().getCep();

        this.uf = pessoa.getEndereco().getEstado().getSigla();
        this.cidade = pessoa.getEndereco().getCidade().getNome();
        this.bairro = pessoa.getEndereco().getBairro().getNome();
        this.logradouro = pessoa.getEndereco().getLogradouro();
        this.numero = pessoa.getEndereco().getNumero();
        this.complemento = pessoa.getEndereco().getComplemento();
    }


   
    @NotBlank(message = "Preencha o CEP")
    @Size(min = 9, max = 9, message = "Preeencha com um CEP válido")
    private String cep;

    @NotNull(message = "Preencha o campo")
    private String uf;

    @NotNull(message = "Preencha o campo")
    private String cidade;

    @NotNull(message = "Preencha o campo")
    private String bairro;

    @NotNull(message = "Preencha o campo")
    private String logradouro;

    @NotNull(message = "Preencha o campo")
    private String numero;

    @NotNull(message = "Preencha o campo")
    private String complemento;

   
        
}  



    
    

