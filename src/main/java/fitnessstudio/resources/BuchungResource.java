//package fitnessstudio.resources;
//
//import fitnessstudio.FitnessstudioService;
//import fitnessstudio.model.Buchung;
//
//import javax.ws.rs.*;
//import javax.ws.rs.core.Context;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//import javax.ws.rs.core.UriInfo;
//
//public class BuchungResource {
//
//    String id;
//
//    public BuchungResource(String id) {
//        this.id = id;
//    }
//
//    @GET
//    @Path("{kunde}/{id}")
//    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
//    public Response getBuchung(@PathParam("kunde") String kunde, @PathParam("id") String id) {
//        Integer buchungsIndex
//        Buchung buchung = FitnessstudioService.buchungen.get(kunde).get(0);
//        if (buchung == null) return Response.status(404).build();
//        return Response.ok(buchung).build();
//    }
//
////    @PUT
////    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
////    public Response putBuchung(Buchung buchung, @Context UriInfo uriInfo) {
////        if (FitnessstudioService.buchungen.get(id) == null) { //if exists
////            return Response.status(404).build();
////        } else {
////            buchung.BuchungNr = id;
////            FitnessstudioService.buchungenRef.child(id).setValueAsync(buchung);
////            return Response.noContent().build();
////        }
////    }
////
////    @DELETE
////    public Response deleteBuchung() {
////        FitnessstudioService.buchungenRef.child(id).removeValueAsync();
////        return Response.noContent().build(); // 204
////    }
//
//}
