package com.mantenedora.program.config;

import com.mantenedora.program.model.Conta;
import com.mantenedora.program.model.Endereco;
import com.mantenedora.program.model.Mantenedora;
import com.mantenedora.program.model.Telefone;
import com.mantenedora.program.model.enums.TipoConta;
import com.mantenedora.program.repositories.ContaRepository;
import com.mantenedora.program.repositories.EnderecoRepository;
import com.mantenedora.program.repositories.MantenedoraRepository;
import com.mantenedora.program.repositories.TelefoneRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.util.Arrays;

@Configuration
@Profile("test")
public class TesteConfig implements CommandLineRunner {
    @Autowired
    private MantenedoraRepository mantenedoraRepository;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    @Override
    public void run(String... args) throws Exception {
        Endereco endereco = new Endereco(null, "Rua Nova", "932", "42850-000","Genaro", "BA", "Dias Dávila");

        Telefone telefone = new Telefone(null, "71", "7198730-8769");
        Telefone telefone1 = new Telefone(null, "91", "7198730-0000");
        Telefone telefone2 = new Telefone(null, "82", "7198730-3333");

        Conta conta = new Conta(null, "391", "Banco do Brazil", "3091-0", "57897-9", TipoConta.CORRENTE);
        Conta conta1 = new Conta(null, "361", "Nubank SA", "0001", "2964455-9", TipoConta.POPANCA);

        Mantenedora mantenedora = new Mantenedora(null, "Hospital Geral", null, null, endereco);

        this.telefoneRepository.saveAll(Arrays.asList(telefone, telefone1, telefone2));
        this.mantenedoraRepository.save(mantenedora);
        this.contaRepository.saveAll(Arrays.asList(conta, conta1));
        this.enderecoRepository.save(endereco);


        mantenedora.getContas().add(conta);
        mantenedora.getContas().add(conta1);

        this.mantenedoraRepository.save(mantenedora);

        mantenedora.getTelefones().add(telefone);
        mantenedora.getTelefones().add(telefone1);
        mantenedora.getTelefones().add(telefone2);

        this.mantenedoraRepository.save(mantenedora);
    }
}
