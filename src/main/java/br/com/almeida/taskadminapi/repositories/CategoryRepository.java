package br.com.almeida.taskadminapi.repositories;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.almeida.taskadminapi.models.CategoryModel;

@Repository
public interface CategoryRepository extends JpaRepository<CategoryModel, UUID> {
    List<CategoryModel> findByName(String name);
}
