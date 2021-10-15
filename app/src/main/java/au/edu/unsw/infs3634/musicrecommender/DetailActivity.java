package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {
    private ImageView imgAlbum;
    private ArrayList<Music> music;
    private TextView txtDetailName;
    private TextView txtDetailArtist;
    private TextView txtDetailAbout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent incomingIntent = getIntent();
        String song = incomingIntent.getStringExtra("Song");

        setImages(song);

        music = Music.getMusic();
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailArtist = findViewById(R.id.txtDetailArtist);
        txtDetailAbout = findViewById(R.id.txtDetailAbout);


        txtDetailName.setText(findSong(song,1));
        txtDetailArtist.setText(findSong(song,2));
        txtDetailAbout.setText(findSong(song,3));


    }


    public void setImages(String name) {
        imgAlbum = findViewById(R.id.imgDetail);
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

    public String findSong(String song, int key) {
        //loop through arraylist, find country code
        ArrayList<Music> musicList = Music.getMusic();
        for (Music music: musicList) {
            if(music.getName().equals(song)) {
                //return country name
                if (key == 1) {
                    return music.getName();
                }
                if (key == 2) {
                    return music.getArtist();
                }

                if (key == 3) {
                    return music.getDescription();
                }

                if (key == 4) {
                    return music.getGenre();
                }

            }
        }

        return "not found";
    }
}