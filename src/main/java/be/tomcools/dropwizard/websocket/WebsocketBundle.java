package be.tomcools.dropwizard.websocket;

import be.tomcools.dropwizard.websocket.handling.ServerFactoryWrapper;
import io.dropwizard.Configuration;
import io.dropwizard.ConfiguredBundle;
import io.dropwizard.server.ServerFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class WebsocketBundle implements ConfiguredBundle {
    private WebsocketHandlerFactory handlerFactory = new WebsocketHandlerFactory();

    private WebsocketHandler handler;

    public WebsocketBundle() {
    }

    public void addEndpoint(Class<?> aClass) {
        handler.addEndpoint(aClass);
    }

    @Override
    public void run(Configuration configuration, Environment environment) throws Exception {
        handler = handlerFactory.forEnvironment(environment);
        ServerFactory serverFactory = configuration.getServerFactory();
        ServerFactoryWrapper factoryWrapper = new ServerFactoryWrapper(serverFactory, handler);
        configuration.setServerFactory(factoryWrapper);
    }

    @Override
    public void initialize(Bootstrap bootstrap) {

    }
}
