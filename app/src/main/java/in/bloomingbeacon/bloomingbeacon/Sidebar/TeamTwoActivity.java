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

import in.bloomingbeacon.bloomingbeacon.Adapters.TeamTwoAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.TeamTwo;
import in.bloomingbeacon.bloomingbeacon.R;

public class TeamTwoActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView teamTwoRecycler;
    private List<TeamTwo> teamTwoList;
    private TeamTwoAdapter teamTwoAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_two);

        toolbar = (Toolbar) findViewById(R.id.toolbar_team_two);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("2015 - 2016");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        teamTwoRecycler = (RecyclerView) findViewById(R.id.recycler_team_two);
        teamTwoList = new ArrayList<>();
        teamTwoAdapter = new TeamTwoAdapter(this, teamTwoList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        teamTwoRecycler.setLayoutManager(lm);
        teamTwoRecycler.setItemAnimator(new DefaultItemAnimator());
        teamTwoRecycler.setAdapter(teamTwoAdapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("team_two").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TeamTwo teamTwo = dataSnapshot.getValue(TeamTwo.class);
                teamTwoList.add(teamTwo);
                teamTwoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                teamTwoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                teamTwoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                teamTwoAdapter.notifyDataSetChanged();
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
