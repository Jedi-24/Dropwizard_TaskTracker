package com.Jedi.resources;

import com.Jedi.dao.taskDao;
import com.Jedi.entity.taskEntity;
import io.dropwizard.hibernate.UnitOfWork;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.Date;
import java.util.List;
import java.util.Objects;

// this resource contains the api-endpoints to
/*
* create new tasks
* remove an existing task
* get a specific task
* get all tasks
* update an existing task
*/

@Path("/tasks")
@Produces(MediaType.APPLICATION_JSON)
public class taskResource {
    // instance of taskDao is necessary in our resource to call methods present in Dao(s) inorder to comm. with DB.
    private final taskDao tskDao;
    public taskResource(taskDao tDao) {
        this.tskDao = tDao;
    }

    // creating a new task:
    @POST
    @Path("/store")
    @UnitOfWork
    public String postTask(@Valid taskEntity newTask) {
        try {
            tskDao.saveNewTask(newTask);
            return "Task Saved";
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    // get all tasks:
    @GET
    @Path("/fetchAll")
    @UnitOfWork
    public List<taskEntity> fetchAllTasks() {
        return tskDao.getAllTasks();
    }

    // get a specific task (based on taskId):
    @GET
    @Path("/fetch/{id}")
    @UnitOfWork
    public taskEntity fetchTask(@PathParam("id") Integer fetchId) {
        return tskDao.getTask(fetchId);
    }

    // update a specific task:
    @PUT
    @Path("/update/{id}")
    @UnitOfWork
    public taskEntity updateTask(@PathParam("id") Integer updateId, @Valid taskEntity updatedTask) {
        return tskDao.updateTask(updateId, updatedTask);
    }

    @DELETE
    @Path("/delete/{id}")
    @UnitOfWork
    public String deleteTask(@PathParam("id") Integer delId) {
        return tskDao.deleteTask(delId);
    }

    /*
    * The @UnitOfWork annotation in Dropwizard hides the boilerplate code for opening and closing a Hibernate Session,
    *  and beginning, committing, and rolling back a transaction.
    *  It can only be applied to a resource (Jersey) end-point method.
    * */

    @GET
    public String checker() {
        return "First Api Request hit ! " + new Date();
    }
}