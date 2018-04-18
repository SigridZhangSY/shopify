package price;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;

//Component让spring boot扫描到JerseyConfig
@Component
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {

        //        register(UsersResource.class);
//        配置扫描的restful package.
        packages("price.web.rest");
        //不加此配置jersey会将错误码response包装成404
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
