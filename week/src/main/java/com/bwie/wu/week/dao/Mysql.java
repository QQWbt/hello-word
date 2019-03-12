package com.bwie.wu.week.dao;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/9 20:24:20
 * @Description:
 */
public class Mysql extends SQLiteOpenHelper {
    public Mysql(Context context){
        super(context,"bw.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE jsonlist(id INTEGER PRIMARY KEY AUTOINCREMENT,title TEXT,url TEXT,thumbnail_pic_s TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


}
