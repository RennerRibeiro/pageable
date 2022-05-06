package br.ibm.com.javadesafio.service;

import java.util.List;

import java.util.Optional;

import br.ibm.com.javadesafio.entity.PibEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.ibm.com.javadesafio.repository.PibRepository;


@Service
public class PibService {

    @Autowired
    private PibRepository pibRepository;

    public Optional<PibEntity> listar(Long id) {
        return pibRepository.findById(id);

    }

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private HttpHeaders httpHeaders;

    private static final String DIVIDA_API = "https://api.bcb.gov.br/dados/serie/bcdata.sgs.4505/dados?formato=json";


    public List<PibEntity> callApi() {

        httpHeaders.setAccept(List.of(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<>(httpHeaders);
        ResponseEntity<List<PibEntity>> response = restTemplate.exchange(DIVIDA_API, HttpMethod.GET, entity, new ParameterizedTypeReference<>() {
        });

        List<PibEntity> listaDivida = response.getBody();

        if (listaDivida != null) {
            return pibRepository.saveAll(listaDivida);
        }
        throw new RuntimeException("Não foi possível acessar a API do Banco Central");

    }


    public Optional<PibEntity> findById(Long id) {
        return pibRepository.findById(id);

    }


    public List<PibEntity> findAll() {
        return pibRepository.findAll();

    }


    public List<PibEntity> findByYear(Integer year) {
        return pibRepository.findByYear(year);
    }


    public Double soma(Integer year) {
        List<PibEntity> byYear = pibRepository.findByYear(year);

        double soma = 0;

        for (int i = 0; i < byYear.size(); i++) {
            soma += byYear.get(i).getValor();
        }
        return soma;
    }


    public PibEntity save(PibEntity divida) {
        return pibRepository.save(divida);
    }


    public void deleteById(Long id) {
        pibRepository.deleteById(id);

    }

    public Optional<PibEntity> atualizar(Long id) {
        return pibRepository.findById(id);

    }
    
    public Page<PibEntity> listPageable(Pageable pageable) {
		return pibRepository.findAll(pageable);
    }
	
    

}
			

