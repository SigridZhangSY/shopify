package price;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.springframework.stereotype.Component;
import price.web.rest.PriceListResource;
import price.web.rest.ProductsResource;

//Component让spring boot扫描到JerseyConfig
@Component
public class JerseyConfig  extends ResourceConfig {
    public JerseyConfig() {

        //        register(UsersResource.class);
//        配置扫描的restful package.
//        packages("price.web.rest");
        //通过指定package路径配置要注册的resource类，在打包时目录结构改变，会找不到相应的类
        register(ProductsResource.class);
        register(PriceListResource.class);
        //不加此配置jersey会将错误码response包装成404
        property(ServerProperties.RESPONSE_SET_STATUS_OVER_SEND_ERROR, true);
    }
}
