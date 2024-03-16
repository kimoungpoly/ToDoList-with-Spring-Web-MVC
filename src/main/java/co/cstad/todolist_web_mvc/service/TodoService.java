package co.cstad.todolist_web_mvc.service;

import co.cstad.todolist_web_mvc.model.Todo;

import java.util.List;

public interface TodoService {
      List<Todo> getAllTasks();
      void addTask(Todo todo);
      void deleteTask(int id);
      void editTask(Todo todo);
      Todo getTaskById(int id);
      List<Todo> searchTasks(String task, Boolean isDone);
}
