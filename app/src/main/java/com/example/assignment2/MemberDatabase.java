package com.example.assignment2;


import android.content.Context;
import android.os.AsyncTask;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


@Database(entities={Member.class}, version=1)
public  abstract class MemberDatabase extends RoomDatabase {

    private static MemberDatabase instance;
    private static Context activity;

    public abstract MemberDao MemberDao();

    public static synchronized MemberDatabase getInstance(Context context) {
        activity=context.getApplicationContext();
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MemberDatabase.class, "Member_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomcallback)
                    .build();
        }
        return instance;
    }

    public static RoomDatabase.Callback roomcallback = new RoomDatabase.Callback() {
        public void onCreate(SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MemberDao memberDao;

        private PopulateDbAsyncTask(MemberDatabase db) {
            memberDao = db.MemberDao();
        }

        protected Void doInBackground(Void... voids) {
            //memberDao.insert(new Member("Lavanya", "agencyone", "https://imgur.com/mC9naw6.png", "Wikipedia", "Active"));
            fillWithStartingData(activity);
            return null;

        }

    }

    private static void fillWithStartingData(Context context){
        MemberDao dao= getInstance(context).MemberDao();
        JSONArray members=loadJSONArray(context);
        try{
            for (int i=0;i<=members.length();i++){
                JSONObject member = members.getJSONObject(i);

                String name=member.getString("name");
                String agency=member.getString("agency");
                String image=member.getString("image");
                String wikipedia=member.getString("wikipedia");
                String status= member.getString("status");
               dao.insert(new Member(name,agency,image,wikipedia,status));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    private static JSONArray loadJSONArray(Context context) {
        StringBuilder builder = new StringBuilder();
        InputStream in = context.getResources().openRawResource(R.raw.members);
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));

        String line;
        try {
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            JSONObject json = new JSONObject(builder.toString());
            return json.getJSONArray("members");
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}

