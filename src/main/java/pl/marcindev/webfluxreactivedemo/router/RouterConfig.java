package pl.marcindev.webfluxreactivedemo.router;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;
import pl.marcindev.webfluxreactivedemo.handler.CustomerHandler;
import pl.marcindev.webfluxreactivedemo.handler.CustomerReactiveHandler;

@Configuration
public class RouterConfig {
    @Autowired
    private CustomerHandler customerHandler;

    @Autowired
    private CustomerReactiveHandler customerReactiveHandler;

    @Bean
    public RouterFunction<ServerResponse> routerFunction() {
        return RouterFunctions.route()
                .GET("/router/customers", request -> customerHandler.loadCustomers(request))
                .GET("/router/customers/reactive",request -> customerReactiveHandler.getCustomers(request))
                .GET("router/customer/{input}",request->customerHandler.findCustomer(request))
                .POST("/router/customer/save",request -> customerHandler.saveCustomer(request))
                .build();
    }
}
