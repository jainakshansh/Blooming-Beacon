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

import in.bloomingbeacon.bloomingbeacon.Adapters.TeamThreeAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.TeamThree;
import in.bloomingbeacon.bloomingbeacon.R;

public class TeamThreeActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView teamThreeRecycler;
    private List<TeamThree> teamThreeList;
    private TeamThreeAdapter teamThreeAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_three);

        toolbar = (Toolbar) findViewById(R.id.toolbar_team_three);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("2016 - 2017");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        teamThreeRecycler = (RecyclerView) findViewById(R.id.recycler_team_three);
        teamThreeList = new ArrayList<>();
        teamThreeAdapter = new TeamThreeAdapter(this, teamThreeList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        teamThreeRecycler.setLayoutManager(lm);
        teamThreeRecycler.setItemAnimator(new DefaultItemAnimator());
        teamThreeRecycler.setAdapter(teamThreeAdapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("team_three").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TeamThree teamThree = dataSnapshot.getValue(TeamThree.class);
                teamThreeList.add(teamThree);
                teamThreeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                teamThreeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                teamThreeAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                teamThreeAdapter.notifyDataSetChanged();
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
