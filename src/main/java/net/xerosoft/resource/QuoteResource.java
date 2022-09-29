package net.xerosoft.resource;

import net.xerosoft.common.Paginate;
import net.xerosoft.dto.QuoteCreateDto;
import net.xerosoft.interceptor.AuditLogger;
import net.xerosoft.model.Quote;
import net.xerosoft.common.Page;
import net.xerosoft.service.QuoteContract;
import net.xerosoft.utils.MapUtil;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/quotes")
@Tag(name = "Quote")
public class QuoteResource {

    @Inject
    private QuoteContract quoteMgr;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @AuditLogger(event = "Quote", description = "Retrieve quotes")
    public Response getQuotes(@QueryParam("author") String author, @BeanParam Page page) {
        Paginate<Quote> response;
        if (author == null || author.trim().isEmpty()) {
            response = quoteMgr.getAll(page);
        } else {
            response = quoteMgr.getByAuthor(author, page);
        }
        return Response.ok(response).build();
    }

    @GET
    @Path("{quote_id}")
    @Produces(MediaType.APPLICATION_JSON)
    @AuditLogger(event = "Quote", description = "Retrieve single quote")
    public Response getById(@PathParam("quote_id") Long id) {
        Quote quote = quoteMgr.getById(id);
        return Response.ok(quote).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @AuditLogger(event = "Quote", description = "Create 1uote")
    public Response create(QuoteCreateDto quoteCreateDto) {
        Quote quote = MapUtil.mapToEntity(quoteCreateDto);
        quoteMgr.create(quote);
        return Response.accepted(null).build();
    }
}
