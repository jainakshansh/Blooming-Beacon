package in.bloomingbeacon.bloomingbeacon.Sidebar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatSpinner;
import androidx.appcompat.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import in.bloomingbeacon.bloomingbeacon.LandingActivity;
import in.bloomingbeacon.bloomingbeacon.Objects.RegisterItem;
import in.bloomingbeacon.bloomingbeacon.R;

public class RegisterActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private EditText regName, regEmail, regPassword;
    private EditText regContact, regDOB, regHometown;
    private EditText regYear, regStream, regID;
    private CheckBox regHindi, regEnglish, regTamil, regTelugu;
    private EditText regLanguage;
    private AppCompatSpinner regDepartment;
    private EditText regInterested, regSkills, regHowHelp;
    private RadioGroup regExp;
    private RadioButton yesOrNo;
    private Button confirm;
    private Calendar calendar;
    private DatePickerDialog.OnDateSetListener date;

    private String name, email, password;
    private String contact, dob, hometown;
    private String year, stream, collegeID;
    private boolean hindi, english, tamil, telugu;
    private String language;
    private String department;
    private List<String> knownLangs;
    private String interested, skills, howHelp;
    private String experience;

    private List<String> categories;
    private ArrayAdapter<String> departmentAdapter;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    private int timesRegistered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        toolbar = (Toolbar) findViewById(R.id.toolbar_register);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Register");
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        timesRegistered = 0;

        regName = (EditText) findViewById(R.id.reg_name);
        regEmail = (EditText) findViewById(R.id.reg_email);
        regPassword = (EditText) findViewById(R.id.reg_password);
        regContact = (EditText) findViewById(R.id.reg_phone);
        regDOB = (EditText) findViewById(R.id.reg_dob);
        regHometown = (EditText) findViewById(R.id.reg_hometown);
        regYear = (EditText) findViewById(R.id.reg_graduation);
        regStream = (EditText) findViewById(R.id.reg_stream);
        regID = (EditText) findViewById(R.id.reg_college_id);
        regHindi = (CheckBox) findViewById(R.id.reg_cb_hindi);
        regEnglish = (CheckBox) findViewById(R.id.reg_cb_english);
        regTamil = (CheckBox) findViewById(R.id.reg_cb_tamil);
        regTelugu = (CheckBox) findViewById(R.id.reg_cb_telugu);
        regLanguage = (EditText) findViewById(R.id.reg_language);
        regDepartment = (AppCompatSpinner) findViewById(R.id.reg_department);
        regInterested = (EditText) findViewById(R.id.reg_interested);
        regSkills = (EditText) findViewById(R.id.reg_skills);
        regHowHelp = (EditText) findViewById(R.id.reg_how_help);
        regExp = (RadioGroup) findViewById(R.id.reg_radio_exp);
        confirm = (Button) findViewById(R.id.reg_confirm);
        knownLangs = new ArrayList<>();

        setUpSpinner();

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (timesRegistered < 2) {
                    takeInInfo();
                    registerUser();
                    timesRegistered++;
                } else {
                    Toast.makeText(RegisterActivity.this, "You have already registered!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void takeInInfo() {
        name = regName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            regName.setError("Required");
        }

        email = regEmail.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            regEmail.setError("Required");
        }

        password = regPassword.getText().toString();
        if (TextUtils.isEmpty(password)) {
            regPassword.setError("Required");
        }

        if (password.length() < 8) {
            Toast.makeText(this, "Password too short, enter minimum 8 characters!", Toast.LENGTH_SHORT).show();
        }

        contact = regContact.getText().toString();
        if (TextUtils.isEmpty(contact)) {
            regContact.setError("Required");
        }

        dob = regDOB.getText().toString();
        if (TextUtils.isEmpty(dob)) {
            regDOB.setError("Required");
        }

        hometown = regHometown.getText().toString().trim();
        if (TextUtils.isEmpty(hometown)) {
            regHometown.setError("Required");
        }

        stream = regStream.getText().toString().trim();
        year = regYear.getText().toString().trim();

        collegeID = regID.getText().toString().trim();
        if (TextUtils.isEmpty(collegeID)) {
            regID.setError("Required");
        }

        hindi = regHindi.isChecked();
        if (hindi) {
            knownLangs.add(regHindi.getText().toString());
        }
        english = regEnglish.isChecked();
        if (english) {
            knownLangs.add(regEnglish.getText().toString());
        }
        tamil = regTamil.isChecked();
        if (tamil) {
            knownLangs.add(regTamil.getText().toString());
        }
        telugu = regTelugu.isChecked();
        if (telugu) {
            knownLangs.add(regTelugu.getText().toString());
        }
        language = regLanguage.getText().toString().trim();
        if (!TextUtils.isEmpty(language)) {
            knownLangs.add(language);
        }

        interested = regInterested.getText().toString().trim();
        howHelp = regHowHelp.getText().toString().trim();
        skills = regSkills.getText().toString().trim();

        yesOrNo = (RadioButton) findViewById(regExp.getCheckedRadioButtonId());
        experience = yesOrNo.getText().toString();
    }

    private void setUpSpinner() {
        categories = new ArrayList<>();
        categories.add("Publicity");
        categories.add("Sponsorship");
        categories.add("Creativity");
        categories.add("Documentation");
        categories.add("Surveying");

        departmentAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, categories);
        departmentAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        regDepartment.setAdapter(departmentAdapter);

        regDepartment.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                department = parent.getItemAtPosition(position).toString();
                Toast.makeText(RegisterActivity.this, "You selected: " + department, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                regDepartment.setPrompt("Choose department!");
            }
        });
    }

    private void registerUser() {
        RegisterItem item = new RegisterItem(name, email, contact, dob, hometown, stream, year, collegeID, knownLangs, null, interested, skills, howHelp, experience, false);
        databaseReference.child("registered_users").push().setValue(item);
        if (!email.isEmpty() && !password.isEmpty()) {
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                FirebaseUser user = firebaseAuth.getCurrentUser();
                                if (user != null) {
                                    startActivity(new Intent(getApplicationContext(), LandingActivity.class));
                                    finish();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Authentication Failed!", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
