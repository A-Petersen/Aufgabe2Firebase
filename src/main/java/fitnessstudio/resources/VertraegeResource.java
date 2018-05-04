package fitnessstudio.resources;

import com.google.firebase.database.DatabaseReference;
import fitnessstudio.FitnessstudioService;
import fitnessstudio.ShopService;
import fitnessstudio.model.Product;
import fitnessstudio.model.Vertrag;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;

public class VertraegeResource {

    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Collection<Vertrag> getProducts() {
        return FitnessstudioService.vertraege.values();
    }

    @POST
    @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public Response postVertrag(Vertrag vertrag, @Context UriInfo uriInfo) {

        // Product is saved with a new generated id, which is returned in the response location URI
        DatabaseReference ref = FitnessstudioService.vertraegeRef.push();
        vertrag.VertragsNr = ref.getKey();
        ref.setValueAsync(vertrag);

        URI uri = uriInfo.getAbsolutePathBuilder().path(vertrag.VertragsNr).build();

        return Response.created(uri).entity(vertrag).build(); // 201
    }

    @Path("{VertragsNr}")
    public VertragResource getVertrag(@PathParam("VertragsNr") String VertragsNr) {
        return new VertragResource(VertragsNr);
    }
}
