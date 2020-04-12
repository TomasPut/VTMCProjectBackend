package com.andriusk.project.service;

import com.andriusk.project.entity.Project;
import com.andriusk.project.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class ProjectServiceImp implements ProjectService {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    @Override
    public void save(Project project) {
        projectRepository.save(project);
    }

    @Override
    public Project findById(Long id) {
        return projectRepository.findById(id).get();
    }
    
    @Override
	public void deleteProjectByID(Long id) {
		projectRepository.deleteById(id);
		
	}
	@Override
	public Project getProjectByID(Long id) {
		return projectRepository.findById(id).get();
	}

    @Override
    public List<Project> findByProjectName(String projectName) {
        Pattern pattern = Pattern.compile( "\\b(" + projectName + ".*)", Pattern.CASE_INSENSITIVE);
        return projectRepository.findAll().stream().filter(project -> pattern.matcher(project.getProjectName()).find()).collect(Collectors.toList());
    }
}