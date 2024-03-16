package co.cstad.todolist_web_mvc.repository;

import co.cstad.todolist_web_mvc.model.Todo;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


@Repository
public class TodoRepository {
    private final List<Todo> todoList = new ArrayList<>();
    private final AtomicInteger idCounter = new AtomicInteger(0);

    public TodoRepository() {
        todoList.add(new Todo(idCounter.incrementAndGet(), "Task 1", "Description 1", false, LocalDateTime.now()));
        todoList.add(new Todo(idCounter.incrementAndGet(), "Task 2", "Description 2", false, LocalDateTime.now()));
        todoList.add(new Todo(idCounter.incrementAndGet(), "Task 3", "Description 3", false, LocalDateTime.now()));
    }

    public List<Todo> getAllTasks() {
        return todoList;
    }

    public void addTask(Todo todo) {
        todo.setId(idCounter.incrementAndGet());
        todoList.add(todo);
    }
    public void editTask(Todo todo) {
        for (Todo existingTodo : todoList) {
            if (existingTodo.getId().equals(todo.getId())) {
                existingTodo.setTask(todo.getTask());
                existingTodo.setDescription(todo.getDescription());
                existingTodo.setDone(todo.isDone());
                existingTodo.setCreatedAt(LocalDateTime.now());

                break;
            }
        }
    }
    public void deleteTask(int id) {
        todoList.removeIf(todo -> todo.getId() == id);
    }

    public Todo getTaskById(int id) {
        return todoList.stream()
                .filter(todo -> todo.getId().equals(id))
                .findFirst()
                .orElse(null);
    }


    public List<Todo> searchTasks(String task, Boolean isDone) {
        List<Todo> matchingTasks = new ArrayList<>();

        // Convert task to lowercase for case-insensitive search
        String searchTask = (task != null) ? task.toLowerCase() : null;

        for (Todo todo : todoList) {
            // Convert todo task to lowercase for case-insensitive comparison
            String todoTask = todo.getTask().toLowerCase();

            boolean isTaskMatch = searchTask == null || todoTask.contains(searchTask);
            boolean isDoneMatch = isDone == null || todo.isDone() == isDone;

            if (isTaskMatch && isDoneMatch) {
                matchingTasks.add(todo);
            }
        }

        return matchingTasks;
    }


}
