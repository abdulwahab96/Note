package com.abdulhadi.abdulwahab.notes;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {
    @Query("SELECT * FROM note")
    List<Note> getAll();

    @Query("SELECT * FROM note WHERE nid IN (:noteIds)")
    List<Note> loadAllByIds(int[] noteIds);

    /*@Query("SELECT * FROM note WHERE text LIKE :first AND " +
            "last_edit LIKE :last LIMIT 1")
    Note findByName(String first, String last);*/

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Update
    void update(Note note);
}
