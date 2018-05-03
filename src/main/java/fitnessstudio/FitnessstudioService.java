package fitnessstudio;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import fitnessstudio.model.Anschrift;
import fitnessstudio.model.Kunde;
import fitnessstudio.model.Vertrag;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
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
        anschriftenRef = FirebaseDatabase.getInstance().getReference("anschriften");
        kundenRef = FirebaseDatabase.getInstance().getReference("kunden");
        vertraegeRef = FirebaseDatabase.getInstance().getReference("vertraege");

        // Register change listener on database
        anschriftenRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot data, String prevChildKey) {
                Anschrift anschrift = data.getValue(Anschrift.class);
                anschriften.put(anschrift.anschriftId, anschrift);
            }

            @Override
            public void onChildChanged(DataSnapshot data, String prevChildKey) {
                Anschrift anschrift = data.getValue(Anschrift.class);
                anschriften.put(anschrift.anschriftId, anschrift);
            }

            @Override
            public void onChildRemoved(DataSnapshot data) {
                Anschrift anschrift = data.getValue(Anschrift.class);
                anschriften.remove(anschrift.anschriftId);
            }

            @Override
            public void onChildMoved(DataSnapshot data, String prevChildKey) {}

            @Override
            public void onCancelled(DatabaseError error) {}
        });

    }
}
