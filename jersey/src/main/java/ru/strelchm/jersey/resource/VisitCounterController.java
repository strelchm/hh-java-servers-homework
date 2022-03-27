package ru.strelchm.jersey.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@Path("/counter")
public class VisitCounterController {
    private static final AtomicInteger VISIT_TOTAL_COUNTER = new AtomicInteger(0);

    /**
     * возвращает счетчик
     */
    @GET
    @Path(value = "/")
    @Produces("application/json")
    public Response getCounter() {
        GetCounterResponse resume = new GetCounterResponse(LocalDateTime.now(), VISIT_TOTAL_COUNTER.get());
        return Response.ok(resume).build();
    }

    /**
     * увеличивает счетчик на 1
     */
    @POST
    @Path(value = "/")
    public Response incrementCounter() {
        VISIT_TOTAL_COUNTER.incrementAndGet();
        return Response.ok("OK").build();
    }

    /**
     * уменьшает счетчик на значение, которое передается в заголовке «Subtraction-Value»
     */
    @DELETE
    @Path(value = "/")
    public Response decrementCounter() {
        VISIT_TOTAL_COUNTER.decrementAndGet();
        return Response.ok("OK").build();
    }

    @POST
    @Path(value = "/clear")
    public Response doPost(@CookieParam("hh-auth") String cookie) {
        if (cookie == null) {
            return Response.status(Response.Status.FORBIDDEN.getStatusCode()).entity("hh-auth cookie not found").build();
        } else if (cookie.length() <= 10) {
            return Response.status(Response.Status.FORBIDDEN.getStatusCode()).entity("hh-auth cookie is wrong").build();
        } else {
            VISIT_TOTAL_COUNTER.set(0);
            return Response.ok("OK").build();
        }
    }
}
