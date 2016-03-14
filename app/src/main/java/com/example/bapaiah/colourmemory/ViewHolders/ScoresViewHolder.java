package com.example.bapaiah.colourmemory.ViewHolders;

import android.view.View;
import android.widget.TextView;

import com.example.bapaiah.colourmemory.R;

/**
 * Created by bapai_000 on 3/8/2016.
 */
public class ScoresViewHolder {

    public TextView playerName;
    public TextView playerScore;
    public TextView playerRank;

    public ScoresViewHolder(View v)
    {

        playerName =(TextView) v.findViewById(R.id.txtPlayerName);
        playerScore =(TextView) v.findViewById(R.id.txtPlayerScore);
        playerRank =(TextView) v.findViewById(R.id.txtPlayerRank);

    }
}
