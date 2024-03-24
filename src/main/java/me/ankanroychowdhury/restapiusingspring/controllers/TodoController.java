package me.ankanroychowdhury.restapiusingspring.controllers;

import me.ankanroychowdhury.restapiusingspring.entities.Todo;
import me.ankanroychowdhury.restapiusingspring.exceptions.ApplicationException;
import me.ankanroychowdhury.restapiusingspring.utils.ApiSuccessResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    @DeleteMapping("/{todoId}")
    public ResponseEntity<?> deleteTodoById(@PathVariable Long todoId){
        Todo todoToRemove = null;
        for(Todo todo : todoList) {
            if(todo.getId() == todoId) {
                todoToRemove = todo;
                break;
            }
        }
        if(todoToRemove == null){
            throw new ApplicationException(
                    String.format("Todo with id=%d not found to delete", todoId),
                    HttpStatus.NOT_FOUND
            );
        }
        todoList.remove(todoToRemove);
        ApiSuccessResponse successResponse = new ApiSuccessResponse(
                String.format("Successfully deleted the Todo with id=%d", todoToRemove.getId()),
                HttpStatus.OK.value(),
                HttpStatus.OK.name()
        );
        return new ResponseEntity<>(successResponse, HttpStatus.OK);
    }

    @PatchMapping("/{todoId}")
    public ResponseEntity<Todo> updateTodoById(@PathVariable Long todoId){
        boolean isUpdated = false;
        Todo todoToUpdate = null;

        for(Todo todo: todoList){
            if(todo.getId() == todoId){
                todo.setTitle("Todo Updated");
                todo.setCompleted(false);
                isUpdated = true;
                todoToUpdate = todo;
                break;
            }
        }
        if(!isUpdated) {
            throw new ApplicationException(
                    String.format("Unable to update the Todo with id=%d", todoId),
                    HttpStatus.NOT_FOUND
            );
        }
        return ResponseEntity.ok(todoToUpdate);
    }
}
