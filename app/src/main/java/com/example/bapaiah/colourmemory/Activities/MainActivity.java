package com.example.bapaiah.colourmemory.Activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.bapaiah.colourmemory.Activities.HighScoreActivity;
import com.example.bapaiah.colourmemory.ViewHolders.ColourViewHolder;
import com.example.bapaiah.colourmemory.Entities.Player;
import com.example.bapaiah.colourmemory.Entities.ColourMemory;
import com.example.bapaiah.colourmemory.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MainActivity extends AppCompatActivity {

    protected TextView scoreView;
    GridView colourGrid;
    AlertDialog playerNameDialogBuilder;
    View nameView;
    EditText playerNameInput;
    Button button,Submit,Cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        colourGrid = (GridView) findViewById(R.id.gridView);
        colourGrid.setAdapter(new colorMemoryAdapter(this));
        scoreView = (TextView) findViewById(R.id.txtCurrentScoreValue);
        scoreView.setText(Integer.toString(0));
        LayoutInflater li = LayoutInflater.from(this);
        nameView = li.inflate(R.layout.dialog_player_name, null);
        playerNameDialogBuilder = new AlertDialog.Builder(this)
                .setView(nameView)
                .setTitle(R.string.title)
                .setMessage(R.string.message)
                .setPositiveButton(android.R.string.ok, null)
                .setNegativeButton(android.R.string.cancel, null)
                .create();

        playerNameInput = (EditText) nameView.findViewById(R.id.editTextDialogUserInput);
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);

        button = (Button) findViewById(R.id.btnHighScore);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent highScoreIntent = new Intent(v.getContext(), HighScoreActivity.class);
                startActivity(highScoreIntent);
            }
        });
    }


    class colorMemoryAdapter extends BaseAdapter {

        int colour1, colour2, position1, position2 = 0;
        int score =   0;
        int flag = 0;
        ColourViewHolder holder;
        ColourViewHolder holder1 = null;
        ColourViewHolder holder2 = null;
        ColourMemory colourMemory;
        Context context;
        int white;
        ArrayList<Integer> positions = new ArrayList<Integer>();
        HashMap<Integer, ColourMemory> colourPosMap = new HashMap<>();
        int count = 0;

        colorMemoryAdapter(Context context) {
            this.context = context;
            int[] colourID = {R.drawable.colour1,R.drawable.colour2, R.drawable.colour3, R.drawable.colour4, R.drawable.colour5, R.drawable.colour6, R.drawable.colour7, R.drawable.colour8};
            white = R.drawable.white;
            for (int i = 0; i < 16; i++) {
                positions.add(new Integer(i));
            }
            Collections.shuffle(positions);
            positions.toArray();
            for (int j = 0; j < positions.size(); j++) {

                if (j >= colourID.length && count == colourID.length) {
                    count = 0;
                }
                colourMemory = new ColourMemory(colourID[count], positions.get(j));
                colourPosMap.put(positions.get(j), colourMemory);
                count++;
            }
        }

        @Override
        public int getCount() {
            return colourPosMap.size();
        }

        @Override
        public ColourMemory getItem(int position) {
            return colourPosMap.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View view, ViewGroup parent) {

            View row = view;
            ColourViewHolder holder = null;
            if (row == null) {
                LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                row = inflater.inflate(R.layout.colour_item, parent, false);
                holder = new ColourViewHolder(row);
                row.setTag(holder);

            } else {
                holder = (ColourViewHolder) row.getTag();
            }
            ColourMemory temp = colourPosMap.get(position);
            holder.colourMemory = temp;

            if (temp.colorID > 0) {
                holder.colourFlipImage.setFrontImage(R.drawable.cover);
                holder.colourFlipImage.setRearImage(temp.colorID);
                holder.colourFlipImage.setTag(holder);
            } else {
                    holder.colourFlipImage.removeViewAt(temp.position);
                    holder.colourFlipImage.setRearImage(temp.position);
                    holder.colourFlipImage.setTag(holder);
            }
            holder.colourFlipImage.setOnClickListener(listener);
            return row;
        }


        protected View.OnClickListener listener = new View.OnClickListener() {

            @Override
            public void onClick(final View v) {

                holder = (ColourViewHolder) v.getTag();
                if (holder.colourMemory.colorID > 0) {
                    if (holder1 == null) {
                        holder1 = (ColourViewHolder) v.getTag();
                        holder1.colourFlipImage.flip(true);
                    } else if (colour1 == holder.colourMemory.colorID && position1 == holder.colourMemory.position) {
                        holder1.colourFlipImage.flip(false);
                        resetValues();
                        return;
                    } else if (holder2 == null) {
                        holder2 = (ColourViewHolder) v.getTag();
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        holder2.colourFlipImage.flip(true);
                    } else if (colour2 == holder.colourMemory.colorID && position2 == holder.colourMemory.position) {
                        holder2.colourFlipImage.flip(false);
                        resetValues();
                        return;
                    }
                    if (colour1 == 0) {
                        colour1 = holder1.colourMemory.colorID;
                        position1 = holder1.colourMemory.position;
                    } else if (colour2 == 0) {
                        colour2 = holder2.colourMemory.colorID;
                        position2 = holder2.colourMemory.position;
                        if (colour1 == colour2) {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    holder1.colourMemory.colorID = holder2.colourMemory.colorID = 0;
                                    colourPosMap.put(position1, holder1.colourMemory);
                                    colourPosMap.put(position2, holder2.colourMemory);
                                    score += 2;
                                    flag++;

                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                    resetValues();
                                    ((Activity) context).runOnUiThread(new Runnable() {
                                        public void run() {
                                            scoreView.setText(Integer.toString(score));
                                            notifyDataSetChanged();
                                            if(flag == 8)
                                            {

                                                playerNameDialogBuilder.setOnShowListener(new DialogInterface.OnShowListener() {

                                                    @Override
                                                    public void onShow(DialogInterface dialog) {

                                                        Submit = playerNameDialogBuilder.getButton(AlertDialog.BUTTON_POSITIVE);
                                                        Cancel = playerNameDialogBuilder.getButton(AlertDialog.BUTTON_NEGATIVE);
                                                        Submit.setOnClickListener(new View.OnClickListener() {

                                                            @Override
                                                            public void onClick(View view) {
                                                                if (playerNameInput.getText().toString().trim().length() == 0) {
                                                                    playerNameInput.setError("Name Cannot be Blank.");

                                                                } else {
                                                                    insertScore();
                                                                    playerNameDialogBuilder.cancel();
                                                                    finish();
                                                                    startActivity(getIntent());
                                                                }

                                                            }
                                                        });

                                                        Cancel.setOnClickListener(new View.OnClickListener() {

                                                            @Override
                                                            public void onClick(View view) {
                                                                playerNameDialogBuilder.cancel();
                                                                finish();
                                                                startActivity(getIntent());

                                                            }
                                                        });
                                                    }
                                                });
                                                playerNameDialogBuilder.show();
                                            }
                                        }
                                    });
                                }
                            };
                            thread.start();
                        } else {
                            Thread thread = new Thread() {
                                @Override
                                public void run() {
                                    try {
                                        Thread.sleep(500);
                                        ((Activity) context).runOnUiThread(new Runnable() {
                                            public void run() {

                                                if (holder1 != null && holder2 != null) {
                                                    score--;
                                                    holder1.colourFlipImage.flip(false);
                                                    holder2.colourFlipImage.flip(false);
                                                    scoreView.setText(Integer.toString(score));
                                                    resetValues();

                                                }
                                            }
                                        });

                                    } catch (InterruptedException e) {
                                        e.printStackTrace();
                                    }
                                }
                            };
                            thread.start();
                        }
                    }
                }
            }
        };

        public void resetValues() {
            colour1 = colour2 = position1 = position2 = 0;
            holder1 = holder2 = null;
        }

        public void insertScore()
        {
            new Thread(new Runnable() {
                public void run() {
                    Realm realm = Realm.getDefaultInstance();
                    realm.executeTransaction(new Realm.Transaction() {
                        @Override
                        public void execute(Realm realm) {
                            Player player = realm.createObject(Player.class);
                            player.setPlayerName(playerNameInput.getText().toString().trim());
                            player.setPlayerScore(score);
                        }
                    });
                }
            }).start();
        }

    }
}


