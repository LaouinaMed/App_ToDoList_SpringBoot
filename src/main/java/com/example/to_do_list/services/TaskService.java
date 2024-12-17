package com.example.to_do_list.services;

import com.example.to_do_list.models.Task;
import com.example.to_do_list.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {

        this.taskRepository = taskRepository;
    }

    public List<Task> getAllTasks() {
        return  taskRepository.findAll();
    }

    public void createTask(String title) {
        Task task = new Task();
        task.setTitle(title);
        task.setComplete(false);
        taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    public void toggleTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid task"));
        task.setComplete(!task.isComplete());
        taskRepository.save(task);
    }
}
