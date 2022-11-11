package br.com.almeida.taskadminapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almeida.taskadminapi.models.TeamModel;

@Repository
public interface TeamRepository extends JpaRepository<TeamModel, UUID> {
    List<TeamModel> findByName(String name);
}
