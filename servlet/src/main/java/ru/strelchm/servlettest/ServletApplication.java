package ru.strelchm.servlettest;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHandler;

/**
 * Использует только сервлеты. Нужно написать приложение-счетчик – хранить в памяти состояние, котороое изменяется через HTTP походы. Используйте подходящие коды HTTP ответов из лекции по HTTP
 * <p>
 * GET: /counter - возвращает счетчик
 * <p>
 * POST: /counter - увеличивает счетчик на 1
 * <p>
 * DELETE: /counter - уменьшает счетчик на значение, которое передается в заголовке «Subtraction-Value»
 * <p>
 * POST: /counter/clear - сбрасывает счетчик. Для выполнения этого запроса должна быть cookie - «hh-auth», единственное условие - она должна быть длиннее 10 символов.
 */
public class ServletApplication {
    public static void main(String[] args) throws Exception {
        Server server = createServer(8081);
        server.start();
        server.join();
    }

    private static Server createServer(int port) {
        Server server = new Server(port);
        ServletHandler handler = new ServletHandler();

        handler.addServletWithMapping(VisitCounterController.class, "/counter");
        handler.addServletWithMapping(VisitCounterController.DeleteController.class, "/counter/clear");
        server.setHandler(handler);

        return server;
    }
}