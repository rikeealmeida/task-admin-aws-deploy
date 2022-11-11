package br.com.almeida.taskadminapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almeida.taskadminapi.models.TaskModel;

@Repository
public interface TaskRepository extends JpaRepository<TaskModel, UUID> {
    List<TaskModel> findByTitle(String title);
}
