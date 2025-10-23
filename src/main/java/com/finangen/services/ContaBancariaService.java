package com.finangen.services;
import com.finangen.domains.ContaBancaria;
import com.finangen.domains.dtos.ContaBancariaDTO;
import com.finangen.repositories.ContaBancariaRepository;

import com.finangen.services.exceptions.DataIntegrityViolationException;
import com.finangen.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ContaBancariaService {

    @Autowired
    private ContaBancariaRepository contaBancariaRepo;

    public List<ContaBancariaDTO> findAll(){
        return contaBancariaRepo.findAll().stream()
                .map(obj -> new ContaBancariaDTO(obj))
                .collect(Collectors.toList());
    }

    public ContaBancaria findByIdConta(Long idConta){
        Optional<ContaBancaria> obj =  contaBancariaRepo.findByIdConta(idConta);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID:" + idConta));
    }

    public ContaBancaria create(ContaBancariaDTO objDto){
        objDto.setIdConta(null);
        ContaBancaria newObj = new ContaBancaria(objDto);
        return contaBancariaRepo.save(newObj);
    }

    public ContaBancaria update(Long idConta, ContaBancariaDTO objDto){
        objDto.setIdConta(idConta);
        ContaBancaria oldObj = findByIdConta(idConta);
        oldObj = new ContaBancaria(objDto);
        return contaBancariaRepo.save(oldObj);
    }

    public void delete(Long idConta) {
        ContaBancaria obj = findByIdConta(idConta);
        if (obj.getLancamentos().size() > 0) {
            throw new DataIntegrityViolationException("Conta Bancaria não pode ser deletada pois possui cadastrados ativos");
        }
        contaBancariaRepo.deleteById(idConta);
    }


}
