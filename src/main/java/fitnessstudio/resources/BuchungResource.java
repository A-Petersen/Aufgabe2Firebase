package fitnessstudio.resources;

import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Anschrift;
import fitnessstudio.model.Buchung;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class BuchungResource {

    String id;

    public BuchungResource(String id) {
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getBuchung() {
        Buchung buchung = FitnessstudioService.buchungen.get(id);
        if (buchung == null) return Response.status(404).build();
        return Response.ok(buchung).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putBuchung(Buchung buchung, @Context UriInfo uriInfo) {
        if (FitnessstudioService.buchungen.get(id) == null) { //if exists
            return Response.status(404).build();
        } else {
            buchung.BuchungNr = id;
            FitnessstudioService.buchungenRef.child(id).setValueAsync(buchung);
            return Response.noContent().build();
        }
    }

    @DELETE
    public Response deleteBuchung() {
        FitnessstudioService.buchungenRef.child(id).removeValueAsync();
        return Response.noContent().build(); // 204
    }

}
