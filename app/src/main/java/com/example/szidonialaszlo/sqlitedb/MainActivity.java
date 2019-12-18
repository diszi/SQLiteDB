package com.example.szidonialaszlo.sqlitedb;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class MainActivity extends Activity {

    private  DBHandler db ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       db = new DBHandler(this);

        //create some players
        Player player1 = new Player(1,"James","F",203);
        Player player2 = new Player(2,"Nick","F",208);
        Player player3 = new Player(3, "Robert","C",220);

        //add them to db
        db.addPlayer(player1);
        db.addPlayer(player2);
        db.addPlayer(player3);

        List<Player> players = db.allPlayers();

        if (players != null){
            String[] itemsNames = new String[players.size()];

            for (int i=0;i<players.size();i++){
                itemsNames[i] = players.get(i).toString();
                Log.d("Players:",itemsNames[i]);
            }

        }
    }
}
