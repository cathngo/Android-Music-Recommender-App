package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.media.Rating;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    private ImageView imgAlbum;
    private ArrayList<Music> music;
    private TextView txtDetailName;
    private TextView txtDetailArtist;
    private TextView txtDetailAbout;
    private RatingBar rating;
    private ImageView imgPlay;
    private ImageView imgHeart;
    boolean favourite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        //receive the song name passed from main activity
        Intent incomingIntent = getIntent();
        String song = incomingIntent.getStringExtra("Song");
        setImages(song);

        //Find the ids for the UI elements
        txtDetailName = findViewById(R.id.txtDetailName);
        txtDetailArtist = findViewById(R.id.txtDetailArtist);
        txtDetailAbout = findViewById(R.id.txtDetailAbout);
        rating = findViewById(R.id.dRating);
        imgPlay = findViewById(R.id.imgPlay);
        imgHeart = findViewById(R.id.imgHeart);

        //set the name, artist, rating and short description for each song on the UI
        txtDetailName.setText(findSong(song,1));
        txtDetailArtist.setText(findSong(song,2));
        txtDetailAbout.setText(findSong(song,3));
        rating.setRating(findRating(song));


        //Set onclick listener for the play button to play the song's youtube mv
        imgPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (song.equals("WITHOUT YOU")) {
                    gotoUrl("https://www.youtube.com/watch?v=SJOKlqJho8U&ab_channel=TheKidLAROI.");
                }
                else if(song.equals("Photograph")) {
                    gotoUrl("https://www.youtube.com/watch?v=nSDgHBxUbVQ&ab_channel=EdSheeran");
                }
                else if(song.equals("Heather")) {
                    gotoUrl("https://www.youtube.com/watch?v=24u3NoPvgMw&ab_channel=ConanGrayVEVO");
                }
                else if(song.equals("Trip")) {
                    gotoUrl("https://www.youtube.com/watch?v=W16bk86xIY0&ab_channel=EllaMaiVEVO");
                }
                else if(song.equals("Sativa")) {
                    gotoUrl("https://www.youtube.com/watch?v=5x-d3pabd5o&ab_channel=JheneAikoVEVO");
                }
                else if(song.equals("Instagram")) {
                    gotoUrl("https://www.youtube.com/watch?v=wKyMIrBClYw&ab_channel=DEANTRBL");
                }
                else if(song.equals("Bye bye my blue")) {
                    gotoUrl("https://www.youtube.com/watch?v=WbhK3wMXluE&ab_channel=JYPEntertainment");
                }
                else if(song.equals("Wonderland")) {
                    gotoUrl("https://www.youtube.com/watch?v=3WlOZTy072k&ab_channel=iri");
                }
                else if(song.equals("Lights")) {
                    gotoUrl("https://www.youtube.com/watch?v=Jtu4t6ZNuqU&ab_channel=EnriqueNoquiero");
                }
                else if(song.equals("Dear Name")) {
                    gotoUrl("https://www.youtube.com/watch?v=JSOBF_WhqEM&ab_channel=IU-Topic");
                }
            }
        });
    }

    //Reflect changes in user favourited songs onResume
    @Override
    public void onResume()
    {
        super.onResume();
        Intent incomingIntent = getIntent();
        String song = incomingIntent.getStringExtra("Song");

        //Check if the user has favourited the song
        favourite = getFavourite(song);
        //Set the favourite icon to the pink heart if it has been favourited
        if (favourite == true) {
            imgHeart.setImageResource(R.drawable.thickheart);
        } else {
            //Set the favourite icon to the green heart if it has not been favourited
            imgHeart.setImageResource(R.drawable.greenheart);
        }

        //Set on click listener to detect if user has favourited song or not
        imgHeart.setOnClickListener((new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //if not favourited, set icon to default
                if (favourite == false){
                    imgHeart.setImageResource(R.drawable.thickheart);
                    //else mark the song as favourited
                    Toast.makeText(DetailActivity.this, "song added to favourites", Toast.LENGTH_SHORT).show();
                } else {
                    imgHeart.setImageResource(R.drawable.greenheart);
                    Toast.makeText(DetailActivity.this, "song removed from favourites", Toast.LENGTH_SHORT).show();
                }

                //search music list for song name and mark as favourite
                getIndex(song);
            }
        }));
    }

    //Set the image to the album cover of the selected song
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

    //Method to return the name, artist, description, genre for a given song
    public String findSong(String song, int key) {
        //loops through musiclist and find song
        ArrayList<Music> musicList = Music.getMusic();
        for (Music music: musicList) {
            if(music.getName().equals(song)) {
                //returns information based on key
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

    //Returns the rating of the song
    public int findRating(String song) {
        //loop through arraylist, find song
        ArrayList<Music> musicList = Music.getMusic();
        for (Music music: musicList) {
            if(music.getName().equals(song)) {
                //return information
                return music.getRating();
            }
        }
        return -1;
    }

    //Returns whether the song was favourited or not
    public boolean getFavourite(String song) {
        //loop through arraylist, find song
        ArrayList<Music> musicList = Music.getMusic();
        for (Music music: musicList) {
            if(music.getName().equals(song)) {
                //return information
                return music.getFav();
            }
        }
        return false;

    }

    //updates the value of favourite for a given song
    public void getIndex(String itemName)
    {
        for (int i = 0; i < Music.musicList.size(); i++)
        {
            Music song = Music.musicList.get(i);
            if (itemName.equals(song.getName()))
            {
                //Get the original data
                String name = song.getName();
                int rate = song.getRating();
                String artist = song.getArtist();
                String desc = song.getDescription();
                String genre = song.getGenre();
                boolean fav = song.getFav();
                boolean newFav;

                //Change the value of favourite based on user input
                if (fav == false) {
                    newFav = true;
                } else {
                    newFav = false;
                }
                //update the music list to reflect new changes
                Music.musicList.set(i, new Music(name,artist,genre,rate,desc,newFav));
            }
        }
    }

    //onClick method for play button, goes to the youtube url provided
    public void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }
}