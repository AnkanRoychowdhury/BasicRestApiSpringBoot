package me.ankanroychowdhury.restapiusingspring.controllers;

import me.ankanroychowdhury.restapiusingspring.entities.Todo;
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
    public List<Todo> getTodoList(){
        return todoList;
    }

    @GetMapping("/{todoId}")
    public Todo getTodoByID(@PathVariable Long todoId){
        try {
            for(Todo todo: todoList){
                if(todo.getId() == todoId) return todo;
            }
        } catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }


}
