package br.ibm.com.javadesafio.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.ibm.com.javadesafio.entity.PibEntity;
import br.ibm.com.javadesafio.service.PibService;

@RestController
public class Controller {


    @Autowired
    private PibService pibService;
    
    
    @RequestMapping(value = "/pageable", method = RequestMethod.GET)
    public Page <PibEntity> listPageable(Pageable pageable) {
        return pibService.listPageable(pageable);
    }

    //CONSULTAR POR ID
    @RequestMapping(value = "/topicos/{id}", method = RequestMethod.GET)
    public Optional<PibEntity> consultar(@PathVariable Long id) {
        return pibService.findById(id);

    }

    //LISTAR TODOS
    @RequestMapping(value = "/topicos/", method = RequestMethod.GET)
    public List<PibEntity> listarTodos() {
        return pibService.findAll();

    }

    //LISTAR TODOS
    @RequestMapping(value = "/topicos/todos", method = RequestMethod.GET)
    public List<PibEntity> callApi() {
        return pibService.callApi();

    }

    //CONSULTA POR ANO
    @RequestMapping(value = "/topicos/ano/{year}", method = RequestMethod.GET)
    public List<PibEntity> findByYear(@PathVariable Integer year) {
        return pibService.findByYear(year);
    }

    //CONSULTA E SOMA POR ANO
    @RequestMapping(value = "topicos/soma/{year}", method = RequestMethod.GET)
    public Double soma(@PathVariable Integer year) {
        return pibService.soma(year);

    }

    //SALVAR POR ID
    @RequestMapping(value = "/topicos/salvar/{id}", method = RequestMethod.GET)
    public PibEntity saveAll(@RequestBody PibEntity divida) {
        return pibService.save(divida);
    }


    //DELETAR POR ID
    @RequestMapping(value = "/topicos/deleta/{id}", method = RequestMethod.DELETE)
    public void deletar(@PathVariable Long id) {
        pibService.deleteById(id);

    }

    //ATUALIZAR POR ID
    @RequestMapping(value = "/topicos/atualizar/{id}", method = RequestMethod.GET)
    public PibEntity atualizar(@PathVariable Long id,  @RequestParam Double valor) {
         Optional <PibEntity> pibOptional = pibService.findById(id);
         
         if(pibOptional.isPresent()) {
        	 
        	 pibOptional.get().setValor(valor);
        	 return pibService.save(pibOptional.get());
        	 
         }
		return null;
	

    }


}