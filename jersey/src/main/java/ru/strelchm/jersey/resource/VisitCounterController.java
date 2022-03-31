package ru.strelchm.jersey.resource;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/counter")
public class VisitCounterController {

    private CounterStorage counterStorage;

    public VisitCounterController(CounterStorage counterStorage) {
        this.counterStorage = counterStorage;
    }

    /**
     * возвращает счетчик
     */
    @GET
    @Path(value = "/")
    @Produces("application/json")
    public Response getCounter() {
        GetCounterResponse counterResponse = counterStorage.getCounter();
        return Response.ok(counterResponse).build();
    }

    /**
     * увеличивает счетчик на 1
     */
    @POST
    @Path(value = "/")
    public Response incrementCounter() {
        counterStorage.incrementAndGet();
        return Response.ok("OK").build();
    }

    /**
     * уменьшает счетчик на значение, которое передается в заголовке «Subtraction-Value»
     */
    @DELETE
    @Path(value = "/")
    public Response decrementCounter() {
        counterStorage.decrementAndGet();
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
            counterStorage.clear();
            return Response.ok("OK").build();
        }
    }
}
