package com.Jedi.dao;

import com.Jedi.entity.taskEntity;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

// Dao(s) in dropwizard are worth paying attention to.
public class taskDao extends AbstractDAO<taskEntity> {
    public taskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    // here I write methods to communicate with my mariaDB database instance
}
