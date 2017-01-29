package com.example.ahmme.recyclerview;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnLongClickListener{
    boolean is_in_action=false;
    TextView counter_text_View;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    int[] img_id={R.drawable.nozrul,R.drawable.lalon,R.drawable.vashani,R.drawable.faruki,
            R.drawable.chomok,R.drawable.mashrafi,R.drawable.moshfiq,R.drawable.sakib};

    ArrayList<Contact> contacts=new ArrayList<>();
    ArrayList<Contact> selectinList=new ArrayList<>();
    int counter=0;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        recyclerView=(RecyclerView)findViewById(R.id.recyclerView);
        layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);

        counter_text_View=(TextView)findViewById(R.id.counter_text);
        counter_text_View.setVisibility(View.GONE);

        String[] name;
        String[] email;
        name=getResources().getStringArray(R.array.name);
        email=getResources().getStringArray(R.array.email);
        int i=0;
        for (String thisname: name){
            Contact contact=new Contact(img_id[i],thisname,email[i]);
            contacts.add(contact);
            i++;
        }
        adapter=new ContactAdapter(contacts,MainActivity.this);
        recyclerView.setAdapter(adapter);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main,menu);
        return true;
    }

    @Override
    public boolean onLongClick(View v) {
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_action_mode);
        counter_text_View.setVisibility(View.VISIBLE);
        is_in_action=true;
        adapter.notifyDataSetChanged();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        return true;
    }

    public void prepearSelection(View view, int position){
        if(((CheckBox) view).isChecked()){
            selectinList.add(contacts.get(position));
            counter=counter+1;
            updateCounter(counter);
        }else {
            selectinList.remove(contacts.get(position));
            counter=counter-1;
            updateCounter(counter);
        }

    }

    public void updateCounter(int counter){
        if(counter==0) {
            counter_text_View.setText("0 item Selected");
        }else {
            counter_text_View.setText(counter+" item selectde");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.item_delete){
            ContactAdapter contactAdapter=(ContactAdapter) adapter;
            contactAdapter.updateAdapter(selectinList);
            clearActionMode();
        }else if(item.getItemId()==android.R.id.home){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }
        return true;
    }

    public void clearActionMode(){
        is_in_action=false;
        toolbar.getMenu().clear();
        toolbar.inflateMenu(R.menu.menu_activity_main);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        counter_text_View.setVisibility(View.GONE);
        counter_text_View.setText("0 item selected");
        counter=0;
        selectinList.clear();
    }

    @Override
    public void onBackPressed() {
        if(is_in_action){
            clearActionMode();
            adapter.notifyDataSetChanged();
        }else {
            super.onBackPressed();
        }
    }
}
