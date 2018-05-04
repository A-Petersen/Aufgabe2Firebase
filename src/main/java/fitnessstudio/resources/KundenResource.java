package fitnessstudio.resources;

import com.google.firebase.database.DatabaseReference;
import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Kunde;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("/kunden")
public class KundenResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Kunde> getKunden() {
        return FitnessstudioService.kunden.values();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postKunden(Kunde kunde, @Context UriInfo uriInfo) {

        DatabaseReference ref = FitnessstudioService.kundenRef.push();
        kunde.KundeNr = ref.getKey();
        ref.setValueAsync(kunde);

        URI uri = uriInfo.getAbsolutePathBuilder().path(kunde.KundeNr).build();

        return Response.created(uri).entity(kunde).build(); // 201
    }

    @Path("{id}")
    public KundeResource getKunden(@PathParam("id") String kundenNr) {
        return new KundeResource(kundenNr);
    }


}
