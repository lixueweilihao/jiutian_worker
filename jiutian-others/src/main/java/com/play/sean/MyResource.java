
package com.play.sean;

import com.sun.jersey.api.container.grizzly2.GrizzlyServerFactory;
import com.sun.jersey.api.core.PackagesResourceConfig;
import com.sun.jersey.api.core.ResourceConfig;
import com.sun.jersey.spi.resource.Singleton;
import org.glassfish.grizzly.http.server.HttpServer;

import javax.ws.rs.*;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.*;
import java.io.IOException;
import java.net.URI;
import java.util.Iterator;

@Singleton
@Path("service")
public class MyResource {

    @Path("{sub_path:[a-zA-Z0-9]*}")
    @GET
    @Consumes({MediaType.TEXT_PLAIN, MediaType.APPLICATION_JSON})
    @Produces(MediaType.TEXT_PLAIN)
    public String getResourceName(
            @PathParam("sub_path") String resourceName,
            @DefaultValue("Just a test!") @QueryParam("desc") String description,
            @Context Request request,
            @Context UriInfo uriInfo,
            @Context HttpHeaders httpHeader) {
        System.out.println(this.hashCode());

//		将HTTP请求打印出来
        System.out.println("****** HTTP request ******");
        StringBuilder strBuilder = new StringBuilder();
        strBuilder.append(request.getMethod() + " ");
        strBuilder.append(uriInfo.getRequestUri().toString() + " ");
        strBuilder.append("HTTP/1.1[\\r\\n]");
        System.out.println(strBuilder.toString());
        MultivaluedMap<String, String> headers = httpHeader.getRequestHeaders();
        Iterator<String> iterator = headers.keySet().iterator();
        while(iterator.hasNext()){
            String headName = iterator.next();
            System.out.println(headName + ":" + headers.get(headName) + "[\\r\\n]");
        }
        System.out.println("[\\r\\n]");
        String responseStr =resourceName + "[" + description + "]";
        return responseStr;
    }

    public static void main(String[] args) {
        URI uri = UriBuilder.fromUri("http://127.0.0.1").port(7777).build();
        ResourceConfig rc = new PackagesResourceConfig("com.sean");
        try {
            HttpServer server = GrizzlyServerFactory.createHttpServer(uri, rc);
            server.start();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            Thread.sleep(1000*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
