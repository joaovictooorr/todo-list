package com.todo_list.projeto.repository;

import com.todo_list.projeto.model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas,String> {
}
