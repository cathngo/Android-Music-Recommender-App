package au.edu.unsw.infs3634.musicrecommender;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity implements RecyclerAdapter.MusicListener {
    private ArrayList<Music> music;
    private RecyclerView recyclerView;
    private RecyclerAdapter adapter;
    private LinearLayoutManager layoutManager;
    private ImageView menuIcon;
    private EditText searchText;
    private CharSequence search="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    //Set recycler view, menu and search onResume to reflect any data changes
    @Override
    public void onResume()
    {
        super.onResume();
        setContentView(R.layout.activity_main);

        music = Music.getMusic();
        recyclerView = findViewById(R.id.recyclerView);

        //set layout manager
        layoutManager = new LinearLayoutManager(MainActivity.this);
        layoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);

        //intiialise recycler adapter
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        adapter = new RecyclerAdapter(music, this);
        recyclerView.setAdapter(adapter);

        //Create pop up menu
        menuIcon = findViewById(R.id.menu);
        menuIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showMenu(view);
            }
        });

        //Create search view
        searchText = findViewById(R.id.searchText);
        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            //detect changes in text and call search function
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.getFilter().filter(charSequence);
                search = charSequence;
            }
            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }

    //On click method to switch to detail activity
    @Override
    public void onClick(int position) {
        //pass the song name as an intent
        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("Song", music.get(position).getName());
        startActivity(intent);
    }

    //Create menu for list sorting options
    private void showMenu(View v){
        PopupMenu popupMenu = new PopupMenu(MainActivity.this, v);
        popupMenu.getMenuInflater().inflate(R.menu.menu_main, popupMenu.getMenu());
        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                //sort by lowest to highest rating if user selects this option
                if(menuItem.getItemId() == R.id.mSort) {
                    Collections.sort(music, Music.ascendingComparator);
                    adapter.notifyDataSetChanged();
                    return true;

                }
                //sort by lowest to highest rating if user selects this option
                if (menuItem.getItemId() == R.id.mDescend) {
                    Collections.sort(music, Music.descendingComparator);
                    adapter.notifyDataSetChanged();
                    return true;
                }
                return true;
            }
        });
        popupMenu.show();
    }

}