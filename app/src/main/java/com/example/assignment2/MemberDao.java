package com.example.assignment2;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface MemberDao {

    @Insert
    void insert(Member member);

    @Delete
    void delete(Member member);

    @Query("SELECT * from Member_Table ORDER BY name ASC")
    LiveData<List<Member>> getAllMember();
}
