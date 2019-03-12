package com.bwie.wu.week.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * @Auther: 武丙涛
 * @Date: 2019/1/9 20:23:17
 * @Description:
 */
public class Dao {

    private final SQLiteDatabase db;

    public Dao(Context context){
        Mysql mysql = new Mysql(context);
        db = mysql.getWritableDatabase ();
    }

    public long insert(String table, String nullColumnHack, ContentValues values) {
        return db.insert (table,nullColumnHack,values);
    }

    public Cursor query(String table, String[] columns, String selection,
                        String[] selectionArgs, String groupBy, String having,
                        String orderBy) {
        return db.query (table,columns,selection,selectionArgs,groupBy,having,orderBy);
    }
}
