package com.wp.stmik.uptkomp.madesubmission_1_aplikasi_movie_catalogue;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class MovieAdapter extends BaseAdapter {

    private ArrayList<MovieItems> movieData = new ArrayList<>();
    private LayoutInflater mInflater;
    private Context context;
    private String final_overview;

    public MovieAdapter(Context context) {
        this.context    = context;
        mInflater       = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void setData(ArrayList<MovieItems> items){
        movieData = items;
        notifyDataSetChanged();
    }

    public void addItem(final MovieItems item) {
        movieData.add(item);
        notifyDataSetChanged();
    }

    public void clearData(){
        movieData.clear();
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getCount() {
        if (movieData == null) return 0;
        return movieData.size();
    }

    @Override
    public MovieItems getItem(int position) {
        return movieData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {
            holder                  = new ViewHolder();
            convertView             = mInflater.inflate(R.layout.item_movies, null);
            holder.tvTitle        = (TextView)convertView.findViewById(R.id.tvTitle);
            holder.tvOverview       = (TextView)convertView.findViewById(R.id.tvOverview);
            holder.tvReleaseDate    = (TextView)convertView.findViewById(R.id.tvDate);
            holder.imgPoster        = (ImageView)convertView.findViewById(R.id.imgMoviePict);
            convertView.setTag(holder);
        }else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.tvTitle.setText(movieData.get(position).getMov_title());

        String overview = movieData.get(position).getMov_synopsis();

        if(TextUtils.isEmpty(overview)){
            final_overview = "No data";
        }else{
            final_overview = overview;
        }
        holder.tvOverview.setText(final_overview);

        String retrievedDate = movieData.get(position).getMov_date();
        SimpleDateFormat date_format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = date_format.parse(retrievedDate);

            SimpleDateFormat new_date_format = new SimpleDateFormat("EEEE, MMM dd, yyyy");
            String date_of_release = new_date_format.format(date);
            holder.tvReleaseDate.setText(date_of_release);

        } catch (ParseException e) {
            e.printStackTrace();
        }
        Picasso.with(context).load("http://image.tmdb.org/t/p/w154/"+movieData.get(position).getMov_image()).placeholder(context.getResources().getDrawable(R.drawable.ic_image_black_50dp)).error(context.getResources().getDrawable(R.drawable.ic_image_black_50dp)).into(holder.imgPoster);

        return convertView;
    }

    private static class ViewHolder {
        TextView tvTitle;
        TextView tvOverview;
        TextView tvReleaseDate;
        ImageView imgPoster;
    }
}
