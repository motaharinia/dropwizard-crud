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
public class App extends Application<Configuration> {

    @Override
    public void initialize(Bootstrap<Configuration> b) {
    }

    @Override
    public void run(Configuration c, Environment e) throws Exception {
        log.info("Registering REST resources");
        e.jersey().register(new EmployeeRESTController(e.getValidator()));
    }

    public static void main(String[] args) throws Exception {
        new App().run(args);
    }
}
