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
import com.example.demo.Form.Pessoa.DeficienciaForm;
import com.example.demo.Form.Pessoa.PessoaForm;
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
    private PessoaRepository pessoaRepository;

    @Autowired
    private DeficienciaRepository deficienciaRepository;

    @Autowired
    private DeficienciaService pessoaService;

    @GetMapping("/deficiencia")
    public String index(Model model, @RequestParam("display") Optional<String> display){
        String finalDisplay = display.orElse("true");

        List<Deficiencia> pessoas = deficienciaRepository.findAll(Boolean.valueOf(finalDisplay));      
        model.addAttribute("deficiencia", deficiencia);

        return "deficiencia/listar";
    }
    
    

    @GetMapping("/deficiencia/create")
    public String create(Model model) {
        DeficienciaForm deficienciaForm = new DeficienciaForm();


        deficienciaForm.setListDeficiencia(deficienciaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);
        System.out.println(categoriaRepository.getCodigo());

        return "deficiencia/create";
    }
    
    @PostMapping("/deficiencia/create")
    public String create(@Valid  DeficienciaForm deficienciaForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes) {        
        ListCrudRepository<Deficiencia, Long> categoriaRepository;
        deficienciaForm.setListDeficiencia(categoriaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);
   



        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/deficiencia/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Salvo com sucesso!");
        pessoaService.create(deficienciaForm);
       
        
        return "redirect:/deficiencia";
    }

    @GetMapping("/deficiencia/update/{id}")
    public String update(@PathVariable Long id, Model model){
        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);

        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.orElseThrow());
        deficienciaForm.setListCategoria(deficienciaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);
        model.addAttribute("id", deficiencia.orElseThrow().getId());

        return "/deficiencia/update";
    }

    @GetMapping("/deficiencia/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model){
        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);

        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.get());

        deficienciaForm.setListCategoria(deficienciaRepository.findAll());

        model.addAttribute("deficienciaForm", deficienciaForm);
        model.addAttribute("id", deficiencia.get().getId());

        return "/deficiencia/visualizar";
    }
    
    @PostMapping("/deficiencia/update/{id}")
    public String update(
        @PathVariable Long id, 
        @Valid DeficienciaForm deficienciaForm, 
        BindingResult bindingResult, 
        Model model, 
        RedirectAttributes redirectAttributes
    ){   
        deficienciaForm.setListCategoria(categoriaRepository.findAll());

        model.addAttribute("deficienciaForm",deficienciaForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/deficiencia/update";
        }

        pessoaService.update(deficienciaForm, id); 

      

        redirectAttributes.addFlashAttribute("successMessage", "Alterado com sucesso!");
       

        return "redirect:/deficiencia";
    }
    
    @GetMapping("/deficiencia/remover/{id}")
    public String remover(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        Optional<Deficiencia> deficiencia = this.deficienciaRepository.findById(id);
        Deficiencia deficienciaModel = deficiencia.get();

        if(deficienciaModel.isAtivo()){
            deficienciaModel.setAtivo(false);
            redirectAttributes.addFlashAttribute("successMessage","Excluído com sucesso!");

        }else{
            deficienciaModel.setAtivo(true);
            redirectAttributes.addFlashAttribute("successMessage","Ativado com sucesso!");
        }

        this.deficienciaRepository.save(deficienciaModel);
        return "redirect:/deficiencia/";
          
    }
    
    
}