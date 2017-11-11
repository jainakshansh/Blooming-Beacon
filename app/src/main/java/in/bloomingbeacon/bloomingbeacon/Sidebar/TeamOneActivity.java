package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Adapters.TeamOneAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.TeamOne;
import in.bloomingbeacon.bloomingbeacon.R;

public class TeamOneActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView teamOneRecycler;
    private List<TeamOne> teamOneList;
    private TeamOneAdapter teamOneAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_one);

        toolbar = (Toolbar) findViewById(R.id.toolbar_team_one);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("2014 - 2015");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        teamOneRecycler = (RecyclerView) findViewById(R.id.recycler_team_one);
        teamOneList = new ArrayList<>();
        teamOneAdapter = new TeamOneAdapter(this, teamOneList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        teamOneRecycler.setLayoutManager(lm);
        teamOneRecycler.setItemAnimator(new DefaultItemAnimator());
        teamOneRecycler.setAdapter(teamOneAdapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("team_one").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TeamOne teamOne = dataSnapshot.getValue(TeamOne.class);
                teamOneList.add(teamOne);
                teamOneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                teamOneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                teamOneAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                teamOneAdapter.notifyDataSetChanged();
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
