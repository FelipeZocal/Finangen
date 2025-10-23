package com.finangen.services;

import com.finangen.domains.Admin;
import com.finangen.domains.dtos.AdminDTO;
import com.finangen.repositories.AdminRepository;
import com.finangen.services.exceptions.DataIntegrityViolationException;
import com.finangen.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AdminService {
    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private PasswordEncoder encoder;

    public  List<AdminDTO> findAll(){
        return adminRepo.findAll().stream()
                .map(obj ->new AdminDTO(obj)).collect(Collectors.toList());
    }

    public Admin findById(Long id){
        Optional<Admin> obj = adminRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID:"+ id));
    }

    public Admin findByCpf(String cpf){
        Optional<Admin> obj = adminRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, CPF:"+ cpf));
    }

    public Admin findByEmail(String email){
        Optional<Admin> obj = adminRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, EMAIL:"+ email));
    }

    public Admin create (AdminDTO objDto){
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        ValidaPorCPFeEmail(objDto);
        Admin newObj = new Admin(objDto);
        return adminRepo.save(newObj);
    }

    public Admin update(Long id, AdminDTO objDto){
        objDto.setId(id);
        Admin oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Admin(objDto);
        return adminRepo.save(oldObj);
    }

    public void delete(Long id) {
        Admin obj = findById(id);
        if (obj.getContaBancarias().size() > 0 && obj.getCategorias().size() > 0 && obj.getLancamentos().size() >0) {
            throw new DataIntegrityViolationException("Usuário não pode ser deletado pois possui cadastros ativos");
        }
        adminRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(AdminDTO objDto) {
        Optional<Admin> obj = adminRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = adminRepo.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}
