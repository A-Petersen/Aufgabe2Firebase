package fitnessstudio.resources;

import fitnessstudio.FitnessstudioService;
import fitnessstudio.ShopService;
import fitnessstudio.model.Anschrift;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

public class AnschriftResource {

    String id;

    public AnschriftResource(String id) {
        this.id = id;
    }

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response getAnschrift() {
        Anschrift anschrift = FitnessstudioService.anschriften.get(id);
        if (anschrift == null) return Response.status(404).build();
        return Response.ok(anschrift).build();
    }

    @PUT
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response putAnschrift(Anschrift anschrift, @Context UriInfo uriInfo) {
        if (FitnessstudioService.anschriften.get(id) == null) { //if exists
            return Response.status(404).build();
        } else {
            anschrift.anschriftId = id;
            FitnessstudioService.anschriftenRef.child(id).setValueAsync(anschrift);
            return Response.noContent().build();
        }
    }

    @DELETE
    public Response deleteAnschrift() {
        FitnessstudioService.anschriftenRef.child(id).removeValueAsync();
        return Response.noContent().build(); // 204
    }
}
