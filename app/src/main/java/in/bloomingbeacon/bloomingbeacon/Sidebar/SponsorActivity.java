package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Adapters.SponsorAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.SponsorItem;
import in.bloomingbeacon.bloomingbeacon.R;

public class SponsorActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView sponsorRecycler;
    private SponsorAdapter adapter;
    private List<SponsorItem> sponsorItemList;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);

        toolbar = (Toolbar) findViewById(R.id.toolbar_sponsor);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Sponsors");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        sponsorRecycler = (RecyclerView) findViewById(R.id.recycler_sponsor);
        sponsorItemList = new ArrayList<>();
        adapter = new SponsorAdapter(this, sponsorItemList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        sponsorRecycler.setLayoutManager(layoutManager);
        sponsorRecycler.setHasFixedSize(true);
        sponsorRecycler.setItemAnimator(new DefaultItemAnimator());
        sponsorRecycler.setAdapter(adapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("sponsors").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SponsorItem sponsor = dataSnapshot.getValue(SponsorItem.class);
                sponsorItemList.add(sponsor);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
