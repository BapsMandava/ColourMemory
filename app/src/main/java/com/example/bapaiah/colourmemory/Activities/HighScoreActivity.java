package com.example.bapaiah.colourmemory.Activities;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


import com.example.bapaiah.colourmemory.Entities.Player;
import com.example.bapaiah.colourmemory.R;
import com.example.bapaiah.colourmemory.ViewHolders.ScoresViewHolder;

import io.realm.Realm;
import io.realm.RealmBaseAdapter;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;
import io.realm.Sort;

public class HighScoreActivity extends AppCompatActivity {

    ListView scoreList;
    Realm realm;
    RealmResults<Player> players;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_high_score);
        scoreList = (ListView) findViewById(R.id.lvScoreList);


        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);


        Realm realm = Realm.getDefaultInstance();
        players = realm.where(Player.class).findAll();
        players.sort("playerScore", Sort.DESCENDING);

        scoreList.setAdapter(new ScoresAdapter(this, players, false));

     }

    public class ScoresAdapter extends RealmBaseAdapter<Player> implements ListAdapter {

        public ScoresAdapter(Context context,RealmResults<Player> realmResults,boolean automaticUpdate) {
            super(context, realmResults, automaticUpdate);
        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {
            View row = view;
            ScoresViewHolder holder = null;
            if (view == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.single_row_score, parent, false);
                holder = new ScoresViewHolder(row);
                row.setTag(holder);
            } else {
                holder = (ScoresViewHolder) row.getTag();
            }

            Player item = realmResults.get(position);
            holder.playerName.setText(item.getPlayerName());
            holder.playerScore.setText(Integer.toString(item.getPlayerScore()));
            holder.playerRank.setText(Integer.toString(position+1));
            return row;
        }

    }

}


