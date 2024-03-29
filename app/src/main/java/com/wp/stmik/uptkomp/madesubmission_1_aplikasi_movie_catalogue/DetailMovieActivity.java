package com.wp.stmik.uptkomp.madesubmission_1_aplikasi_movie_catalogue;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailMovieActivity extends AppCompatActivity {

    public static String EXTRA_TITLE        = "extra_title";
    public static String EXTRA_OVERVIEW     = "extra_overview";
    public static String EXTRA_RELEASE_DATE = "extra_release_date";
    public static String EXTRA_POSTER_JPG   = "extra_poster_jpg";
    public static String EXTRA_RATE_COUNT   = "extra_rate_count";
    public static String EXTRA_RATE         = "extra_rate";
    public static String EXTRA_LANGUAGE     = "extraLanguage";


    private TextView tvTitle, tvOverview, tvReleaseDate, tvRating, tvLanguage;
    private ImageView imgPoster;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_movie);

        tvTitle = (TextView)findViewById(R.id.detailTitle);
        tvOverview = (TextView)findViewById(R.id.detailOverview);
        tvReleaseDate = (TextView)findViewById(R.id.detailReleaseDate);
        tvRating = (TextView)findViewById(R.id.detailVote);
        tvLanguage = (TextView)findViewById(R.id.detailLanguage);
        imgPoster = (ImageView)findViewById(R.id.imgPoster);

        String title = getIntent().getStringExtra(EXTRA_TITLE);
        String overview = getIntent().getStringExtra(EXTRA_OVERVIEW);
        String rating = getIntent().getStringExtra(EXTRA_RATE);
        String rating_count = getIntent().getStringExtra(EXTRA_RATE_COUNT);
        String original_language = getIntent().getStringExtra(EXTRA_LANGUAGE);
        String poster_jpg = getIntent().getStringExtra(EXTRA_POSTER_JPG);
        String release_date = getIntent().getStringExtra(EXTRA_RELEASE_DATE);
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(release_date);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            tvReleaseDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        tvTitle.setText(title);
        tvOverview.setText(overview);
        tvLanguage.setText(original_language);
        tvRating.setText(rating_count+" Ratings ("+rating+"/10)");
        Picasso.with(context).load("http://image.tmdb.org/t/p/w500/"+poster_jpg).into(imgPoster);
    }
}
