package com.finangen.services;

import com.finangen.domains.*;
import com.finangen.domains.enums.*;
import com.finangen.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;

@Service
public class DBService {

    @Autowired
    private BancoRepository bancoRepo;

    @Autowired
    private ContaBancariaRepository contaBancariaRepo;

    @Autowired
    private LancamentoRepository lancamentoRepo;

    @Autowired
    private CategoriaRepository categoriaRepo;

    @Autowired
    private PessoaRepository pessoaRepo;

    @Autowired
    private AdminRepository adminRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @Autowired
    private PasswordEncoder encoder;

    public void initDB(){

        //  Banco
        Banco banco1 = new Banco(null,"Banco do Brasil", Status.ATIVO);
        Banco banco2 = new Banco(null,"Itau", Status.INATIVO);

        bancoRepo.save(banco1);
        bancoRepo.save(banco2);

        //  Usuario
        Usuario usuario1 = new Usuario(null,"João Mamão","00.000.000-3","000.000.000-3","(00)00000 0003","felipezocal@gmail.com",
                encoder.encode("003"),Status.ATIVO);

        Usuario usuario2 = new Usuario(null,"Italo Parachoque","00.000.000-4","000.000.000-4","(00)00000 0004","arthurzocal@gmail.com",
                encoder.encode("004"),Status.INATIVO);

        usuarioRepo.save(usuario1);
        usuarioRepo.save(usuario2);

        //  Admin
        Admin admin1 = new Admin(null,"Pedro Costa","00.000.000-1","000.000.000-1","(00)00000 0001","pedrocosta@gmail.com",
                encoder.encode("001"),Status.ATIVO);

        Admin admin2 = new Admin(null,"Gabriel Barriga","00.000.000-2","000.000.000-2","(00)00000 0002","pedrobarriga@gmail.com",
                encoder.encode("002"),Status.INATIVO);

        adminRepo.save(admin1);
        adminRepo.save(admin2);

        //  Conta Bancaria
        ContaBancaria contaBancaria1 = new ContaBancaria(null,"Conta Corrente","1111","11111-1","1111111",
                new BigDecimal("32000.00"), TipoConta.CONTACORRENTE,usuario1,banco1);

        ContaBancaria contaBancaria2 = new ContaBancaria(null,"Conta Investimento","2222","22222-2","2222222",
                new BigDecimal("32000.00"), TipoConta.CONTAINVESTIMENTO,usuario1,banco1);

        ContaBancaria contaBancaria3 = new ContaBancaria(null,"Cartão de Credito","3333","33333-3","3333333",
                new BigDecimal("32000.00"), TipoConta.CARTAOCREDITO,usuario1,banco1);

        ContaBancaria contaBancaria4 = new ContaBancaria(null,"Conta Alimentação","4444","44444-4","4444444",
                new BigDecimal("32000.00"), TipoConta.ALIMENTACAO,usuario1,banco1);

        ContaBancaria contaBancaria5 = new ContaBancaria(null,"Conta Poupança","5555","55555-5","5555555",
                new BigDecimal("32000.00"), TipoConta.CONTAPOUPANCA,usuario1,banco1);

        contaBancariaRepo.save(contaBancaria1);
        contaBancariaRepo.save(contaBancaria2);
        contaBancariaRepo.save(contaBancaria3);
        contaBancariaRepo.save(contaBancaria4);
        contaBancariaRepo.save(contaBancaria5);

        //  Categoria
        Categoria categoria1 = new Categoria(null,"Compras", usuario1);
        Categoria categoria2 = new Categoria(null,"Contas", usuario2);

        categoriaRepo.save(categoria1);
        categoriaRepo.save(categoria2);


        //  Lancamento
        Lancamento lancamento1 = new Lancamento(null,"Compra de roupas",LocalDate.now(),LocalDate.now(),LocalDate.now(),
                new BigDecimal("320.00"), TipoLancamento.CREDITO, Situacao.ABERTO,usuario1,categoria1,contaBancaria1);

        Lancamento lancamento2 = new Lancamento(null,"Conta de agua",LocalDate.now(),LocalDate.now(),LocalDate.now(),
                new BigDecimal("320.00"), TipoLancamento.DEBITO, Situacao.BAIXADO,usuario1,categoria1,contaBancaria2);

        Lancamento lancamento3 = new Lancamento(null,"Compras do mes",LocalDate.now(),LocalDate.now(),LocalDate.now(),
                new BigDecimal("320.00"), TipoLancamento.CREDITO, Situacao.ATRASADO,usuario1,categoria1,contaBancaria1);

        lancamentoRepo.save(lancamento1);
        lancamentoRepo.save(lancamento2);
        lancamentoRepo.save(lancamento3);


    }

}
