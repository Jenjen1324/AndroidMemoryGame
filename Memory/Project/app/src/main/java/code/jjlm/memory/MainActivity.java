package code.jjlm.memory;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        Button btn_sp = (Button) findViewById(R.id.button_sp);
        Button btn_mp = (Button) findViewById(R.id.button_mp);
        Button btn_sets = (Button) findViewById(R.id.button_sets);

        final MainActivity activity = this;

        btn_sp.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
                startActivity(intent);
            }
        });

    }
}
