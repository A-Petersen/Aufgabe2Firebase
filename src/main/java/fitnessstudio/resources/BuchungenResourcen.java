package fitnessstudio.resources;

import com.google.firebase.database.DatabaseReference;
import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Anschrift;
import fitnessstudio.model.Buchung;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("/buchungen")
public class BuchungenResourcen {

    @GET

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Buchung> getBuchungen() {
        return FitnessstudioService.buchungen.values();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postBuchung(Buchung buchung, @Context UriInfo uriInfo) {

        DatabaseReference ref = FitnessstudioService.buchungenRef.push();
        buchung.BuchungNr = ref.getKey();
        ref.setValueAsync(buchung);

        URI uri = uriInfo.getAbsolutePathBuilder().path(buchung.BuchungNr).build();

        return Response.created(uri).entity(buchung).build(); // 201
    }

    @Path("{id}")
    public BuchungResource getBuchung(@PathParam("id") String id) {
        return new BuchungResource(id);
    }

}
