package com.todo_list.projeto.service.imp;

import com.todo_list.projeto.model.Tarefas;
import com.todo_list.projeto.repository.TarefasRepository;
import com.todo_list.projeto.service.TarefasService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;


@Service
public class TarefasServiceImpl implements TarefasService {

    private final TarefasRepository tarefasRepository;

    public TarefasServiceImpl(TarefasRepository tarefasRepository){
        this.tarefasRepository = tarefasRepository;
    }
    @Override
    public List<Tarefas> findAll() {
        return tarefasRepository.findAll();
    }

    @Override
    public Tarefas findById(String id) {
        return tarefasRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @Override
    public Tarefas create(Tarefas entity) {
        return tarefasRepository.save(entity);
    }

    @Override
    public Tarefas update(String id, Tarefas entity) {
        Tarefas dbTarefas = this.findById(id);

        if (dbTarefas != null) {
            dbTarefas.setNomeTarefa(entity.getNomeTarefa());
            dbTarefas.setDescricao(entity.getDescricao());
            return tarefasRepository.save(dbTarefas);
        }

        throw new NoSuchElementException();
    }

    @Override
    public void delete(String id) {
        Tarefas dbTarefas = this.findById(id);
        if(dbTarefas == null){
            throw new NoSuchElementException();
        }
        tarefasRepository.delete(dbTarefas);
    }
}
