package com.mantenedora.program.services;

import com.mantenedora.program.DTO.MantenedoraDTO;
import com.mantenedora.program.model.Conta;
import com.mantenedora.program.model.Endereco;
import com.mantenedora.program.model.Mantenedora;
import com.mantenedora.program.model.Telefone;
import com.mantenedora.program.repositories.ContaRepository;
import com.mantenedora.program.repositories.MantenedoraRepository;
import com.mantenedora.program.repositories.TelefoneRepository;
import com.mantenedora.program.services.exception.ObjectNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MantenedoraService {
    @Autowired
    private MantenedoraRepository mantenedoraRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TelefoneRepository telefoneRepository;

    public List<Mantenedora> listaMantenedora() {

        return this.mantenedoraRepository.findAll();
    }

    /*
        Cadastrando Objeto com outros objetos em seu corpo.
    */
    public Mantenedora cadastrarMantenedora(MantenedoraDTO mantenedoraDTO) {
        Mantenedora mantenedora = new Mantenedora();

        BeanUtils.copyProperties(mantenedoraDTO, mantenedora);

        Endereco endereco = new Endereco();
        BeanUtils.copyProperties(mantenedoraDTO.endereco(), endereco);
        mantenedora.setEndereco(endereco);

        if (mantenedoraDTO.contas() != null) {
            for (Conta contaDTO : mantenedoraDTO.contas()) {
                Conta conta = new Conta();

                BeanUtils.copyProperties(contaDTO, conta);
                mantenedora.getContas().add(conta);
            }
        }

        if (mantenedoraDTO.telefones() != null) {
            for (Telefone telefoneDTO : mantenedoraDTO.telefones()) {
                Telefone telefone = new Telefone();

                BeanUtils.copyProperties(telefoneDTO, telefone);
                mantenedora.getTelefones().add(telefone);
            }
        }

        return this.mantenedoraRepository.save(mantenedora);
    }

    public Mantenedora buscarMantenedora(Integer id) {

        return this.mantenedoraRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found!"));
    }

    /*
        Atualizando objeto com outros objetos em seu corpo.
    */
    public Mantenedora atualizarMantenedora(Integer id, MantenedoraDTO mantenedoraDTO) {
        Mantenedora mantenedora = this.mantenedoraRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found!"));

        // Atualizar o Endereco
        if (mantenedoraDTO.endereco() != null) {
            Endereco enderecoDTO = mantenedoraDTO.endereco();
            if (enderecoDTO.getId() != null) {
                // Verificar se o Endereco já existe e pertence à Mantenedora atual
                Endereco endereco = mantenedora.getEndereco();
                if (endereco != null && endereco.getId().equals(enderecoDTO.getId())) {
                    BeanUtils.copyProperties(enderecoDTO, endereco, "id");
                } else {
                    throw new IllegalStateException("Invalid Endereco ID or mismatch with existing Endereco.");
                }
            } else {
                // Se o Endereco no DTO não tem ID, criar um novo
                Endereco newEndereco = new Endereco();
                BeanUtils.copyProperties(enderecoDTO, newEndereco, "id");
                mantenedora.setEndereco(newEndereco);
            }
        } else {
            mantenedora.setEndereco(null); // Remover o Endereco se o DTO não tiver um
        }

        // Atualizar as Contas (Similar ao Endereco)
        if (mantenedoraDTO.contas() != null) {
            mantenedora.getContas().clear();
            for (Conta contaDTO : mantenedoraDTO.contas()) {
                Conta conta = new Conta();
                if (contaDTO.getId() != null) {
                    // Se o ID já existe, buscar a Conta para atualizar
                    conta = contaRepository.findById(contaDTO.getId())
                            .orElseThrow(() -> new ObjectNotFoundException("Conta not found!"));
                }
                BeanUtils.copyProperties(contaDTO, conta, "id");
                mantenedora.getContas().add(conta);
            }
        }

        // Atualizar os Telefones (Similar ao Endereco)
        if (mantenedoraDTO.telefones() != null) {
            mantenedora.getTelefones().clear();
            for (Telefone telefoneDTO : mantenedoraDTO.telefones()) {
                Telefone telefone = new Telefone();
                if (telefoneDTO.getId() != null) {
                    // Se o ID já existe, buscar o Telefone para atualizar
                    telefone = telefoneRepository.findById(telefoneDTO.getId())
                            .orElseThrow(() -> new ObjectNotFoundException("Telefone not found!"));
                }
                BeanUtils.copyProperties(telefoneDTO, telefone, "id");
                mantenedora.getTelefones().add(telefone);
            }
        }

        // Atualizar as demais propriedades (exceto o ID)
        BeanUtils.copyProperties(mantenedoraDTO, mantenedora, "id", "endereco", "contas", "telefones");

        // Salvar a entidade atualizada
        return this.mantenedoraRepository.save(mantenedora);
    }

    public Mantenedora deletaMantenedora(Integer id) {
        Mantenedora mantenedora = this.mantenedoraRepository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Object not found!"));

        this.mantenedoraRepository.deleteById(id);

        return  mantenedora;
    }
}
