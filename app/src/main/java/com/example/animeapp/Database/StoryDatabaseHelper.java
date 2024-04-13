package com.example.animeapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.animeapp.Model.Story;

import java.util.ArrayList;
import java.util.List;

public class StoryDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "story_database";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "followed_stories";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_NAME = "name";
    private static final String COLUMN_COVER_IMAGE = "cover_image";
    private static final String COLUMN_AUTHOR = "author";
    private static final String COLUMN_SUMMARY = "summary";
    private static final String COLUMN_CATEGORY = "category";

    public StoryDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY," +
                COLUMN_NAME + " TEXT," +
                COLUMN_COVER_IMAGE + " TEXT," +
                COLUMN_AUTHOR + " TEXT," +
                COLUMN_SUMMARY + " TEXT," +
                COLUMN_CATEGORY + " TEXT)";
        db.execSQL(createTableQuery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addFollowedStory(Story story) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, story.getId());
        values.put(COLUMN_NAME, story.getName());
        values.put(COLUMN_COVER_IMAGE, story.getCoverImage());
        values.put(COLUMN_AUTHOR, story.getAuthor());
        values.put(COLUMN_SUMMARY, story.getSummary());
        values.put(COLUMN_CATEGORY, story.getCategory());
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public void removeFollowedStory(int storyId) {
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_NAME, COLUMN_ID + " = ?", new String[] { String.valueOf(storyId) });
        db.close();
    }

    public boolean isStoryFollowed(int storyId) {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, COLUMN_ID + " = ?", new String[] { String.valueOf(storyId) }, null, null, null);
        boolean isFollowed = (cursor.getCount() > 0);
        cursor.close();
        db.close();
        return isFollowed;
    }
    public List<Story> getAllFollowedStories() {
        List<Story> followedStories = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID));
                String name = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAME));
                String coverImage = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_COVER_IMAGE));
                String author = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_AUTHOR));
                String summary = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SUMMARY));
                String category = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_CATEGORY));
                Story story = new Story(id, name, coverImage, author, summary, category, 0);
                followedStories.add(story);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return followedStories;
    }
}