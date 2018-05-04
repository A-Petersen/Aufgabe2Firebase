package fitnessstudio.resources;

import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Vertrag;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import static fitnessstudio.FitnessstudioService.vertraege;

public class VertragResource {

    String VertragsNr;

    public VertragResource(String VertragsNr) {
        this.VertragsNr = VertragsNr;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getVertrag() {
        Vertrag vertrag = vertraege.get(VertragsNr);
        if (vertrag == null) {
            return Response.status(404).build(); // 404
        }
        return Response.ok(vertrag).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putVertrag(Vertrag vertrag, @Context UriInfo uriInfo) {

        boolean exists = vertraege.get(VertragsNr) != null;
        if (!exists) {
            return Response.status(404).build(); // 404
        } else {
            vertrag.VertragsNr = VertragsNr;
            FitnessstudioService.vertraegeRef.child(VertragsNr).setValueAsync(VertragsNr);
            return Response.noContent().build(); // 204
        }
    }

    @DELETE
    public Response deleteVertrag() {
        // Delete product with id provided in the URI
        FitnessstudioService.vertraegeRef.child(VertragsNr).removeValueAsync();
        return Response.noContent().build(); // 204
    }
}
