package au.edu.unsw.infs3634.musicrecommender;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    private ArrayList<Music> music;
    private RecyclerViewClickListener listener;
    private ArrayList<Music> musicFiltered;

    public RecyclerAdapter(ArrayList<Music> music, RecyclerViewClickListener listener) {
        this.music = music;
        this.listener = listener;
        this.musicFiltered = music;
    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_items, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.ViewHolder holder, int position) {
        String name = music.get(position).getName();
        String artist = music.get(position).getArtist();
        String genre = music.get(position).getGenre();
        int rating = music.get(position).getRating();
        String description = music.get(position).getDescription();

        holder.setData(name,artist,genre);
        holder.setImages(name);
    }

    @Override
    public int getItemCount() {
        return music.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
            TextView txtName;
            TextView txtArtist;
            TextView txtGenre;
            ImageView imgAlbum;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtName = itemView.findViewById(R.id.txtName);
            txtArtist = itemView.findViewById(R.id.txtArtist);
            txtGenre = itemView.findViewById(R.id.txtGenre);
            //set onclick listener in view
            itemView.setOnClickListener(this);
        }

        public void setData(String name, String artist, String genre) {
            txtName.setText(name);
            txtArtist.setText(artist);
            txtGenre.setText(genre);

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

        @Override
        public void onClick(View view) {
            //might need to change this for filterable
            listener.onClick(view, getAbsoluteAdapterPosition());
        }

    }

    public interface RecyclerViewClickListener {
        void onClick(View v, int position);
    }
}
