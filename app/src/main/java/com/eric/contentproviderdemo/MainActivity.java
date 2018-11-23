package com.eric.contentproviderdemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Uri bookuri = Uri.parse("content://com.eric.contentproviderdemo.provider/book");
        ContentValues values = new ContentValues();
        values.put("_id", 6);
        values.put("name", "艺术");
        getContentResolver().insert(bookuri, values);
        Cursor bookCursor = getContentResolver().query(bookuri, new String[]{"_id", "name"}, null, null, null);
        while (bookCursor.moveToNext()) {
            Book book = new Book();
            book.bookId = bookCursor.getInt(0);
            book.bookName = bookCursor.getString(1);
            Log.e(TAG, "query book:" + book.toString());
        }
        bookCursor.close();

    }
}
