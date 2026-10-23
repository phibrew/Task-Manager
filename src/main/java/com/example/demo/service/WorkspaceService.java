package com.example.demo.service;

import com.example.demo.dto.WorkspaceDto;
import com.example.demo.entity.Workspace;
import com.example.demo.repository.WorkspaceRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkspaceService{
    private final WorkspaceRepository workspaceRepository;
    public WorkspaceService(WorkspaceRepository workspaceRepository) {
        this.workspaceRepository = workspaceRepository;
    }

    public WorkspaceDto saveWorkspace(Workspace workspace) {
        Workspace savedWorkspace = workspaceRepository.save(workspace);
        return convertToDto(savedWorkspace);
    }

    public List<WorkspaceDto> findAllWorkspaces() {
        List<Workspace> workspaces = workspaceRepository.findAll();
        List<WorkspaceDto> workspaceDtos = new ArrayList<>();
        for(Workspace workspace : workspaces){
            workspaceDtos.add(convertToDto(workspace));
        }
        return workspaceDtos;
    }
    private WorkspaceDto convertToDto(Workspace workspace){
        WorkspaceDto workspaceDto = new WorkspaceDto();
        workspaceDto.setId(workspace.getId());
        workspaceDto.setName(workspace.getName());
        workspaceDto.setDescription(workspace.getDescription());
        workspaceDto.setUserId(workspace.getUser().getId());
        return workspaceDto;
    }
}
