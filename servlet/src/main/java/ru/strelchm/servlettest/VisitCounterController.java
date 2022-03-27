package ru.strelchm.servlettest;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

@WebServlet
public class VisitCounterController extends HttpServlet {
    private static final AtomicInteger VISIT_TOTAL_COUNTER = new AtomicInteger(0);

    /**
     * возвращает счетчик
     */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();
        writer.print(VISIT_TOTAL_COUNTER.get());
        writer.flush();
    }

    /**
     * увеличивает счетчик на 1
     *
     * @param request
     * @param response
     */
    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VISIT_TOTAL_COUNTER.incrementAndGet();
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();
        writer.print("OK");
        writer.flush();
    }

    /**
     * уменьшает счетчик на значение, которое передается в заголовке «Subtraction-Value»
     *
     * @param request
     * @param response
     */
    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) throws IOException {
        VISIT_TOTAL_COUNTER.decrementAndGet();
        response.setStatus(HttpServletResponse.SC_OK);

        PrintWriter writer = response.getWriter();
        writer.print("OK");
        writer.flush();
    }

    @WebServlet
    public static class DeleteController extends HttpServlet {
        /**
         * сбрасывает счетчик. Для выполнения этого запроса должна быть cookie - «hh-auth», единственное условие - она должна быть длиннее 10 символов
         */
        @Override
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
            PrintWriter writer = response.getWriter();
            Optional<Cookie> cookie = request.getCookies() == null ? Optional.empty() :
                    Arrays.stream(request.getCookies()).filter(v -> v.getName().equals("hh-auth")).findFirst();
            if (cookie.isEmpty()) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                writer.print("hh-auth cookie not found");
            } else if (cookie.get().getValue().length() <= 10) {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                writer.print("hh-auth cookie is wrong");
            } else {
                response.setStatus(HttpServletResponse.SC_OK);
                writer.print("OK");
                VISIT_TOTAL_COUNTER.set(0);
            }
            writer.flush();
        }
    }
}
