package fitnessstudio.resources;

import fitnessstudio.FitnessstudioService;
import fitnessstudio.model.Kunde;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.core.Response;

public class KundeResource {

    private String kundenNr;

    public KundeResource(String kundenNr) {
        this.kundenNr = kundenNr;
    }

    @GET
    public Response getKunde() {
        Kunde kunde = FitnessstudioService.kunden.get(kundenNr);
        if (kunde == null) {
            return Response.status(404).build();
        }
        return Response.ok(kunde).build();
    }

    @PUT
    public Response putKunde(Kunde kunde) {
        boolean exists = FitnessstudioService.kunden.get(kundenNr) != null;
        if (!exists) {
            return Response.status(404).build();
        } else {
            kunde.kundenNr = kundenNr;
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
