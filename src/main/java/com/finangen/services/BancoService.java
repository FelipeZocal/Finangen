package com.finangen.services;

import com.finangen.domains.Banco;
import com.finangen.domains.dtos.BancoDTO;
import com.finangen.repositories.BancoRepository;

import com.finangen.services.exceptions.DataIntegrityViolationException;
import com.finangen.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BancoService {

    @Autowired
    private BancoRepository bancoRepo;

    public List<BancoDTO> findAll(){
        return bancoRepo.findAll().stream()
                .map(obj -> new BancoDTO(obj))
                .collect(Collectors.toList());
    }

    public Banco findByIdBanco(Long idBanco){
        Optional<Banco> obj = bancoRepo.findByIdBanco(idBanco);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID:"+idBanco));
    }

    public Banco findByRazaoSocial(String razaoSocial) {
        Optional<Banco> obj = bancoRepo.findByRazaoSocial(razaoSocial);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, Razão Social:" + razaoSocial));
    }

    public Banco create(BancoDTO objDto){
        objDto.setIdBanco(null);
        ValidaBanco(objDto);
        Banco newObj = new Banco(objDto);
        return bancoRepo.save(newObj);
    }

    public Banco update(Long idBanco, BancoDTO objDto){
        objDto.setIdBanco(idBanco);
        Banco oldObj = findByIdBanco(idBanco);
        ValidaBanco(objDto);
        oldObj = new Banco(objDto);
        return bancoRepo.save(oldObj);
    }

    public void delete(Long idBanco) {
        Banco obj = findByIdBanco(idBanco);
        if (obj.getContaBancarias().size() > 0) {
            throw new DataIntegrityViolationException("Banco não pode ser deletado pois possui cadastrados ativos");
        }
        bancoRepo.deleteById(idBanco);
    }


    public void ValidaBanco(BancoDTO dto){
        Optional<Banco> obj = bancoRepo.findByRazaoSocial((dto.getRazaoSocial()));
        if (obj.isPresent() && obj.get().getIdBanco() != dto.getIdBanco()){
            throw  new DataIntegrityViolationException("Razão Socical já cadastrada");
        }
    }

}
