package co.cstad.todolist_web_mvc.service;

import co.cstad.todolist_web_mvc.model.Todo;
import co.cstad.todolist_web_mvc.repository.TodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TodoServiceImp implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public List<Todo> getAllTasks() {
        return todoRepository.getAllTasks();
    }

    @Override
    public void addTask(Todo todo) {
        todoRepository.addTask(todo);
    }


    @Override
    public void deleteTask(int id) {
        todoRepository.deleteTask(id);

    }

    @Override
    public Todo getTaskById(int id) {
        return todoRepository.getTaskById(id);
    }

    @Override
    public void editTask(Todo todo) {
        todoRepository.editTask(todo);
    }

    @Override
    public List<Todo> searchTasks(String task, Boolean isDone) {
        return todoRepository.searchTasks(task, isDone);
    }
}