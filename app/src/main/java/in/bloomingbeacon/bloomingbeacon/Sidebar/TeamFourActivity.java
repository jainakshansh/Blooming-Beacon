package in.bloomingbeacon.bloomingbeacon.Sidebar;

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

import in.bloomingbeacon.bloomingbeacon.Adapters.TeamFourAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.TeamFour;
import in.bloomingbeacon.bloomingbeacon.R;

public class TeamFourActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private RecyclerView teamFourRecycler;
    private List<TeamFour> teamFourList;
    private TeamFourAdapter teamFourAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_four);

        toolbar = (Toolbar) findViewById(R.id.toolbar_team_four);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("2017 - 2018");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        teamFourRecycler = (RecyclerView) findViewById(R.id.recycler_team_four);
        teamFourList = new ArrayList<>();
        teamFourAdapter = new TeamFourAdapter(this, teamFourList);

        RecyclerView.LayoutManager lm = new LinearLayoutManager(this);
        teamFourRecycler.setLayoutManager(lm);
        teamFourRecycler.setItemAnimator(new DefaultItemAnimator());
        teamFourRecycler.setAdapter(teamFourAdapter);

        getData();
    }

    private void getData() {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        databaseReference.child("team_four").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                TeamFour teamFour = dataSnapshot.getValue(TeamFour.class);
                teamFourList.add(teamFour);
                teamFourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                teamFourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                teamFourAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                teamFourAdapter.notifyDataSetChanged();
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
