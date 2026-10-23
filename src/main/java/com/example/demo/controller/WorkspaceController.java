package com.example.demo.controller;

import com.example.demo.dto.WorkspaceDto;
import com.example.demo.entity.Workspace;
import com.example.demo.service.WorkspaceService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/workspaces")
public class WorkspaceController {
    private final WorkspaceService workspaceService;
    public WorkspaceController(WorkspaceService workspaceService) {
        this.workspaceService = workspaceService;
    }

    @GetMapping
    public List<WorkspaceDto> getAllWorkspaces(){
        return workspaceService.findAllWorkspaces();
    }
    @PostMapping
    public WorkspaceDto saveWorkspace(@RequestBody Workspace workspace) {
        return workspaceService.saveWorkspace(workspace);
    }

}
