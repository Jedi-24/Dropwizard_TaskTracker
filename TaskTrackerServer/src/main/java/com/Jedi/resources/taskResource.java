package com.Jedi.resources;

import com.Jedi.dao.taskDao;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.Date;

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

    // get all tasks:

    // get a specific task (based on taskId):

    @GET
    public String checker() {
        return "First Api Request hit ! " + new Date();
    }
}