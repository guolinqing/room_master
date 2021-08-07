package com.example.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    WordDatabase wordDatabase;
    WordDao wordDao;
    TextView textView;
    Button buttonInsert,buttonUpdate,buttonDelete,buttonClear;


    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView2);
        // allowMainThreadQueries() 强制允许在主线程执行，真实开发中不要这么做
        wordDatabase = Room.databaseBuilder(this,WordDatabase.class,"word_database").allowMainThreadQueries().build();
        wordDao = wordDatabase.getWordDao();

        updateView();

        buttonInsert = findViewById(R.id.buttonInsert);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonClear = findViewById(R.id.buttonClear);


        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word1 = new Word("Hello","你好");
                Word word2 = new Word("World","世界");
                wordDao.insertWords(word1,word2);
                updateView();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                wordDao.deleteAllWords();
                updateView();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("good","真棒");
                word.setId(20);
                wordDao.updatWords(word);
                updateView();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Word word = new Word("", "");
                word.setId(20);
                wordDao.deleteWords(word);
                updateView();
            }
        });

    }

    void updateView(){
        List<Word> list = wordDao.getAllWords();
        String text = "";
        for(int i = 0; i < list.size(); i++){
            Word word = list.get(i);
            text += word.getId() + ":" + word.getWord() + ":" + word.getChineseMeaning() + "\n";
        }
        textView.setText(text);
    }
}
