package com.abdulhadi.abdulwahab.notes;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "note")
public class Note implements Serializable {
    public Note(String note, String lastEdit) {
        this.note = note;
        this.lastEdit = lastEdit;
    }

    public Note() {
    }

    @PrimaryKey(autoGenerate = true)
    public int nid;

    @ColumnInfo(name = "text")
    public String note;

    @ColumnInfo(name = "last_edit")
    public String lastEdit;

    @Override
    public String toString() {
        return note;
        /*return "Note{" +
                "note='" + note + '\'' +
                ", lastEdit='" + lastEdit + '\'' +
                '}';*/
    }
}
