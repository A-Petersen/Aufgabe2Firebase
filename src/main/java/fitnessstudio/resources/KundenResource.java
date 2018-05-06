package fitnessstudio.resources;

import com.google.firebase.database.DatabaseReference;
import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Buchung;
import fitnessstudio.model.Kunde;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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

    @Path("{KundenNr}")
    public KundeResource getKunden(@PathParam("KundenNr") String kundenNr) {
        return new KundeResource(kundenNr);
    }

    @Path("/{id}/buchungen")
    public BuchungenResource getBuchungen() {
        System.out.println("id/buchung");
        return new BuchungenResource();
    }

    public class BuchungenResource {

        @GET
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Collection<Buchung> getBuchungen(@PathParam("id") String id) {
            System.out.println("GET aus Buchungen:" + FitnessstudioService.buchungen);
            Map<String, Buchung> output = new HashMap<>();
            FitnessstudioService.buchungen.get(id).forEach(buchung -> output.put(buchung.BuchungNr, buchung));
            return output.values();
        }

        @POST
        @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Response postBuchungen(Buchung buchung, @Context UriInfo uriInfo) {

            DatabaseReference ref = FitnessstudioService.kundenRef.push();
            buchung.BuchungNr = ref.getKey();
            ref.setValueAsync(buchung);

            URI uri = uriInfo.getAbsolutePathBuilder().path(buchung.BuchungNr).build();

            return Response.created(uri).entity(buchung).build(); // 201
        }
    }
}
