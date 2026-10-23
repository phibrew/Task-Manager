package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    private final TaskRepository taskRepository;
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public TaskDto saveTask(Task task){
        Task savedTask = taskRepository.save(task);
        return convertToDto(savedTask);
    }

    public List<TaskDto> findAllTasks(){
        List<Task>  tasks = taskRepository.findAll();
        List<TaskDto> taskDtos = new ArrayList<>();
        for(Task task : tasks){
            taskDtos.add(convertToDto(task));
        }
        return taskDtos;
    }
    private TaskDto convertToDto(Task task){
        TaskDto taskDto = new TaskDto();
        taskDto.setId(task.getId());
        taskDto.setName(task.getName());
        taskDto.setDescription(task.getDescription());
        taskDto.setStatus(task.getStatus());
        taskDto.setUserId(task.getUser().getId());
        taskDto.setBoardId(task.getBoard().getId());
        return  taskDto;
    }
}
