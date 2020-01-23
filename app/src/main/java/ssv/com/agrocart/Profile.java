package ssv.com.agrocart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class Profile extends AppCompatActivity {



    EditText etName,etPhone;
    Button btnSave, btnEdit;

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        etName = findViewById(R.id.etName);
        etPhone = findViewById(R.id.etPhone);
        btnEdit = findViewById(R.id.btnEdit);
        btnSave = findViewById(R.id.btnSave);

        etName.setEnabled(false);
        etPhone.setEnabled(false);



        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToMain();
            }
        });



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setEnabled(true);
                etPhone.setEnabled(true);
            }
        });




    }
    private void goToMain() {
        Intent j = new Intent(this, HomePage.class);
        startActivity(j);
        finish();
    }
}
