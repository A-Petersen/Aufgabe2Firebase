package shop.resources;

import shop.model.Kunde;

import javax.ws.rs.GET;
import javax.ws.rs.core.Response;

public class KundeResource {
    public Long kundenNr;

    public KundeResource(Long id) {
        this.kundenNr = id;
    }

//    @GET
//    public Response getKunde() {
//        Kunde kunde = FitnessstudioService.kunden.get(kundenNr);
//    }
}
