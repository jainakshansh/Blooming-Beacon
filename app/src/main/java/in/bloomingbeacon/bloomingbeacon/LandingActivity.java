package in.bloomingbeacon.bloomingbeacon;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import androidx.annotation.NonNull;
import com.google.android.material.navigation.NavigationView;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;
import androidx.appcompat.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import in.bloomingbeacon.bloomingbeacon.Adapters.ImageAdapter;
import in.bloomingbeacon.bloomingbeacon.Objects.EventLatest;
import in.bloomingbeacon.bloomingbeacon.Objects.EventsListItem;
import in.bloomingbeacon.bloomingbeacon.Objects.ImageItem;
import in.bloomingbeacon.bloomingbeacon.Objects.WeeklyUpdates;
import in.bloomingbeacon.bloomingbeacon.Sidebar.AboutUsActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.ContactUsActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.DonateActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.LoginActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.OurVisionActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.SponsorActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.TeamFourActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.TeamOneActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.TeamThreeActivity;
import in.bloomingbeacon.bloomingbeacon.Sidebar.TeamTwoActivity;

public class LandingActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    private RecyclerView imageRecycler;
    private List<ImageItem> imageItemList;
    private ImageAdapter imageAdapter;

    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;
    private static boolean calledFBDB = false;

    private ImageView facebook, website, instagram, youtube;

    private Typeface bold, medium, regular;

    private TextView event_date, event_date_extra, event_name, event_description, event_location;
    private Button event_more;
    private List<EventsListItem> eventsItemList;

    private LinearLayout link_wu;
    private TextView time_wu, update_wu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing);

        toolbar = (Toolbar) findViewById(R.id.toolbar_landing);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Blooming Beacon");
        }

        navigationView = (NavigationView) findViewById(R.id.navigation_view_landing);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout_landing);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.nav_open, R.string.nav_close);
        toggle.syncState();

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo == null) {
            Toast.makeText(this, "Please check your internet connection!", Toast.LENGTH_SHORT).show();
        }

        bold = Typeface.createFromAsset(getAssets(), "fonts/poppinsb.ttf");
        medium = Typeface.createFromAsset(getAssets(), "fonts/poppinsm.ttf");
        regular = Typeface.createFromAsset(getAssets(), "fonts/poppinsr.ttf");

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.home_landing:
                        startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                        finish();
                        break;
                    case R.id.about_us:
                        startActivity(new Intent(getApplicationContext(), AboutUsActivity.class));
                        break;
                    case R.id.our_vision:
                        startActivity(new Intent(getApplicationContext(), OurVisionActivity.class));
                        break;
                    case R.id.chat_landing:
                        Toast.makeText(LandingActivity.this, "Coming Soon!", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id._17_18:
                        startActivity(new Intent(getApplicationContext(), TeamFourActivity.class));
                        break;
                    case R.id._16_17:
                        startActivity(new Intent(getApplicationContext(), TeamThreeActivity.class));
                        break;
                    case R.id._15_16:
                        startActivity(new Intent(getApplicationContext(), TeamTwoActivity.class));
                        break;
                    case R.id._14_15:
                        startActivity(new Intent(getApplicationContext(), TeamOneActivity.class));
                        break;
                    case R.id.contact_developer:
                        Intent emailDevIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "jainakshansh@outlook.com", null));
                        startActivity(Intent.createChooser(emailDevIntent, "Choose e-mail provider"));
                        break;
                    case R.id.sponsor_landing:
                        startActivity(new Intent(getApplicationContext(), SponsorActivity.class));
                        break;
                    case R.id.donate_bb:
                        startActivity(new Intent(getApplicationContext(), DonateActivity.class));
                        break;
                    case R.id.login_register:
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                        break;
                    case R.id.exit_application:
                        finish();
                        System.exit(9);
                        break;
                }
                return true;
            }
        });

        imageRecycler = (RecyclerView) findViewById(R.id.recycler_landing);
        imageItemList = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, imageItemList);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, RecyclerView.HORIZONTAL, false);
        imageRecycler.setLayoutManager(layoutManager);
        imageRecycler.setItemAnimator(new DefaultItemAnimator());
        imageRecycler.setAdapter(imageAdapter);

        if (!calledFBDB) {
            FirebaseDatabase.getInstance().setPersistenceEnabled(true);
            calledFBDB = true;
        }
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();

        imageSliders();
        bbLinks();
        eventLatest();
        weeklyUpdates();
    }

    private void imageSliders() {
        databaseReference.child("events").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                ImageItem imageItem = dataSnapshot.getValue(ImageItem.class);
                imageItemList.add(0, imageItem);
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                imageAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

        databaseReference.keepSynced(true);

        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(imageRecycler);
    }

    private void bbLinks() {
        facebook = (ImageView) findViewById(R.id.facebook_bb);
        facebook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/bloomingbeacon/")));
            }
        });

        website = (ImageView) findViewById(R.id.website_bb);
        website.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.bloomingbeacon.co.in/")));
            }
        });

        instagram = (ImageView) findViewById(R.id.instagram_bb);
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.instagram.com/beaconblooming/")));
            }
        });

        youtube = (ImageView) findViewById(R.id.youtube_bb);
        youtube.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/channel/UC9F9fCEZcFO7vj8ZozMxPjw")));
            }
        });
    }

    private void eventLatest() {
        event_date = (TextView) findViewById(R.id.event_date_latest);
        event_date.setTypeface(bold);
        event_date_extra = (TextView) findViewById(R.id.event_date_extra_latest);
        event_date_extra.setTypeface(medium);
        event_name = (TextView) findViewById(R.id.event_name_latest);
        event_name.setTypeface(bold);
        event_description = (TextView) findViewById(R.id.event_description_latest);
        event_description.setTypeface(regular);
        event_location = (TextView) findViewById(R.id.event_location_latest);
        event_location.setTypeface(medium);
        event_more = (Button) findViewById(R.id.view_more_events);
        event_more.setTypeface(bold);

        event_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), EventsListActivity.class));
            }
        });

        eventsItemList = new ArrayList<>();
        databaseReference.child("event_latest").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                EventLatest latest = dataSnapshot.getValue(EventLatest.class);
                event_date.setText(latest.getEvent_date());
                event_date_extra.setText(latest.getEvent_date_more());
                event_location.setText(latest.getEvent_location());
                event_description.setText(latest.getEvent_description());
                event_name.setText(latest.getEvent_name());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                EventLatest latest = dataSnapshot.getValue(EventLatest.class);
                event_date.setText(latest.getEvent_date());
                event_date_extra.setText(latest.getEvent_date_more());
                event_location.setText(latest.getEvent_location());
                event_description.setText(latest.getEvent_description());
                event_name.setText(latest.getEvent_name());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                EventLatest latest = dataSnapshot.getValue(EventLatest.class);
                event_date.setText(latest.getEvent_date());
                event_date_extra.setText(latest.getEvent_date_more());
                event_location.setText(latest.getEvent_location());
                event_description.setText(latest.getEvent_description());
                event_name.setText(latest.getEvent_name());
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    private void weeklyUpdates() {
        time_wu = (TextView) findViewById(R.id.time_weekly_updates);
        time_wu.setTypeface(bold);
        update_wu = (TextView) findViewById(R.id.update_weekly_updates);
        update_wu.setTypeface(medium);
        link_wu = (LinearLayout) findViewById(R.id.wu_parent);

        databaseReference.child("weeklyUpdates").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                final WeeklyUpdates weeklyUpdates = dataSnapshot.getValue(WeeklyUpdates.class);
                time_wu.setText(weeklyUpdates.getUpdate_datetime());
                update_wu.setText(weeklyUpdates.getUpdate_text());
                link_wu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (weeklyUpdates.getUpdate_link() != null) {
                            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(weeklyUpdates.getUpdate_link())));
                        }
                    }
                });
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                final WeeklyUpdates weeklyUpdates = dataSnapshot.getValue(WeeklyUpdates.class);
                time_wu.setText(weeklyUpdates.getUpdate_datetime());
                update_wu.setText(weeklyUpdates.getUpdate_text());
                link_wu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(weeklyUpdates.getUpdate_link())));
                    }
                });
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                final WeeklyUpdates weeklyUpdates = dataSnapshot.getValue(WeeklyUpdates.class);
                time_wu.setText(weeklyUpdates.getUpdate_datetime());
                update_wu.setText(weeklyUpdates.getUpdate_text());
                link_wu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(weeklyUpdates.getUpdate_link())));
                    }
                });
            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {
                final WeeklyUpdates weeklyUpdates = dataSnapshot.getValue(WeeklyUpdates.class);
                time_wu.setText(weeklyUpdates.getUpdate_datetime());
                update_wu.setText(weeklyUpdates.getUpdate_text());
                link_wu.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(weeklyUpdates.getUpdate_link())));
                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_landing, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        switch (itemId) {
            case R.id.donate_bb:
                startActivity(new Intent(getApplicationContext(), DonateActivity.class));
                break;
            case R.id.contact_us:
                startActivity(new Intent(getApplicationContext(), ContactUsActivity.class));
                break;
                /*
            case R.id.our_location:
                startActivity(new Intent(getApplicationContext(), OurLocationActivity.class));
                break;
                */
            case R.id.feedback_sgeestion:
                Intent feedSuggestIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", "bloomingbeacon@gmail.com", null));
                feedSuggestIntent.putExtra(Intent.EXTRA_SUBJECT, "Feedback / Suggestions for Blooming Beacon");
                startActivity(Intent.createChooser(feedSuggestIntent, "Choose e-mail provider "));
                break;
            case R.id.rate_us:
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=in.bloomingbeacon.bloomingbeacon")));
                break;
        }
        return true;
    }
}