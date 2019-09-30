package com.abdulhadi.abdulwahab.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , Serializable {

    TextView tv_add_new_note;
    GridView gridView;
    ArrayAdapter<Note> adapter;
    static List<Note> numbers;
    /*static String[] numbers = new String[]{
            "A", "B", "C"};*/
    static AppDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //db = AppDatabase.getInstance(getApplicationContext());

        db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "Note").allowMainThreadQueries().build();
        Note n1 = new Note();
//        n1.note = "first hello!";
//        n1.lastEdit = "19/9";
//
//        db.noteDao().insertAll(n1);

        numbers = new ArrayList<Note>();
       /* numbers.add("A");
        numbers.add("B");
        numbers.add("C");*/
       numbers.addAll(db.noteDao().getAll());
       // numbers.add(db.noteDao().getAll().get(0).note);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();*/
                Intent intent = new Intent(MainActivity.this, type_note.class);
                intent.putExtra("state", "new");
                startActivity(intent);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        // grid
        gridView = (GridView) findViewById(R.id.gridView1);
         adapter = new ArrayAdapter<Note>(this,
                R.layout.gridview_layout, R.id.android_gridview_text, numbers);
        gridView.setAdapter(adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View v,
                                    int position, long id) {
               // Toast.makeText(getApplicationContext(),
                //        numbers[position], Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, type_note.class);
                intent.putExtra("state", "update");
                intent.putExtra("MyClass", (Serializable) numbers.get(position));
                intent.putExtra("note", numbers.get(position).note);
                intent.putExtra("index", position);
                startActivity(intent);
            }
        });
        if (numbers.size() > 0) {
            tv_add_new_note = (TextView) findViewById(R.id.tv_add_new_note);
            tv_add_new_note.setVisibility(View.GONE);
        }

    }

    @Override
    protected void onPostResume() {
        //Toast.makeText(this, "onPostResume", Toast.LENGTH_SHORT).show();
        try {
            adapter.notifyDataSetChanged();

        } catch (IllegalMonitorStateException e) {
        }


        super.onPostResume();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    public static void insert(Note n){
        db.noteDao().insertAll(n);

    }
    public static void update(Note n){
        db.noteDao().update(n);
    }
    public static void delete(Note n){
        db.noteDao().delete(n);
    }

}
