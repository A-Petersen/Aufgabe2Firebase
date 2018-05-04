package fitnessstudio.resources;

import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Kunde;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class KundeResource {

    private String kundenNr;

    public KundeResource(String kundenNr) {
        this.kundenNr = kundenNr;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getKunde() {
        Kunde kunde = FitnessstudioService.kunden.get(kundenNr);
        if (kunde == null) {
            return Response.status(404).build();
        }
        return Response.ok(kunde).build();
    }

    @PUT
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putKunde(Kunde kunde, @Context UriInfo uriInfo) {
        boolean exists = FitnessstudioService.kunden.get(kundenNr) != null;
        if (!exists) {
            return Response.status(404).build();
        } else {
            kunde.KundeNr = kundenNr;
            FitnessstudioService.kundenRef.child(kundenNr).setValueAsync(kunde);
            return Response.noContent().build();
        }
    }

    @DELETE
    public Response deleteKunde() {
        FitnessstudioService.kundenRef.child(kundenNr).removeValueAsync();
        return Response.noContent().build();
    }
}
