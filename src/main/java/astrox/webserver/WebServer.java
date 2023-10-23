package astrox.webserver;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.server.handler.ResourceHandler;

public class WebServer {

    Server server;
    int port;
    String path;
    public WebServer(int port, String path) throws Exception {

        this.server = new Server();
        this.port = port;
        this.path = path;

        ServerConnector connector = new ServerConnector(server);
        connector.setPort(port);
        server.setConnectors(new Connector[] {connector});

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setBaseResourceAsString(path); // Set the path to your Angular app's build output

        server.setHandler(resourceHandler); // Set the ResourceHandler as the server's handler

        server.start();
        server.join();
    }


}
