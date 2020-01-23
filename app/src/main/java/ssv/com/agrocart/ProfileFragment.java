package ssv.com.agrocart;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.annotations.Nullable;

import java.util.Objects;

import androidx.fragment.app.Fragment;

import static androidx.constraintlayout.widget.Constraints.TAG;

public class ProfileFragment extends Fragment {


    private DatabaseReference reff;
    private User user;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
       View view = inflater.inflate(R.layout.fragment_profile,container, false);




        final EditText etName = view.findViewById(R.id.etName);
        final EditText etPhone = view.findViewById(R.id.etPhone);
        final Button btnEdit = view.findViewById(R.id.btnEdit);
        Button btnSave = view.findViewById(R.id.btnSave);
        final Button btnLogout = view.findViewById(R.id.btnLogout);
        final EditText etEmail = view.findViewById(R.id.etEmail);

        etName.setEnabled(false);
        etPhone.setEnabled(false);


        user = new User();

        reff = FirebaseDatabase.getInstance().getReference().child("user");
        if (isInternetConnection()) {
            try {
                reff.child("user").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        if (dataSnapshot.exists()) {
                            User user = dataSnapshot.getValue(User.class);
                            String name = user.getName();
                            String email = user.getEmail();
//                            long phone = Long.valueOf(user.getNumber());

                            etEmail.setText(email);
                            etName.setText(name);
//                            etPhone.setText((int) phone);

                        } else {
                            Toast.makeText(getContext(), "Add detail", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        Log.w(TAG, "getUser:onCancelled", databaseError.toException());

                    }
                });
            }catch (Exception e){
                Toast.makeText(getContext(), ""+e, Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getContext(), "Check Internet Connection", Toast.LENGTH_SHORT).show();
        }


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setEnabled(false);
                etPhone.setEnabled(false);
                etEmail.setEnabled(false);


                long number = Long.parseLong(etPhone.getText().toString().trim());

                user.setName(etName.getText().toString().trim());
                user.setEmail(etEmail.getText().toString().trim());
                user.setNumber(number);



                reff.child("user").setValue(user);
                if(isInternetConnection()) {
                    Toast.makeText(getContext(), "Data Updated ", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getContext(), "check Internet connection", Toast.LENGTH_SHORT).show();
                }
            }
        });



        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                etName.setEnabled(true);
                etPhone.setEnabled(true);
                etEmail.setEnabled(true);
            }
        });

        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs= PreferenceManager.getDefaultSharedPreferences(getContext());
                prefs.edit().putBoolean("isLogin",false).apply();
                Toast.makeText(getContext(), "Logging out", Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getContext(), MainActivity.class);
                startActivity(i);
                Objects.requireNonNull(getActivity()).finish();

            }
        });
        return view;
    }
    public boolean isInternetConnection()
    {

        ConnectivityManager connectivityManager =  (ConnectivityManager) Objects.requireNonNull(getContext()).getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getActiveNetworkInfo().isConnectedOrConnecting();
    }
}
