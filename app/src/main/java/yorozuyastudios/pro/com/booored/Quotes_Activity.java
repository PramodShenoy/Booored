package yorozuyastudios.pro.com.booored;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class Quotes_Activity extends AppCompatActivity {


    Button btn;
    TextView tv, tv1;
    private static final String QUERY = "http://api.forismatic.com/api/1.0/?method=getQuote&lang=en&format=json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quotes_activity);
        btn = (Button) findViewById(R.id.random_quote_button);
        tv = (TextView) findViewById(R.id.quote);
        tv1 = (TextView) findViewById(R.id.author);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QuotesAsyncTask quotesAsyncTask = new QuotesAsyncTask();
                quotesAsyncTask.execute(QUERY);
            }
        });
    }

    private class QuotesAsyncTask extends AsyncTask<String, Void, Quote> {
        @Override
        protected Quote doInBackground(String... urls) {

            if (urls.length < 1 || urls[0] == null) {
                return null;
            }
            String jsonresp = QueryUtils.getData(urls[0]);
            if (jsonresp != null) {
                return extractQuote(jsonresp);
            } else return null;
        }

        @Override
        protected void onPostExecute(Quote str) {
            if(str!=null) {
                String quote = str.getQuote();
                String author = str.getAuthor();
                tv.setText(quote);
                tv1.setText(author);
            }
            else
            Toast.makeText(Quotes_Activity.this,"CANNOT CONNECT",Toast.LENGTH_LONG).show();
        }
    }

    private static Quote extractQuote(String jsonResp) {
        String quote = "";
        String author = "";
        if (TextUtils.isEmpty(jsonResp)) {
            return null;
        }

        try {
            JSONObject baseJsonResponse = new JSONObject(jsonResp);
            quote = baseJsonResponse.optString("quoteText");
            author = baseJsonResponse.optString("quoteAuthor");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Quote quote1 = new Quote(quote, author);
        return quote1;
    }

}