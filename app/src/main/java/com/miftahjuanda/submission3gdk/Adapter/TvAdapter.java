package com.miftahjuanda.submission3gdk.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.miftahjuanda.submission3gdk.Model.TvData;
import com.miftahjuanda.submission3gdk.Network.Constant;
import com.miftahjuanda.submission3gdk.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.MovieViewHolder> {

    private List<TvData> tvDatas;
    private Context context;

    private OnTvItemSelectedListener onTvItemSelectedListener;

    public TvAdapter(Context context) {
        this.context = context;
        tvDatas = new ArrayList<>();
    }

    private void add(TvData item) {
        tvDatas.add(item);
        notifyItemInserted(tvDatas.size() - 1);
    }

    public void addAll(List<TvData> movieDatas) {
        for (TvData movieData : movieDatas) {
            add(movieData);
        }
    }

    private void remove(TvData item) {
        int position = tvDatas.indexOf(item);
        if (position > -1) {
            tvDatas.remove(position);
            notifyItemRemoved(position);
        }
    }

    public void clear() {
        while (getItemCount() > 0) {
            remove(getItem(0));
        }
    }

    public TvData getItem(int position) {
        return tvDatas.get(position);
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tvshow, parent, false);
        final MovieViewHolder movieViewHolder = new MovieViewHolder(view);
        movieViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int adapterPos = movieViewHolder.getAdapterPosition();
                TvData tvData = tvDatas.get(adapterPos);
                if (onTvItemSelectedListener != null) {
                    onTvItemSelectedListener.onItemClick(movieViewHolder.itemView,tvData,adapterPos);
                }
            }
        });

        return movieViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        final TvData tvData = tvDatas.get(position);
        holder.bind(tvData);
    }

    @Override
    public int getItemCount() {
        return tvDatas.size();
    }

    public void setOnTvItemSelectedListener(OnTvItemSelectedListener onTvItemSelectedListener) {
        this.onTvItemSelectedListener = onTvItemSelectedListener;
    }

    public interface OnTvItemSelectedListener {
        void onItemClick(View v, TvData tvData, int position);
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {
        private ImageView posterImage;
        private TextView posterTitle,
                posterDescription;
        private RatingBar posterRating;

        private MovieViewHolder(View itemView) {
            super(itemView);

            posterImage = itemView.findViewById(R.id.posterImage);
            posterTitle = itemView.findViewById(R.id.posterTitle);
            posterRating = itemView.findViewById(R.id.rating);
            posterDescription = itemView.findViewById(R.id.posterDescription);
        }

        private void bind(TvData tvData) {
            Picasso.get()
                    .load(Constant.IMG_URL + tvData.getPoster_path())
                    .error(R.drawable.ic_broken_image_black_24dp)
                    .into(posterImage);
            posterTitle.setText(tvData.getName());
            posterRating.setRating((float) (tvData.getVote_average() / 2));
            posterDescription.setText(tvData.getOverview());

        }
    }
}
