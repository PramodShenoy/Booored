package yorozuyastudios.pro.com.booored;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

public class JokesActivity extends AppCompatActivity{
    Button btn;
    TextView tv;
    private static final String QUERY="http://tambal.azurewebsites.net/joke/random";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.jokes_activity);
        btn = (Button) findViewById(R.id.create_joke_button);
        tv = (TextView) findViewById(R.id.joke);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JokesAsyncTask jokesAsyncTask=new JokesAsyncTask();
                jokesAsyncTask.execute(QUERY);
            }
        });
    }

    private class JokesAsyncTask extends AsyncTask<String,Void,String>
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
                return extractJoke(jsonresp);
            }
            else return "nanana";
        }

        @Override
        protected void onPostExecute(String str)
        {
            tv.setText(str);
        }
    }

    private static String extractJoke(String jsonResp)
    {
        String joke="";
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResp);
            joke=baseJsonResponse.optString("joke");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return joke;
    }

}




