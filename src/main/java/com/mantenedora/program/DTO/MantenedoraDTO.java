package com.mantenedora.program.DTO;

import com.mantenedora.program.model.Conta;
import com.mantenedora.program.model.Endereco;
import com.mantenedora.program.model.Telefone;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public record MantenedoraDTO(@NotNull String nome, Integer matriz, Boolean sub, @NotNull Endereco endereco, List<Conta> contas, List<Telefone> telefones) {
}
