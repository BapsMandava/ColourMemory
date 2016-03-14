package com.example.bapaiah.colourmemory.ViewHolders;

import android.view.View;

import com.example.bapaiah.colourmemory.Entities.ColourMemory;
import com.example.bapaiah.colourmemory.R;

import eu.davidea.flipview.FlipView;

/**
 * Created by bapai_000 on 3/8/2016.
 */
public class ColourViewHolder
{
    public ColourMemory colourMemory;
    public FlipView colourFlipImage;

    public ColourViewHolder(View v)
    {
        colourFlipImage =(FlipView) v.findViewById(R.id.flipimageView);

    }
}