package au.edu.unsw.infs3634.musicrecommender;

import java.util.ArrayList;
import java.util.Comparator;

public class Music {
    public static ArrayList<Music> musicList = new ArrayList<>();
    public static int load = 0;
    private String name;
    private String artist;
    private String genre;
    private boolean fav;
    private int rating;

    public Music(String name, String artist, String genre, int rating, String description, boolean fav) {
        this.name = name;
        this.artist = artist;
        this.genre = genre;
        this.rating = rating;
        this.description = description;
        this.fav = fav;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getFav() {
        return fav;
    }

    public void setFav(boolean fav) {
        this.fav = fav;
    }

    private String description;


    public static ArrayList<Music> getMusic() {
        if (load == 0) {
            musicList.add(new Music("Heather", "Conan Gray", "Pop", 5, "Heather is a song by American singer-songwriter Conan Gray released through Republic Records. Originally released as a track on Gray's debut studio album Kid Krow in March 2020, it was released as the sixth single in September 2020 after becoming a sleeper hit.", false));
            musicList.add(new Music("Instagram", "DEAN", "Pop", 5, "DEAN released his single “instagram.” The soft and smooth track combines R&B with soul and was written, composed, and produced by DEAN (listed in the credits under the moniker Deanfluenza). DEAN based the lyrics of “instagram” on his own personal experiences with depression and feeling as if he wasn’t as cool as the people he saw on social media.", false));
            musicList.add(new Music("Photograph", "Ed Sheeran", "Pop", 4, "Photograph is a song by English singer-songwriter Ed Sheeran from his second studio album, × (2014). With visually descriptive lyrics, it discusses a long-distance relationship inspired by Sheeran's own experience of being away from his then-girlfriend while he was on tour.", false));
            musicList.add(new Music("Dear Name", "IU", "K-pop", 4, "Dear Name” follows a story of someone who once lost hope in a painful heartbreak, but she eventually is able to keep her faith and hold onto a hope she keeps deep down in her heart. And that is why she put this song as the last track; it concludes her journey of growing up with a hint of hope for the love in the future.", false));
            musicList.add(new Music("WITHOUT YOU", "The Kid LAROI", "Pop", 3, "In this song, The Kid Laroi raps about a relationship where his girlfriend has had enough and walked out on him. He communicates his feelings of heartbreak and despair to his former partner.", false));
            musicList.add(new Music("Trip", "Ella Mai", "R&B", 3, "\"Trip\" is a song by English-American singer Ella Mai from her eponymous debut studio album. The song peaked at number 47 in the UK and number 11 on the Billboard Hot 100 in the United States.", false));
            musicList.add(new Music("Lights", "warbear", "J-pop", 3, "Ozaki Yuuki (尾崎雄貴), also known as warbear, is a singer-songwriter, the vocalist & guitarist of BBHF, and the former frontman of Galileo Galilei.", false));
            musicList.add(new Music("Sativa", "Jhene Aiko", "R&B", 2, "On “Sativa,” she dictates the pace, controlling the narrative and her lover. Ever the slick scene-setter, Jhené Aiko has mastered lyrical head trips, and on “Sativa,” she pulls you into the haze.", false));
            musicList.add(new Music("Bye bye my blue", "Yerin Baek", "K-pop", 2, "A heart of a girl who’s constantly in love, however always holding back onto the many unexpressed feelings is confessed beautifully by Yerin. Although there are hurtful memories, wanting to be the better one to comfort the one she loves, her heart that goes back and forth on her feelings towards the one she longs for.", false));
            musicList.add(new Music("Wonderland", "iri", "J-Pop", 2, "iri is a Japanese pop and R&B singer songwriter under the Victor Entertainment sub-label Colourful Records. In 2014, iri won JAM, an audition organized by fashion magazine NYLON JAPAN and Sony Music Japan. She made her major debut with her first album Groove it, released on October 26, 2016.", false));
            load = 1;
        }
        return musicList;
    }

    public static Comparator<Music> ascendingComparator = new Comparator<Music>() {

        @Override
        public int compare(Music music, Music t1) {
            return music.getRating() - t1.getRating();
        }
    };

    public static Comparator<Music> descendingComparator = new Comparator<Music>() {

        @Override
        public int compare(Music music, Music t1) {
            return t1.getRating() - music.getRating();
        }
    };


}
