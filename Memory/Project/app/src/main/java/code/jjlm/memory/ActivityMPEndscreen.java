package code.jjlm.memory;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

/**
 * Created by Jens on 22.06.2016.
 */
public class ActivityMPEndscreen extends AppCompatActivity {

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mp_endscreen);

        TextView view = (TextView) findViewById(R.id.text_mp_pwin);

        int winner = getIntent().getIntExtra("winner", 0);
        if(winner == 0) {
            view.setText(getResources().getText(R.string.player1_win));
            view.setTextColor(getResources().getColor(R.color.player2_active));
        } else if(winner == 1) {
            view.setText(getResources().getText(R.string.player2_win));
            view.setTextColor(getResources().getColor(R.color.player1_active));
        }

    }

}
