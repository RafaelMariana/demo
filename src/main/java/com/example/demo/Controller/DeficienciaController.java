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

import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@Controller
public class DeficienciaController {
    
    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private DeficienciaService deficienciaService;

    @Autowired
    private DeficienciaRepository deficienciaRepository;

    @Operation(description = "Busca todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )
  
    
    @GetMapping("/deficiencia")
    public String index(Model model, @RequestParam("display") Optional<String> display){
        String finalDisplay = display.orElse("true");

        List<Deficiencia> deficiencias = deficienciaRepository.findByAtivo(Boolean.valueOf(finalDisplay));
        model.addAttribute("deficiencia", deficiencias);     
        return "deficiencia/listar";
    }
    @Operation(description = "Busca todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )

    @GetMapping("/deficiencia/create")
    public String create(Model model) {
        DeficienciaForm  deficienciaForm = new DeficienciaForm();   

        deficienciaForm.setListCategoria(categoriaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);  
        return "deficiencia/create"; 
    }
    @Operation(description = "Busca todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )

    @PostMapping("/deficiencia/create")
    public String create(@Valid DeficienciaForm deficienciaForm, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
       
        List<Categoria> listCategoria = categoriaRepository.findAll();
        deficienciaForm.setListCategoria(listCategoria);     

        model.addAttribute("deficienciaForm", deficienciaForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/deficiencia/create";
        }

        redirectAttributes.addFlashAttribute("successMessage", "Salvo com sucesso!");
        deficienciaService.create(deficienciaForm);
        
        return "redirect:/deficiencia";
    }
    @Operation(description = "Atualiza todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )
    

    @GetMapping("/deficiencia/update/{id}")
    public String update(@PathVariable Long id,Model model){
        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);

        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.orElseThrow());
        deficienciaForm.setListCategoria(categoriaRepository.findAll());
        model.addAttribute("deficienciaForm", deficienciaForm);
        model.addAttribute("id", deficiencia.orElseThrow().getId());
        return "/deficiencia/update";   
    }
    @Operation(description = "Salvar todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )

    @PostMapping("/deficiencia/update/{id}")
    public String update(@PathVariable Long id,@Valid DeficienciaForm deficienciaForm,BindingResult bindingResult,Model model, RedirectAttributes redirectAttributes){
        deficienciaForm.setListCategoria(categoriaRepository.findAll());
        model.addAttribute("deficienciaForm",deficienciaForm);

        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/deficiencia/update";
                    
        }

        deficienciaService.update(deficienciaForm, id); 
        redirectAttributes.addFlashAttribute("successMessage", "Alterado com sucesso!");
        return "redirect:/deficiencia";   
    }
    @Operation(description = "Remover todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )


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
        return "redirect:/deficiencia";      
    } 
    @Operation(description = "Visualiza todas as deficiências")
    @ApiResponses( value = {
        @ApiResponse(responseCode = "200", description = "tudo certo"),
        @ApiResponse(responseCode = "400", description = "não encontrado deficência"),
        @ApiResponse(responseCode = "404", description = "Página não encontrada")
       
    } )

    @GetMapping("/deficiencia/visualizar/{id}")
    public String visualizar(@PathVariable Long id, Model model){
        Optional<Deficiencia> deficiencia = deficienciaRepository.findById(id);

        DeficienciaForm deficienciaForm = new DeficienciaForm(deficiencia.get());

        deficienciaForm.setListCategoria(categoriaRepository.findAll());

        model.addAttribute("deficienciaForm", deficienciaForm);
        model.addAttribute("id", deficiencia.get().getId());

        return "/deficiencia/visualizar";
    }

}     