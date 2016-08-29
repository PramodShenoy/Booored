package yorozuyastudios.pro.com.booored;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener {

    private Button yoda, movie, jokes, cats, quotes;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkConnection();
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

        quotes = (Button) findViewById(R.id.quotes_button);
        quotes.setOnClickListener(this);
        quotes.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                String str = getResources().getString(R.string.quotes);
                initiatePopupWindow(str);
                return true;
            }
        });

    }
    private void checkConnection() {
        boolean isConnected = ConnectivityReceiver.isConnected();
        showSnack(isConnected);
    }

    // Showing the status in Snackbar
    private void showSnack(boolean isConnected) {
        String message;
        int color;
        if (isConnected) {
            message = "Good! Connected to Internet";
            color = Color.WHITE;
        } else {
            message = "Sorry! Not connected to internet";
            color = Color.RED;
        }

        Toast.makeText(this,message,Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register connection status listener
        MyApplication.getInstance().setConnectivityListener(this);
    }

    /**
     * Callback will be triggered when there is change in
     * network connection
     */
    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        showSnack(isConnected);
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
            case R.id.quotes_button:
                Intent i3 = new Intent(MainActivity.this, Quotes_Activity.class);
                startActivity(i3);
                break;
            case R.id.cat_pics:
                Intent i4=new Intent(MainActivity.this,CatsActivity.class);
                startActivity(i4);
                break;

        }

    }


}
