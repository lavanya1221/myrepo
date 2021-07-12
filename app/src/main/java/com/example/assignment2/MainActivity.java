package com.example.assignment2;

import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import okhttp3.internal.connection.RealConnection;

import static okhttp3.internal.Internal.instance;

public class MainActivity extends AppCompatActivity  {

    private MemberViewModel memberViewModel;
    private Toolbar toolbar;
    private android.app.Application Application;
    private MemberAdapter adapter;

    List<Member> members;
    private MemberDao memberDao;
    private Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar=findViewById(R.id.toolbar);



     setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("RoomDatabase");




        RecyclerView recyclerView=findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
       recyclerView.setHasFixedSize(true);

       final MemberAdapter adapter = new MemberAdapter(this);
       recyclerView.setAdapter(adapter);



        memberViewModel =  new ViewModelProvider(this).get(MemberViewModel.class);
        memberViewModel.getAllMembers().observe(this,new Observer<List<Member>>(){
            @Override
            public void onChanged( List<Member> members){
                adapter.setMembers(members);
            }
        });

    }


  @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_refresh,menu);
        return true;

    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id=item.getItemId();

        if(id==R.id.refresh){

                  refreshItem();
            Toast.makeText(this, "Refreshed", Toast.LENGTH_SHORT).show();
            return true;
        }



        return super.onOptionsItemSelected(item);
    }

    private void refreshItem() {
        adapter.setMembers(members);
        adapter.notifyDataSetChanged();
    }


}