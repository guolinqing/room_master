package com.example.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao // Database aceess object 访问数据库操作的接口
public interface WordDao { // 在这里声明数据库的操作，如增删改查
    @Insert
    void insertWords(Word... word); // 可以传递三个参数

    @Update
    void updatWords(Word... words);

    @Delete
    void deleteWords(Word... words);

    @Query("DELETE FROM WORD")
    void deleteAllWords();

    @Query("SELECT * FROM WORD ORDER BY ID DESC") // 降序排列
    List<Word> getAllWords();
}
