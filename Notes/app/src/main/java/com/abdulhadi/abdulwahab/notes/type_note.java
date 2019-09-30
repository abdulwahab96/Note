package com.abdulhadi.abdulwahab.notes;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import static com.abdulhadi.abdulwahab.notes.MainActivity.delete;
import static com.abdulhadi.abdulwahab.notes.MainActivity.insert;
import static com.abdulhadi.abdulwahab.notes.MainActivity.numbers;
import static com.abdulhadi.abdulwahab.notes.MainActivity.update;

public class type_note extends AppCompatActivity {

    EditText et_note;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_note);

        et_note = (EditText) findViewById(R.id.et_edit_note);

        et_note.setText(getIntent().getStringExtra("note"));
    }

    @Override
    public void onBackPressed() {
        String note = et_note.getText().toString();
        String state = getIntent().getStringExtra("state");

        if (note.trim().length() > 0 && state.equalsIgnoreCase("new")) {
            // Add new Note
            Note note_obj = new Note(note, "19/9");
            numbers.add(note_obj);
            insert(note_obj);

        } else if (state.equalsIgnoreCase("update")) {
            // delete note if emptey
            if (!(note.trim().length() > 0)) {
                Note n = (Note) getIntent().getSerializableExtra("MyClass");
                delete(n);
                numbers.remove(getIntent().getIntExtra("index", 0));
            } else {
                // update note
                Note n = (Note) getIntent().getSerializableExtra("MyClass");
                numbers.set(getIntent().getIntExtra("index", 0), n);
                n.note = note;
                update(n);
            }
        }

        super.onBackPressed();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.type_note_done, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.type_done) {
            onBackPressed();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
