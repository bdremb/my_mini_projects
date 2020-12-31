package main.controllers;

import main.model.Task;
import main.model.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class DefaultController {

    @Autowired
    TaskRepository taskRepository;

    @RequestMapping("/")                        //
    public String index(Model model) {
        Iterable<Task> taskIterable = taskRepository.findAll();
        List<Task> taskList = new ArrayList<>();
        taskIterable.forEach(taskList::add);
        model.addAttribute("count", taskList.size());
        model.addAttribute("tasks", taskList);
        return "index";
    }


}
