package in.hackslash.messsy.complaint;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

import in.hackslash.messsy.R;

public class NoticeActivity extends AppCompatActivity {

    // TODO Declare instances for all UI elements

    RecyclerView r;
    private final String TAG="abcd";
    private final FirebaseFirestore db=FirebaseFirestore.getInstance();
    private final CollectionReference notebookRef = db.collection("MessssyNotice");
    Notice madapter = new Notice(this);
    ArrayList<String> notice=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);
        r = findViewById(R.id.recycler_view);
        RecyclerView.LayoutManager layoutManager;
        layoutManager = new LinearLayoutManager(this);
        r.setLayoutManager(layoutManager);
load();
r.setAdapter(madapter);
    }





    public void load(){
        notebookRef.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                    String note = documentSnapshot.toObject(String.class);

                    notice.add(note);
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d(TAG,e.toString());

            }
        });
        madapter.updatefood(notice);
    }
}
