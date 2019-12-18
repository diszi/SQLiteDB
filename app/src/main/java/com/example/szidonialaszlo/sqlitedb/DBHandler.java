package com.example.szidonialaszlo.sqlitedb;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.pm.InstrumentationInfo;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.StringBuilderPrinter;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by szidonia.laszlo on 2017. 11. 16..
 */

public class DBHandler extends SQLiteOpenHelper {

   private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PlayerDB";
    private static final String TABLE_NAME = "Players";
    private static final String KEY_ID ="id";
    private static final String KEY_NAME="name";
    private static final String KEY_POSITION="position";
    private static final String KEY_HEIGHT="height";
    private static final String[] COLUMNS = {KEY_ID,KEY_NAME,KEY_POSITION,KEY_HEIGHT};



    public DBHandler(Context context) {
        super(context, DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE Players ( id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, position TEXT, height INTEGER )";
        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        this.onCreate(db);
    }

    public void deleteOne(Player player){
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME,"id=?",new String[] {String.valueOf(player.getId())});
        db.close();
    }

    public Player getPlayer(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,COLUMNS,"id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if (cursor != null)
            cursor.moveToFirst();
        Player player=new Player();
        player.setId(Integer.parseInt(cursor.getString(0)));
        player.setName(cursor.getString(1));
        player.setPosition(cursor.getString(2));
        player.setHeight(Integer.parseInt(cursor.getString(3)));
        return player;
    }

    public List<Player> allPlayers(){
            List<Player> players = new LinkedList<Player>();
            String query = "SELECT * FROM "+TABLE_NAME;
            SQLiteDatabase db = this.getWritableDatabase();
            Cursor cursor = db.rawQuery(query,null);
            Player player = null;

            if (cursor.moveToFirst()){
                do{
                    player = new Player();
                    player.setId(Integer.parseInt(cursor.getString(0)));
                    player.setName(cursor.getString(1));
                    player.setPosition(cursor.getString(2));
                    player.setHeight(Integer.parseInt(cursor.getString(3)));
                    players.add(player);
                }while (cursor.moveToNext());
            }
            return players;
    }


    public void addPlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,player.getName());
        values.put(KEY_POSITION,player.getPosition());
        values.put(KEY_HEIGHT, player.getHeight());

        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public int updatePlayer(Player player){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME,player.getName());
        values.put(KEY_POSITION,player.getPosition());
        values.put(KEY_HEIGHT, player.getHeight());

        int i = db.update(TABLE_NAME,values,"id=?",new String[]{String.valueOf(player.getId())});
        db.close();
        return i;
    }
}
