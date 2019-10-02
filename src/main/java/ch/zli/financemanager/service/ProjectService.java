package ch.zli.financemanager.service;

import ch.zli.financemanager.entity.Project;
import ch.zli.financemanager.repository.ProjectRepository;
import org.hibernate.HibernateException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

@Service
public class ProjectService {

    @PersistenceContext
    private EntityManager entityManager;

    private ProjectRepository projectRepository;

    @Autowired
    public ProjectService(ProjectRepository projectRepository) {
        this.projectRepository = projectRepository;
    }

    public List<Project> findAll() {
        return projectRepository.findAll();
    }

    public Optional<Project> findProjectById(long id) {
        return projectRepository.findById(id);
    }

    public Project createProject(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    public void deleteProjectById(long id) throws Exception {
        if (projectRepository.existsById(id)) {
            projectRepository.deleteById(id);
        } else {
            throw new Exception("Couldn't delete project, because it didn't exist!");
        }
    }

    public Project updateProject(Project project, long id) throws Exception {
        Project updatedProject;
        Optional<Project> optionalUpdatedEntry = findProjectById(id);

        if (optionalUpdatedEntry.isPresent()) {
            updatedProject = optionalUpdatedEntry.get();
            updatedProject.setTitle(project.getTitle());
        } else {
            throw new Exception("Couldn't update project, because it didn't exist!");
        }

        return projectRepository.saveAndFlush(updatedProject);
    }

    public Project findProjectByTitle(String title) {
        String queryString = "SELECT p FROM Project as p WHERE p.title = :title";

        TypedQuery<Project> query = entityManager.createQuery(queryString, Project.class);
        query.setParameter("title", title);
        return (Project) query.getSingleResult();
    }
}
