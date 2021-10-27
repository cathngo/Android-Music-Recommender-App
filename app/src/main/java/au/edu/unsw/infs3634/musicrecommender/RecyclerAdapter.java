package au.edu.unsw.infs3634.musicrecommender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements Filterable {
    private ArrayList<Music> music;
    private MusicListener mMusicListener;
    private ArrayList<Music> musicFiltered;
    private List<Music> musicFull;

    public RecyclerAdapter(ArrayList<Music> music, MusicListener listener) {
        this.music = music;
        //this.listener = listener;
        musicFull = new ArrayList<>(music);
        this.mMusicListener = listener;
        this.musicFiltered = music;
    }

    //filter

    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(itemView, mMusicListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String name = music.get(position).getName();
        String artist = music.get(position).getArtist();
        String genre = music.get(position).getGenre();
        int rating = music.get(position).getRating();
        String description = music.get(position).getDescription();
        boolean fav = music.get(position).getFav();


        holder.setData(name,artist,genre, rating, fav);
        holder.setImages(name);
        holder.setRank(name);
    }

    @Override
    public int getItemCount() {
        return music.size();
    }

    public interface MusicListener {
        void onClick(int position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView txtName;
            TextView txtArtist;
            TextView txtGenre;
            ImageView imgAlbum;
            ImageView imgFav;
            RatingBar rating;
            MusicListener musicListener;
            TextView txtRank;



        public ViewHolder(@NonNull View itemView, MusicListener musicListener) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtArtist = itemView.findViewById(R.id.txtArtist);
            txtGenre = itemView.findViewById(R.id.txtGenre);
            rating = itemView.findViewById(R.id.rating);
            txtRank = itemView.findViewById(R.id.txtRank);
            imgFav = itemView.findViewById(R.id.imgFav);
            this.musicListener = musicListener;
            //set onclick listener in view
            itemView.setOnClickListener(this);
        }

        public void setData(String name, String artist, String genre, int rate, boolean fav) {
            txtName.setText(name);
            txtArtist.setText(artist);
            txtGenre.setText(genre);
            rating.setRating(rate);

            //favourite
            if (fav == true) {
                imgFav.setImageResource(R.drawable.thickheart);
            } else {
                imgFav.setVisibility(View.INVISIBLE);
            }


        }

        public void setImages(String name) {
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            if (name.equals("WITHOUT YOU")) {
                imgAlbum.setImageResource(R.drawable.laroi);
            }
            else if(name.equals("Photograph")) {
                imgAlbum.setImageResource(R.drawable.edsheeran);
            }
            else if(name.equals("Heather")) {
                imgAlbum.setImageResource(R.drawable.conangray);
            }
            else if(name.equals("Trip")) {
                imgAlbum.setImageResource(R.drawable.ellamai);
            }
            else if(name.equals("Sativa")) {
                imgAlbum.setImageResource(R.drawable.jheneaiko);
            }
            else if(name.equals("Instagram")) {
                imgAlbum.setImageResource(R.drawable.dean);
            }
            else if(name.equals("Bye bye my blue")) {
                imgAlbum.setImageResource(R.drawable.yerin);
            }
            else if(name.equals("Wonderland")) {
                imgAlbum.setImageResource(R.drawable.iri);
            }
            else if(name.equals("Lights")) {
                imgAlbum.setImageResource(R.drawable.warbear);
            }
            else if(name.equals("Dear Name")) {
                imgAlbum.setImageResource(R.drawable.iu);
            }
        }

        public void setRank(String name) {
            imgAlbum = itemView.findViewById(R.id.imgAlbum);
            if (name.equals("WITHOUT YOU")) {
                txtRank.setText("3.");
            }
            else if(name.equals("Photograph")) {
                txtRank.setText("2.");
            }
            else if(name.equals("Heather")) {
                txtRank.setText("1.");
            }
            else if(name.equals("Trip")) {
                txtRank.setText("3.");
            }
            else if(name.equals("Sativa")) {
                txtRank.setText("4.");
            }
            else if(name.equals("Instagram")) {
                txtRank.setText("1.");
            }
            else if(name.equals("Bye bye my blue")) {
                txtRank.setText("4.");
            }
            else if(name.equals("Wonderland")) {
                txtRank.setText("4.");
            }
            else if(name.equals("Lights")) {
                txtRank.setText("3.");
            }
            else if(name.equals("Dear Name")) {
                txtRank.setText("2.");
            }
        }

        @Override
        public void onClick(View view) {
            //might need to change this for filterable
            musicListener.onClick(getAdapterPosition());
        }

    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }

    @Override
    public Filter getFilter() {
        return exampleFilter;
    }

    public Filter exampleFilter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence charSequence) {
            List<Music> filteredList = new ArrayList<>();

            if (charSequence == null || charSequence.length() == 0) {
                filteredList.addAll(musicFull);
            } else {
                String filterPattern = charSequence.toString().toLowerCase().trim();

                for (Music song : musicFull) {
                    if (song.getName().toLowerCase().contains(filterPattern)) {
                        filteredList.add(song);
                    }
                    if (song.getArtist().toLowerCase().contains(filterPattern)) {
                        filteredList.add(song);
                    }
                }
            }

            FilterResults results = new FilterResults();
            results.values = filteredList;

            return results;
        }

        @Override
        protected void publishResults(CharSequence charSequence, FilterResults results) {
            music.clear();
            music.addAll((List) results.values);
            notifyDataSetChanged();
        }
    };

}


