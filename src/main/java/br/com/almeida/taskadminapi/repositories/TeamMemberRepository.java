package br.com.almeida.taskadminapi.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almeida.taskadminapi.models.TeamMemberModel;

@Repository
public interface TeamMemberRepository extends JpaRepository<TeamMemberModel, UUID> {

}
