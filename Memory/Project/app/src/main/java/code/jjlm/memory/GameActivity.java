package code.jjlm.memory;

import android.media.Image;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import code.jjlm.memory.game.Game;
import code.jjlm.memory.game.Set;
import code.jjlm.memory.game.SinglePlayerGame;

public class GameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        int image = R.drawable.doge;
        int cols = 6;
        int padding = 8;
        GridView grid = (GridView) findViewById(R.id.gridview);

        int displayWidth = getResources().getDisplayMetrics().widthPixels - grid.getPaddingLeft() - grid.getPaddingRight();
        int imageWidth = displayWidth / cols - (padding*2);
        grid.setNumColumns(cols);
        grid.setColumnWidth(imageWidth + padding*2);
        grid.setVerticalSpacing(padding*2);
        grid.setStretchMode(GridView.NO_STRETCH);

        GridViewAdapter adapter = new GridViewAdapter(this, imageWidth, new SinglePlayerGame(new Set("", false)));
        grid.setAdapter(adapter);



        //grid.addView(adapter.getView(1,null,null));


        Button btn_exit = (Button) findViewById(R.id.button_exit);
        btn_exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}
