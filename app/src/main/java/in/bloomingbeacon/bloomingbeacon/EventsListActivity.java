package in.bloomingbeacon.bloomingbeacon;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Adapters.EventsListAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.EventsListItem;

public class EventsListActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView eventsRecycler;
    private List<EventsListItem> eventsListItemList;
    private EventsListAdapter eventsListAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events_list);

        toolbar = (Toolbar) findViewById(R.id.toolbar_events_list);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Events");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        eventsRecycler = (RecyclerView) findViewById(R.id.recycler_events_list);
        eventsListItemList = new ArrayList<>();
        eventsListAdapter = new EventsListAdapter(this, eventsListItemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        eventsRecycler.setLayoutManager(layoutManager);
        eventsRecycler.setItemAnimator(new DefaultItemAnimator());
        eventsRecycler.setAdapter(eventsListAdapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("events_list").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EventsListItem eventsItem = dataSnapshot.getValue(EventsListItem.class);
                eventsListItemList.add(0, eventsItem);
                eventsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                eventsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                eventsListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
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
