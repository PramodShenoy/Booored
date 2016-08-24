package yorozuyastudios.pro.com.booored;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button yoda, movie, jokes, cats;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        yoda = (Button) findViewById(R.id.yoda_button);
        yoda.setOnClickListener(this);
        yoda.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = getResources().getString(R.string.yoda);
                initiatePopupWindow(str);
                return true;
            }
        });

        movie = (Button) findViewById(R.id.movie_quiz_button);
        movie.setOnClickListener(this);
        movie.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = getResources().getString(R.string.movie_quiz);
                initiatePopupWindow(str);
                return true;
            }
        });

        jokes = (Button) findViewById(R.id.jokes_button);
        jokes.setOnClickListener(this);
        jokes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = getResources().getString(R.string.jokes);
                initiatePopupWindow(str);
                return true;
            }
        });

        cats = (Button) findViewById(R.id.cat_pics);
        cats.setOnClickListener(this);
        cats.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = getResources().getString(R.string.cats);
                initiatePopupWindow(str);
                return true;
            }
        });
    }

    public void initiatePopupWindow(String str) {
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.popup_layout);
        tv = (TextView) dialog.findViewById(R.id.info);
        tv.setText(str);
        Toast.makeText(this, str, Toast.LENGTH_LONG).show();
        dialog.setTitle("Custom Alert Dialog");

        Button btnCancel = (Button) dialog.findViewById(R.id.cancel);
        dialog.show();

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.yoda_button:
                Intent i = new Intent(MainActivity.this, YodaActivity.class);
                startActivity(i);
                break;
            case R.id.jokes_button:
                Intent i1 = new Intent(MainActivity.this, JokesActivity.class);
                startActivity(i1);
                break;
        }

    }


}
