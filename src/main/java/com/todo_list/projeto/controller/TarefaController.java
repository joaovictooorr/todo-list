package com.todo_list.projeto.controller;

import com.todo_list.projeto.model.Tarefas;
import com.todo_list.projeto.service.TarefasService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
public class TarefaController {

    private final TarefasService tarefasService;

    public TarefaController(TarefasService tarefasService) {
        this.tarefasService = tarefasService;
    }

    @GetMapping
    @Operation(summary = "buscar todas as tarefas")
    public ResponseEntity<List<Tarefas>> findAll(){
        var tarefas = tarefasService.findAll();
        return ResponseEntity.ok(tarefas);
    }

    @GetMapping(value = "/{id}")
    @Operation(summary = "busca as tarefas pelo id")
    public ResponseEntity<Tarefas> findById(@PathVariable String id){
        var tarefas = tarefasService.findById(id);
        return ResponseEntity.ok(tarefas);
    }

    @PostMapping
    @Operation(summary = "salva as tarefas no banco de dados")
    public ResponseEntity<Tarefas> create(@RequestBody Tarefas tarefa) {
        var tarefaCreated = tarefasService.create(tarefa);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(tarefaCreated.getId())
                .toUri();
        return ResponseEntity.created(location).body(tarefaCreated);
    }

    @PutMapping("/{id}")
    @Operation(summary = "atualiza as tarefas")
    public ResponseEntity<Void> update(@RequestBody Tarefas tarefaUpdate, @PathVariable String id) {
        tarefasService.update(id, tarefaUpdate);
        return ResponseEntity.noContent().build();
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "deleta uma tarefa pelo id")
    public ResponseEntity<Void> delete(@PathVariable String id){
        tarefasService.delete(id);
        return ResponseEntity.noContent().build();
    }


}
