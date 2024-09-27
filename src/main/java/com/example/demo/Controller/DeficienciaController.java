package com.example.demo.Controller;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.demo.Enum.Sexo;
import com.example.demo.Form.Deficiencia.DeficienciaForm;
import com.example.demo.Form.Pessoa.PessoaForm;
import com.example.demo.Model.Categoria;
import com.example.demo.Model.Deficiencia;
import com.example.demo.Model.Pessoa;
import com.example.demo.Repository.CategoriaRepository;
import com.example.demo.Repository.DeficienciaRepository;
import com.example.demo.Repository.PessoaRepository;
import com.example.demo.Service.DeficienciaService;
import com.example.demo.Service.PessoaService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class DeficienciaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private DeficienciaService deficienciaService;
    @Autowired
    private DeficienciaRepository deficienciaRepository;
    
    @GetMapping("/deficiencia")
    public String index(){        
        return "deficiencia/listar";
    }

    @GetMapping("/deficiencia/create")
    public String create(Model model) {

        DeficienciaForm  deficienciaForm = new DeficienciaForm();      
        model.addAttribute("deficienciaForm", deficienciaForm);       
        

        deficienciaForm.setListCategoria(categoriaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);  
        return "deficiencia/create";     

        
    }

    @PostMapping("/deficiencia/create")
    public String create(@Valid DeficienciaForm deficienciaForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
       
        List<Categoria> listCategoria = categoriaRepository.findAll();
        deficienciaForm.setListCategoria(listCategoria);         
       

        model.addAttribute("deficienciaForm", deficienciaForm);       
        
        

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/pessoa/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Salvo com sucesso!");
        deficienciaService.create(deficienciaForm);
       
        
        return "redirect:/pessoa";
    }

        
    }

