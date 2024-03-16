package co.cstad.todolist_web_mvc.controller;

import co.cstad.todolist_web_mvc.model.Todo;
import co.cstad.todolist_web_mvc.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/todo")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @GetMapping
    public String index(Model model) {
        List<Todo> todos = todoService.getAllTasks();
        model.addAttribute("todos", todos);
        return "index";
    }

//    @GetMapping("/edit")
//    public String editTodo(@PathVariable int id, Model model) {
//        Todo todo = todoService.getTaskById(id);
//        if (todo != null) {
//            model.addAttribute("todo", todo);
//            return "index";
//        } else {
//            return "redirect:/todo";
//        }
//    }

    @PostMapping("/add")
    public String add(@ModelAttribute Todo todo) {
        todo.setCreatedAt(LocalDateTime.now());
        todoService.addTask(todo);
        return "redirect:/todo";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        todoService.deleteTask(id);
        return "redirect:/todo";
    }



    @GetMapping("/edit/{id}")
    public String editTodo(@PathVariable int id, Model model) {
        Todo todo = todoService.getTaskById(id);
        if (todo != null) {
            model.addAttribute("todo", todo);
            return "index";
        } else {
            return "redirect:/todo";
        }
    }

    @PostMapping("/edit/{id}")
    public String updateTodo(@PathVariable int id, @RequestParam String task, @RequestParam String description, @RequestParam String isDone) {
        boolean isDoneValue = Boolean.parseBoolean(isDone); // Convert the string value to a boolean
        Todo todo = todoService.getTaskById(id);
        if (todo != null) {
            todo.setTask(task);
            todo.setDescription(description);
            todo.setDone(isDoneValue);
            todoService.editTask(todo);
        }
        return "redirect:/todo";
    }

    @GetMapping("/search")
    public String searchTodo(
            @RequestParam(value = "task", required = false) String task,
            @RequestParam(value = "isDone", required = false) Boolean isDone,
            Model model) {

        List<Todo> searchResults = todoService.searchTasks(task, isDone);
        model.addAttribute("todos", searchResults);
        return "index"; // Assuming "index" is the name of your view for displaying todo items
    }






}




