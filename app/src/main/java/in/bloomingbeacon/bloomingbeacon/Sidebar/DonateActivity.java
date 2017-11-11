package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import in.bloomingbeacon.bloomingbeacon.R;

public class DonateActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView mobileNumber;

    private String number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donate);

        toolbar = (Toolbar) findViewById(R.id.toolbar_donate);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Donate");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        mobileNumber = (TextView) findViewById(R.id.mobile_no_text);
        number = mobileNumber.getText().toString();
        /*
        int start = mobileNumber.getSelectionStart();
        int end = mobileNumber.getSelectionEnd();
        number = number.substring(start, end);
        */

        mobileNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("Number copied", number);
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getApplicationContext(), "Mobile Number copied to Clipboard", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
