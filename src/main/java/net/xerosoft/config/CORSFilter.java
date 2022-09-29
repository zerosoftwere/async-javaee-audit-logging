package net.xerosoft.config;

import java.io.IOException;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;

@Provider
public class CORSFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext reqContext, 
        ContainerResponseContext resContext) throws IOException {
       resContext.getHeaders().add("Access-Control-Allow-Origin", "*");
       resContext.getHeaders().add("Access-Control-Allow-Headers", "origin, content-type, accept, authorization");
       resContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
       resContext.getHeaders().add("Access-Control-Allow-Methods", "*");
       resContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}