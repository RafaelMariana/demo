package com.example.demo.Form.Pessoa;

import java.time.LocalDate;
import java.util.List;

import com.example.demo.Enum.Sexo;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.DeficienciaRepository;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
public class PessoaForm {

    @NotBlank(message = "Preencha o campo nome.")
    private String nome;


   
    @NotNull(message = "Preencha o campo nome")
    @Past(message = "Data invalida!")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate nascimento;


    @NotNull(message ="Preeencha o campo sexo.")
    private int sexo; 

    @NotNull(message = "Preencha o campo")
    private Deficiencia deficiencia;
    private List<Deficiencia> listDeficiencia;



    public Pessoa toEntity(){
        Sexo sexo = Sexo.fromCodigo(this.sexo);
        Pessoa pessoa = new Pessoa(nome, nascimento, sexo);
        pessoa.setDeficiencia(deficiencia);

        return pessoa;
    }

    public PessoaForm(Pessoa pessoa){
        this.nome = pessoa.getNome();
    }

    public void setDeficiencia(){

    }
    public void setListDeficiencia(DeficienciaRepository repository){
        this.listDeficiencia = repository.findAll();
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


    
    

