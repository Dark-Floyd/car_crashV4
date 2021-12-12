package com.example.car_crashv3;
import com.google.gson.Gson;
import java.util.ArrayList;

public class MyDB {

    private ArrayList<Score> records = new ArrayList<>();
    public MyDB(){}

    public static MyDB getDB() {
        Gson gson = new Gson();
        String js = MPSV3.getMe().getString("MY_DB", "");
        MyDB myDB = gson.fromJson(js, MyDB.class);
        if(myDB == null){
            myDB = new MyDB();


            String json = new Gson().toJson(myDB);
            MPSV3.getMe().putString("MY_DB", json);
        }
        return myDB;
    }

    public void setDB(){
        Gson gson = new Gson();
        String json = gson.toJson(this);
        MPSV3.getMe().putString("MY_DB", json);
    }





}
