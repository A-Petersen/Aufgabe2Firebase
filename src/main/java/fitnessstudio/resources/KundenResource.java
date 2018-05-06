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
import java.util.List;
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
        public Response postBuchungen(Buchung buchung, @PathParam("id") String id, @Context UriInfo uriInfo) {

            DatabaseReference ref = FitnessstudioService.buchungenRefMap.get(id).push();
            buchung.BuchungNr = ref.getKey();
            ref.setValueAsync(buchung);

            URI uri = uriInfo.getAbsolutePathBuilder().path(buchung.BuchungNr).build();

            return Response.created(uri).entity(buchung).build(); // 201
        }

        @GET
        @Path("/{buchungNr}")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Response getBuchung(@PathParam("id") String id, @PathParam("buchungNr") String buchungNr) {

            System.out.println(buchungNr + "-" + id);

            Buchung output = null;
            List<Buchung> searchBuchung = FitnessstudioService.buchungen.get(id);
            for (Buchung b : searchBuchung) {
                if (b.BuchungNr.equals(buchungNr)) output = b;
            }
            if (output == null) return Response.status(404).build();
            return Response.ok(output).build();
        }

        @PUT
        @Path("/{buchungNr}")
        @Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public Response putBuchung(Buchung buchung, @PathParam("id") String id, @PathParam("buchungNr") String buchungNr, @Context UriInfo uriInfo) {
            List<Buchung> searchBuchung = FitnessstudioService.buchungen.get(id);
            boolean exists = false;
            for (Buchung b : searchBuchung) {
                if (b.BuchungNr.equals(buchungNr)) exists = true;
            }
            System.out.println("/" + buchungNr + " - " + buchung.BuchungNr + " - exists:" + exists);
            if (!exists) {
                System.out.println("doesn't exist.");
                return Response.status(404).build();
            } else {
                FitnessstudioService.buchungenRefMap.get(id).child(buchungNr).setValueAsync(buchung);
                return Response.noContent().build();
            }
        }

        @DELETE
        @Path("/{buchungNr}")
        public Response deleteBuchung(@PathParam("id") String id, @PathParam("buchungNr") String buchungNr) {
            System.out.println("ID:" + id + "deleteBuchung:" + buchungNr);
            FitnessstudioService.buchungenRefMap.get(id).child(buchungNr).removeValueAsync();
            return Response.noContent().build();
        }
    }
}
