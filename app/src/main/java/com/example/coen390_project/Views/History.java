package com.example.coen390_project.Views;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.coen390_project.Models.User;
import com.example.coen390_project.R;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;


public class History extends AppCompatActivity {

    ListView list_view_history;

    DatabaseReference reference;


    String TAG = "TAG";

    final ArrayList<String> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        getSupportActionBar().setTitle("History");

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        list_view_history= findViewById(R.id.listview_history);
        final ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this,R.layout.list_profile,list);
        list_view_history.setAdapter(arrayAdapter);

        reference = FirebaseDatabase.getInstance().getReference().child("History");

        reference.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String historylog = snapshot.getValue(String.class);
                if(historylog.contains("Opened")) {
                    String[] ReceivedValues = historylog.split("~");
                    User temp = new User();
                    String name = (temp.allUsers).get(ReceivedValues[1]);
                    String toDisplay = "Opened by " + name + " On " + ReceivedValues[2];
                    System.out.println(toDisplay);
                    list.add(0,toDisplay);
                }
                else{
               // list.add(0,historylog);
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}