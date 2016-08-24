package yorozuyastudios.pro.com.booored;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class YodaActivity extends AppCompatActivity {

    EditText et;
    TextView tv;
    String sentence;
    Button btn;
    private static final String api_key="&mashape-key=2Sicv9iUFYmshWqZiO671OhmOMUEp1nsWgTjsnj7iol7Wesn49";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_yoda);

        et=(EditText) findViewById(R.id.enter_text);
        tv=(TextView) findViewById(R.id.yoda_speak_view);
        btn=(Button) findViewById(R.id.initiate_button);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sentence = et.getText().toString();
                StringBuilder QUERY = new StringBuilder();
                QUERY.append("https://yoda.p.mashape.com/yoda?sentence=");
                sentence.toLowerCase();
                sentence=sentence.replace(' ','+');
                QUERY.append(sentence);
                QUERY.append(api_key);
                YodaAsyncTask yoda = new YodaAsyncTask();
                yoda.execute(QUERY.toString());
            }
        });

    }

    private class YodaAsyncTask extends AsyncTask<String,Void,String>
    {
        @Override
        protected String doInBackground(String...urls)
        {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonresp=QueryUtils.getData(urls[0]);
            if(jsonresp!=null)
            {
                return jsonresp;
            }
            else return "dfs";

        }

        @Override
        protected void onPostExecute(String a)
        {
            tv.setText(a);
        }
    }

}