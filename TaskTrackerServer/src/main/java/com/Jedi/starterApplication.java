package com.Jedi;

import com.Jedi.config.customConfig;
import com.Jedi.dao.taskDao;
import com.Jedi.entity.taskEntity;
import com.Jedi.resources.taskResource;
import io.dropwizard.core.Application;
import io.dropwizard.core.setup.Bootstrap;
import io.dropwizard.core.setup.Environment;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.hibernate.HibernateBundle;

// dropwizard server file (current class) requires two program arguments to run -> 1) server. 2) config file path (yaml).
public class starterApplication extends Application<customConfig> {
    /*
    * added hibernateBundle instance to starterApplication -> this specifies
        * entity class,
        * method to get DataSourceFactory from customConfig class.
    */
    private final HibernateBundle<customConfig> taskHibernateInstance = new HibernateBundle<customConfig>(taskEntity.class) {
        @Override
        public DataSourceFactory getDataSourceFactory(customConfig configuration) {
            return configuration.getDataSourceFactory();
        }
    };

    public static void main(String[] args) throws Throwable {
        new starterApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<customConfig> bootstrap) {
        bootstrap.addBundle(taskHibernateInstance);
    }

    @Override
    public void run(customConfig confObj, Environment environment) {
        System.out.println("Jedi is here !");

        // important to make object of taskDao here :->
        final taskDao tDao = new taskDao(taskHibernateInstance.getSessionFactory());

        // all resources are required to be registered here:
        environment.jersey().register(new taskResource(tDao));
    }
}

/*
* command used to enter terminal for docker:
* docker exec -it <container-id> [bash OR sh]
* some containers might not have bash installed.
* above, it stands for "interactive terminal".
*
* Also, next time use docker images for the DBs and any other requirement.
* Hence, use of Docker-Compose is necessary to run our made up image and other pulled images.
* */