package uz.pdp.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.pdp.dtos.TodoDto;
import uz.pdp.entity.Todo;
import uz.pdp.entity.User;
import uz.pdp.enums.Status;
import uz.pdp.repository.User_Repository;
import uz.pdp.repository.TodoRepository;

import java.security.Principal;

@RestController
    @RequestMapping("/api/todos")
    public class PostController {

        private final User_Repository userRepository;
        private final TodoRepository todoRepository;

        public PostController(User_Repository userRepository, TodoRepository todoRepository) {
            this.userRepository = userRepository;
            this.todoRepository = todoRepository;
        }

        @PreAuthorize("hasRole('USER')")
        @PostMapping
        public ResponseEntity<Void> createTodo(@RequestBody TodoDto dto, Principal principal) {

            System.out.println( principal.getName() );

            User user = userRepository.findUserByUsername(principal.getName()).get();

            if ( user.getStatus().equals( Status.BLOCK ) ) {
                throw new RuntimeException("User is blocked");
            }

            Todo todo = new Todo();
            todo.setTodo(dto.getTodo());
            todo.setUserId( user.getId() );

            todoRepository.save(todo);

            return ResponseEntity.status( HttpStatus.CREATED ).build();
        }
    @PreAuthorize("hasRole('USER')")
    @GetMapping ("/api/getTodos  ")
    public ResponseEntity<Iterable<Todo>> getAllTodos( Principal principal) {

        System.out.println( principal.getName() );

        User user = userRepository.findUserByUsername(principal.getName()).get();

        if ( user.getStatus().equals( Status.BLOCK ) ) {
            throw new RuntimeException("User is blocked");
        }

        return ResponseEntity.status( HttpStatus.CREATED ).build();
    }











    }


