package net.xerosoft.resource;

import net.xerosoft.common.Page;
import net.xerosoft.common.Paginate;
import net.xerosoft.model.AuditLog;
import net.xerosoft.service.AuditLogService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@Path("/audits")
@Tag(name = "Audit")
public class AuditLogResource {

    @Inject
    private AuditLogService auditLogService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getLogs(@BeanParam Page page) {
        Paginate<AuditLog> response = auditLogService.getAll(page);
        return Response.ok(response).build();
    }

    @GET
    @Path("{audit_id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("audit_id") Long id) {
        AuditLog audit = auditLogService.getById(id);
        return Response.accepted(audit).build();
    }
}
