package code.jjlm.memory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

import code.jjlm.memory.game.Card;
import code.jjlm.memory.game.Game;
import code.jjlm.memory.game.Set;

/**
 * Created by Jens on 21.06.2016.
 */
public class GridViewAdapter extends BaseAdapter {

    private ArrayList<ImageView> images;

    public GridViewAdapter(Context context, int imageWidth, Game game) {
        final Game g = game;
        images = new ArrayList<ImageView>();
        int image = R.drawable.back;

        for(final Game.DeckCard c : game.getDeckCards()) {
            ImageView img = new ImageView(context);
            img.setImageResource(image);
            img.setLayoutParams(new GridView.LayoutParams(imageWidth,imageWidth));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
            images.add(img);
            c.setView(img);
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    g.openCard(c);
                }
            });
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return images.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return images.get(position);
    }
}
