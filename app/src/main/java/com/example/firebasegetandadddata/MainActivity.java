package com.example.firebasegetandadddata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.firebasegetandadddata.adapter.MyAdapter;
import com.example.firebasegetandadddata.databinding.ActivityMainBinding;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding mainBinding;
    RecyclerView recyclerView;
    DatabaseReference databaseReference;
    MyAdapter myAdapter;
    ArrayList<User> list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ActivityMainBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_main);

        recyclerView = findViewById(R.id.userListRecyclerview);
        databaseReference = FirebaseDatabase.getInstance().getReference("Employee");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



        CuEmployee cuEmployee = new CuEmployee();
        binding.buttonSubmit.setOnClickListener(v -> {

            Employee employee = new Employee(binding.edittextName.getText().toString(),binding.edittextSurname.getText().toString(),binding.edittextAge.getText().toString() , binding.edittextCountry.getText().toString());
            cuEmployee.add(employee).addOnSuccessListener(success ->{
                Toast.makeText(this,"Record is inserted", Toast.LENGTH_SHORT).show();

            }).addOnFailureListener(error->{
                Toast.makeText(this,error.getMessage(), Toast.LENGTH_SHORT).show();

            });

            //update item
         /*  HashMap<String , Object> hashMap = new HashMap<>();
            hashMap.put("name", binding.edittextName.getText().toString());
            hashMap.put("surname", binding.edittextSurname.getText().toString());

            cuEmployee.update("-MpNdmvcROwWoz1w-nic", hashMap).addOnSuccessListener(suc ->{
                Toast.makeText(this,"Record is updated", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,er.getMessage(), Toast.LENGTH_SHORT).show();

            });*/


           /* cuEmployee.removeItem("-MpNdmvcROwWo...").addOnSuccessListener(suc ->{
                Toast.makeText(this,"Record is remove", Toast.LENGTH_SHORT).show();
            }).addOnFailureListener(er->{
                Toast.makeText(this,er.getMessage(), Toast.LENGTH_SHORT).show();

            });*/

        });


        list = new ArrayList<>();
        myAdapter = new MyAdapter(this,list);
        recyclerView.setAdapter(myAdapter);

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    User user = dataSnapshot.getValue(User.class);
                    list.add(user);

                }

                myAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}