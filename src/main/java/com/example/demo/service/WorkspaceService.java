package com.example.demo.service;

import com.example.demo.entity.Workspace;
import com.example.demo.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkspaceService{
    private final WorkspaceRepository workspaceRepository;
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public Workspace saveWorkspace(Workspace workspace) {
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return savedWorkspace;
    }

    public List<Workspace> findAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        return workspaces;
    }
}
