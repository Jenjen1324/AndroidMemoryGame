package code.jjlm.memory;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Jens on 21.06.2016.
 */
public class GridViewAdapter extends BaseAdapter {

    private ArrayList<ImageView> images;
    private Context context;

    public GridViewAdapter(Context context) {
        this.context = context;
        images = new ArrayList<ImageView>();
        int image = R.drawable.doge;

        for (int i = 0; i < 10; i++) {

            ImageView img = new ImageView(context);
            img.setImageResource(image);
            img.setLayoutParams(new GridView.LayoutParams(100,100));
            img.setScaleType(ImageView.ScaleType.CENTER_CROP);
        }
    }

    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return images.get(position);
    }
}
