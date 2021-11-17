package com.motaharinia.crud;

import com.motaharinia.crud.modules.employee.EmployeeRESTController;
import io.dropwizard.Application;
import io.dropwizard.Configuration;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import lombok.extern.slf4j.Slf4j;


//https://howtodoinjava.com/dropwizard/tutorial-and-hello-world-example/
//https://medium.com/swlh/how-to-design-restful-web-services-with-dropwizard-d5681a127cba

/**
 * Hello world!
 *
 */

@Slf4j
public class CrudApplication extends Application<CrudConfiguration> {

    @Override
    public void initialize(Bootstrap<CrudConfiguration> b) {
    }

    @Override
    public void run(CrudConfiguration c, Environment e) throws Exception {
        log.info("--------------- {}", c.getDefaultName());
        log.info("--------------- {}", c.getTemplate());

        log.info("Registering REST resources");
        e.jersey().register(new EmployeeRESTController(e.getValidator()));
    }

    public static void main(String[] args) throws Exception {
        new CrudApplication().run(args);
//        new CrudApplication().run("server", "crud.yml");
    }
}
