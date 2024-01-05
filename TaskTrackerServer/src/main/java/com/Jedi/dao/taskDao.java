package com.Jedi.dao;

import com.Jedi.entity.taskEntity;
import io.dropwizard.hibernate.AbstractDAO;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.ws.rs.core.Response;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.util.ArrayList;
import java.util.List;

// Dao(s) in dropwizard are worth paying attention to.
public class taskDao extends AbstractDAO<taskEntity> {
    // sessionFactory Object to open a session... AbstractDAO has it as private so use its instance here only.
    private final SessionFactory sessionFactory; // Imp for FetchAll functionality.
    public taskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
        this.sessionFactory = sessionFactory;
    }

    // I need to read about sessions object in hibernate and AbstractDAO interface as a whole.

    // here I write methods to communicate with my mariaDB database instance: CRUD.
    public String saveNewTask(taskEntity newTask) {
//        newTask.setStatusId(501); // can be used to give default status to the tasks, but maybe not necessary.
        try{
            currentSession().persist(newTask);
            return Response.ok().toString();
        } catch (Exception exception) {
            return exception.getMessage();
        }
    }

    public taskEntity getTask(Integer fetchId) {
        return get(fetchId);
    }

    public List<taskEntity> getAllTasks() {
        // source: stack-over-flows -> study sessions in hibernate
        try(Session session = sessionFactory.openSession()) {
            CriteriaBuilder builder = session.getCriteriaBuilder();
            CriteriaQuery<taskEntity> criteria = builder.createQuery(taskEntity.class);

            criteria.from(taskEntity.class);

            return session.createQuery(criteria).getResultList();
        } catch (Exception exception) {
            return new ArrayList<>();
        } // closing the session here caused problems for further requests, find the reason why.
//        } finally {
//            sessionFactory.close();
//        }
    }

    public taskEntity updateTask(Integer updateId, taskEntity updatedTask) {
        taskEntity fetchedTask = getTask(updateId);
        fetchedTask.setStatusId(updatedTask.getStatusId());
        fetchedTask.setTaskTitle(updatedTask.getTaskTitle());
        fetchedTask.setTaskDesc(updatedTask.getTaskDesc());
        fetchedTask.setTargetDate(updatedTask.getTargetDate());
        return persist(fetchedTask);
    }

    public String deleteTask(Integer deleteId) {
        try{
            currentSession().remove(getTask(deleteId));
            return Response.ok().toString();
        } catch (Exception ignored) {
            return ignored.getMessage();
        }
    }
}