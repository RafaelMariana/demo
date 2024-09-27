package com.example.demo.Service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.demo.Form.Deficiencia.DeficienciaForm;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Repository.DeficienciaRepository;

public class DeficienciaService {

    @Autowired
    private DeficienciaRepository deficienciaRepository;

    public Deficiencia create(DeficienciaForm deficienciaform){

        Deficiencia deficiencia = new Deficiencia();

        deficiencia.setNome(deficienciaform.getNome());
        deficiencia.setCategoria(deficienciaform.getCategoria());

        this.deficienciaRepository.save(deficiencia);
        return deficiencia;
    }

    

}
