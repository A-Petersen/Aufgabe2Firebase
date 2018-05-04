package fitnessstudio.resources;

import com.google.firebase.database.DatabaseReference;
import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Anschrift;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

@Path("/anschriften")
public class AnschriftenResource {

    @GET

    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Anschrift> getAnschriften() {
        return FitnessstudioService.anschriften.values();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postAnschrift(Anschrift anschrift, @Context UriInfo uriInfo) {

        // Product is saved with a new generated id, which is returned in the response location URI
        DatabaseReference ref = FitnessstudioService.anschriftenRef.push();
        anschrift.AnschriftNr = ref.getKey();
        ref.setValueAsync(anschrift);

        URI uri = uriInfo.getAbsolutePathBuilder().path(anschrift.AnschriftNr).build();

        return Response.created(uri).entity(anschrift).build(); // 201
    }

    @Path("{id}")
    public AnschriftResource getAnschrift(@PathParam("id") String id) {
        return new AnschriftResource(id);
    }

}