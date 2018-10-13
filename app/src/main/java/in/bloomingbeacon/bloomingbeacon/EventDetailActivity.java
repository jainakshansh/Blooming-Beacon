package in.bloomingbeacon.bloomingbeacon;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class EventDetailActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private ImageView event_image;
    private TextView event_date_time, event_title, event_desc_detailed;
    private Button event_location_detailed;

    private String image;
    private String datetime, title, description, location;
    private String event_lat, event_long;

    private Typeface bold, medium, regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_detail);

        initViews();

        Intent intent = getIntent();
        image = intent.getStringExtra("IMAGE");
        datetime = intent.getStringExtra("DATETIME");
        title = intent.getStringExtra("TITLE");
        description = intent.getStringExtra("DESC");
        location = intent.getStringExtra("LOCATION");
        event_lat = intent.getStringExtra("LAT");
        event_long = intent.getStringExtra("LONG");

        toolbar = (Toolbar) findViewById(R.id.toolbar_event_detail);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(title);
        }

        bold = Typeface.createFromAsset(getAssets(), "fonts/poppinsb.ttf");
        medium = Typeface.createFromAsset(getAssets(), "fonts/poppinsm.ttf");
        regular = Typeface.createFromAsset(getAssets(), "fonts/poppinsr.ttf");

        Picasso.with(getApplicationContext())
                .load(image)
                .into(event_image);
        event_date_time.setText(datetime);
        event_date_time.setTypeface(medium);
        event_title.setText(title);
        event_title.setTypeface(bold);
        event_desc_detailed.setText(description);
        event_desc_detailed.setTypeface(regular);
        event_location_detailed.setText(location);
        event_location_detailed.setTypeface(medium);

        /*
        event_location_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent mapsIntent = new Intent(getApplicationContext(), OurLocationActivity.class);
                if (!event_lat.isEmpty()) {
                    mapsIntent.putExtra("LAT", event_lat);
                } else {
                    mapsIntent.putExtra("LAT", "12.8230346");
                }
                if (!event_long.isEmpty()) {
                    mapsIntent.putExtra("LONG", event_long);
                } else {
                    mapsIntent.putExtra("LONG", "80.0416032");
                }
                mapsIntent.putExtra("LOCATION", location);
                startActivity(mapsIntent);
            }
        });
        */
    }

    private void initViews() {
        event_image = (ImageView) findViewById(R.id.event_detailed_image);
        event_date_time = (TextView) findViewById(R.id.event_date_time);
        event_title = (TextView) findViewById(R.id.event_title_detailed);
        event_desc_detailed = (TextView) findViewById(R.id.event_desc_detailed);
        event_location_detailed = (Button) findViewById(R.id.event_location_detailed);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
