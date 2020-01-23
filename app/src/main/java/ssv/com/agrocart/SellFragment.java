package ssv.com.agrocart;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class SellFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    View view;
    EditText weight, price;
    Button post;
//    Spinner spinner;
//    DatabaseReference rootRef, demoRef;
//    FirebaseAuth auth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_sell, container, false);

        price = view.findViewById(R.id.etPrice);
        post = view.findViewById(R.id.btnPost);


        final Spinner spinner = view.findViewById(R.id.spinner1);
        ArrayAdapter<CharSequence> arrayAdapter = ArrayAdapter.createFromResource(getContext(), R.array.crops_names, android.R.layout.simple_spinner_item);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(this);
//
//        rootRef = FirebaseDatabase.getInstance().getReference();
//        String userid= auth.getUid();
//        demoRef = rootRef.child("users/" + userid);


        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (weight.getText().toString().isEmpty() || price.getText().toString().isEmpty()) {
                    Toast.makeText(getContext(), "Enter All Details", Toast.LENGTH_SHORT).show();
                } else {
//                    postToFirebase();
//                    demoRef.child("message1").setValue("Hello World");
                    Toast.makeText(getContext(), "adding", Toast.LENGTH_SHORT).show();
                }

            }
        });

        return view;

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String text = adapterView.getItemAtPosition(i).toString();
//        demoRef.child("crop_name").push().setValue(text);
        Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        Toast.makeText(getContext(), "Nothing Selected!", Toast.LENGTH_SHORT).show();
    }
//    private void postToFirebase () {
//        weight = view.findViewById(R.id.etWeight);
//        price= view.findViewById(R.id.etPrice);
////        String name = spinner.getSelectedItem().toString();
//
////        demoRef.child("crop_name").push().setValue(name);
//        demoRef.child("crop_weight").push().setValue(weight);
//        demoRef.child("crop_price").push().setValue(price);
////         Write a message to the database
////        FirebaseDatabase database = FirebaseDatabase.getInstance();
////        DatabaseReference myRef = database.getReference("message");
//        Toast.makeText(getContext(), "adding", Toast.LENGTH_SHORT).show();
////        myRef.setValue("Hello, World!");
//    }
}
