package co.touchlab.materialdemo;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;


public class PaletteActivity extends ActionBarActivity
{

    private static final String TAG = PaletteActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_palette);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_palette, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        int id = item.getItemId();

        switch(id)
        {
            case R.id.action_palette:
                doPaletteStuff();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void doPaletteStuff()
    {
        new AsyncTask<Void, Palette, Void>()
        {
            int count = 0;
            @Override
            protected Void doInBackground(Void... params)
            {
                Bitmap bitmap;
                Palette palette;

                Log.d(TAG, "bear time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.bear);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                Log.d(TAG, "birds time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.birds);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                Log.d(TAG, "lion time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.lion);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                Log.d(TAG, "piggie time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.piggie);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                Log.d(TAG, "sloth time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.sloth);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                Log.d(TAG, "tiger time");
                bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.tiger);
                palette = Palette.generate(bitmap);
                publishProgress(palette);

                return null;
            }

            @Override
            protected void onProgressUpdate(Palette... values)
            {
                super.onProgressUpdate(values);

                View view;
                switch(count)
                {
                    case 0:
                        view = findViewById(R.id.bear);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                    case 1:
                        view = findViewById(R.id.birds);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                    case 2:
                        view = findViewById(R.id.lion);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                    case 3:
                        view = findViewById(R.id.piggie);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                    case 4:
                        view = findViewById(R.id.sloth);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                    case 5:
                        view = findViewById(R.id.tiger);
                        view.setBackgroundColor(values[0].getVibrantColor(Color.GRAY));
                        break;
                }

                count++;
            }
        }.execute();

    }
}
