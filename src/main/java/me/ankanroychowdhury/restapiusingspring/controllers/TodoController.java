package me.ankanroychowdhury.restapiusingspring.controllers;

import me.ankanroychowdhury.restapiusingspring.entities.Todo;
import me.ankanroychowdhury.restapiusingspring.exceptions.ApplicationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
@RequestMapping("/api/v1/todos")
public class TodoController {
    private static List<Todo> todoList;
    public TodoController(){
        todoList =  new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            todoList.add(new Todo(i, true, "Todo "+i, i));
        }
    }
    @GetMapping("")
    public ResponseEntity<List<Todo>> getTodoList(){
        return ResponseEntity.ok(todoList);
    }

    @GetMapping("/{todoId}")
    public ResponseEntity<Todo> getTodoByID(@PathVariable Long todoId){
        Todo responseTodo = null;
        for(Todo todo: todoList){
            if(todo.getId() == todoId) {
                responseTodo = todo;
                break;
            }
        }
        if(responseTodo == null){
            throw new ApplicationException(
                    String.format("Todo with id=%d not found", todoId),
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(responseTodo);
    }


}
