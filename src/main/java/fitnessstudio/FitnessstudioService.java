package fitnessstudio;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.*;
import fitnessstudio.model.Anschrift;
import fitnessstudio.model.Kunde;
import fitnessstudio.model.Vertrag;
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

public class FitnessstudioService {

    public static Map<String, Anschrift> anschriften = new HashMap<>();
    public static DatabaseReference anschriftenRef;
    public static Map<String, Kunde> kunden = new HashMap<>();
    public static DatabaseReference kundenRef;
    public static Map<String, Vertrag> vertraege = new HashMap<>();
    public static DatabaseReference vertraegeRef;

    public static void main(String[] args) throws IOException, FileNotFoundException {

        // Connect to Firebase database
        FileInputStream serviceAccount = new FileInputStream("firebase.json");
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                .setDatabaseUrl("https://aufgabe2firebase.firebaseio.com").build();
        FirebaseApp.initializeApp(options);
        anschriftenRef = FirebaseDatabase.getInstance().getReference("Anschrift");
        kundenRef = FirebaseDatabase.getInstance().getReference("kunde");
        vertraegeRef = FirebaseDatabase.getInstance().getReference("Vertrag");

        // Register change listener on database
        anschriftenRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String prevChildKey) {
                Anschrift anschrift1 = data.getValue(Anschrift.class);
                Anschrift anschrift = data.getValue(Anschrift.class);
                System.out.println("ID: " + anschrift.AnschriftNr);
                System.out.println("Ort: " + anschrift.Ort);
                System.out.println("E-Mail: " + anschrift.eMail);
                anschriften.put(anschrift1.AnschriftNr, anschrift1);
            }

            @Override
            public void onChildChanged(DataSnapshot data, String prevChildKey) {
                Anschrift anschrift = data.getValue(Anschrift.class);
                anschriften.put(anschrift.AnschriftNr, anschrift);
            }

            @Override
            public void onChildRemoved(DataSnapshot data) {
                Anschrift anschrift = data.getValue(Anschrift.class);
                anschriften.remove(anschrift.AnschriftNr);
            }

            @Override
            public void onChildMoved(DataSnapshot data, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError error) {}
        });

//        anschriftenRef.addValueEventListener(new ValueEventListener() {
//             @Override
//             public void onDataChange(DataSnapshot dataSnapshot) {
//                 Anschrift anschrift = dataSnapshot.getValue(Anschrift.class);
//                 System.out.println(anschrift);
//             }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getCode());
//            }
//        });



            // Register change listener on database
        kundenRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String prevChildKey) {
                Kunde kunde = data.getValue(Kunde.class);
                kunden.put(kunde.kundenNr, kunde);
            }

            @Override
            public void onChildChanged(DataSnapshot data, String prevChildKey) {
                Kunde kunde = data.getValue(Kunde.class);
                kunden.put(kunde.kundenNr, kunde);
            }

            @Override
            public void onChildRemoved(DataSnapshot data) {
                Kunde kunde = data.getValue(Kunde.class);
                kunden.remove(kunde.kundenNr, kunde);
            }

            @Override
            public void onChildMoved(DataSnapshot data, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        // Register change listener on database
        vertraegeRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String prevChildKey) {
                Vertrag vertrag = data.getValue(Vertrag.class);
                vertraege.put(vertrag.VertragsNr, vertrag);
            }

            @Override
            public void onChildChanged(DataSnapshot data, String prevChildKey) {
                Vertrag vertrag = data.getValue(Vertrag.class);
                vertraege.put(vertrag.VertragsNr, vertrag);
            }

            @Override
            public void onChildRemoved(DataSnapshot data) {
                Vertrag vertrag = data.getValue(Vertrag.class);
                vertraege.remove(vertrag.VertragsNr, vertrag);
            }

            @Override
            public void onChildMoved(DataSnapshot data, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError error) {}
        });

        // Start HTTP server
        ResourceConfig rc = new ResourceConfig().packages("fitnessstudio");
        HttpServer server = GrizzlyHttpServerFactory.createHttpServer(URI.create("http://localhost:8081"), rc, true);
        System.out.println("Hit enter to stop HTTP server.");
        System.in.read();
        server.shutdownNow();


    }
}
