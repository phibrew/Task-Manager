package com.example.demo.service;

import com.example.demo.dto.TaskDto;
import com.example.demo.entity.Task;
import com.example.demo.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class TaskServiceTest {

    @Mock // this is to make fake taskrepo no real connection to postgres
    private TaskRepository taskRepository;

    @InjectMocks//this checks first if fake repo exists, if yes then inject that into service
    private TaskService taskService;

    @Test
    public void testFindAllTasks() {
        // 1. Arrange: Create mock dependencies first
        com.example.demo.entity.User mockUser = new com.example.demo.entity.User();
        mockUser.setId(10L);

        com.example.demo.entity.Board mockBoard = new com.example.demo.entity.Board();
        mockBoard.setId(5L);

        // Create the mock task and attach the user and board
        Task mockTask = new Task();
        mockTask.setId(1L);
        mockTask.setName("Test Task");
        mockTask.setStatus("PENDING");
        mockTask.setUser(mockUser);
        mockTask.setBoard(mockBoard);

        //this is what to return if mockito calls for findall using taskrepo
        when(taskRepository.findAll()).thenReturn(Arrays.asList(mockTask));

        // 2. Act: Call your actual service method
        List<TaskDto> result = taskService.findAllTasks();

        // 3. Assert: Verify the DTO mapping worked flawlessly
        assertEquals(1, result.size());
        assertEquals("Test Task", result.get(0).getName());
        assertEquals("PENDING", result.get(0).getStatus());
        assertEquals(10L, result.get(0).getUserId());
        assertEquals(5L, result.get(0).getBoardId());
    }

    @Test
    public void testSaveTask(){
        com.example.demo.entity.User mockUser = new com.example.demo.entity.User();
        mockUser.setId(10L);
        com.example.demo.entity.Board mockBoard = new com.example.demo.entity.Board();
        mockBoard.setId(5L);

        Task savedMockTask = new Task();
        savedMockTask.setId(1L);
        savedMockTask.setName("Test Task");
        savedMockTask.setStatus("PENDING");
        savedMockTask.setUser(mockUser);
        savedMockTask.setBoard(mockBoard);

        when(taskRepository.save(any(Task.class))).thenReturn(savedMockTask);

        Task newTask = new Task();
        TaskDto result = taskService.saveTask(newTask);

        assertEquals(1L, result.getId());
        assertEquals("Test Task", result.getName());
        assertEquals("PENDING", result.getStatus());
        assertEquals(10L, result.getUserId());
        assertEquals(5L, result.getBoardId());
    }
}