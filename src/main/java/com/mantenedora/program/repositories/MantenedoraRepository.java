package com.mantenedora.program.repositories;

import com.mantenedora.program.model.Mantenedora;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MantenedoraRepository extends JpaRepository<Mantenedora, Integer> {
}
