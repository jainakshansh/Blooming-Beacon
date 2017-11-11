package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import in.bloomingbeacon.bloomingbeacon.R;

public class AboutUsActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private TextView title, text;
    private Typeface bold, regular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        toolbar = (Toolbar) findViewById(R.id.toolbar_about_us);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("About Us");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        title = (TextView) findViewById(R.id.au_title);
        text = (TextView) findViewById(R.id.au_text);
        bold = Typeface.createFromAsset(getAssets(), "fonts/poppinsb.ttf");
        regular = Typeface.createFromAsset(getAssets(), "fonts/poppinsr.ttf");

        title.setTypeface(bold);
        text.setTypeface(regular);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
