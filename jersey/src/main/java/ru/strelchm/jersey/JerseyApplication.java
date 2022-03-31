package ru.strelchm.jersey;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.servlet.ServletContainer;

public class JerseyApplication {
  public static void main(String[] args) throws Exception {
    Server server = createServer(8081);
    server.start();
    server.join();
  }

  private static Server createServer(int port) {
    Server server = new Server(port);
    ServletContextHandler ctx = new ServletContextHandler();
    server.setHandler(ctx);

    ServletHolder holder = ctx.addServlet(ServletContainer.class, "/*");
    holder.setInitOrder(1);
    holder.setInitParameter("jersey.config.server.provider.packages", "ru.strelchm.jersey.resource");

    return server;
  }
}
