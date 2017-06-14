package uk.co.example.serviceName;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.core.MultivaluedMap;

public class CorsFilter implements ContainerResponseFilter {
    private static final String TWENTY_FOUR_HOURS = String.valueOf(60 * 60 * 24);

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        MultivaluedMap<String, Object> headers = responseContext.getHeaders();
        headers.add("Access-Control-Allow-Methods", "POST, GET, OPTIONS");
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Max-Age", TWENTY_FOUR_HOURS);
        headers.add("Access-Control-Allow-Headers", "Cache-Control, Content-Language, Content-Type, Expires, Last-Modified, Pragma");
    }
}
