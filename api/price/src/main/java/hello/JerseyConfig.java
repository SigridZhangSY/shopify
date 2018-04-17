package hello;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

//Component让spring boot扫描到JerseyConfig
@Component
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {

        //        register(UsersResource.class);
//        配置扫描的restful package.
        packages("hello.web.rest");

    }
}
