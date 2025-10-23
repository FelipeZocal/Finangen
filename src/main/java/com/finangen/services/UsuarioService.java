package com.finangen.services;

import com.finangen.domains.Usuario;
import com.finangen.domains.dtos.UsuarioDTO;
import com.finangen.repositories.UsuarioRepository;
import com.finangen.services.exceptions.DataIntegrityViolationException;
import com.finangen.services.exceptions.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    public List<UsuarioDTO> findAll(){
        return usuarioRepo.findAll().stream()
                .map(obj ->new UsuarioDTO(obj)).collect(Collectors.toList());
    }

    public Usuario findById(Long id){
        Optional<Usuario> obj = usuarioRepo.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID:"+ id));
    }

    public Usuario findByCpf(String cpf){
        Optional<Usuario> obj = usuarioRepo.findByCpf(cpf);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, CPF:"+ cpf));
    }

    public Usuario findByEmail(String email){
        Optional<Usuario> obj = usuarioRepo.findByEmail(email);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, EMAIL:"+ email));
    }

    public Usuario create (UsuarioDTO objDto){
        objDto.setId(null);
        objDto.setSenha(encoder.encode(objDto.getSenha()));
        ValidaPorCPFeEmail(objDto);
        Usuario newObj = new Usuario(objDto);
        return usuarioRepo.save(newObj);
    }

    public Usuario update(Long id, UsuarioDTO objDto){
        objDto.setId(id);
        Usuario oldObj = findById(id);
        ValidaPorCPFeEmail(objDto);
        oldObj = new Usuario(objDto);
        return usuarioRepo.save(oldObj);
    }

    public void delete(Long id) {
        Usuario obj = findById(id);
        if (obj.getContaBancarias().size() > 0 && obj.getCategorias().size() > 0 && obj.getLancamentos().size() >0) {
            throw new DataIntegrityViolationException("Usuário não pode ser deletado pois possui cadastros ativos");
        }
        usuarioRepo.deleteById(id);
    }

    private void ValidaPorCPFeEmail(UsuarioDTO objDto) {
        Optional<Usuario> obj = usuarioRepo.findByCpf(objDto.getCpf());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema");
        }

        obj = usuarioRepo.findByEmail(objDto.getEmail());
        if (obj.isPresent() && obj.get().getId() != objDto.getId()) {
            throw new DataIntegrityViolationException("Email já cadastrado no sistema");
        }
    }
}

