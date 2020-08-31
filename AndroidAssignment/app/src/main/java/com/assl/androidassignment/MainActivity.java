package com.assl.androidassignment;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.assl.androidassignment.helpers.Helper;
import com.assl.androidassignment.repository.internet.UserDetails;
import com.assl.androidassignment.view.UserDetailsAdapter;
import com.assl.androidassignment.viewmodel.MainActivityViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TextView refreshText;
    private ImageView refreshIcon;
    private RecyclerView recyclerView;
    private MainActivityViewModel mainActivityViewModel;
    private Helper helper;
    private UserDetailsAdapter userDetailsAdapter;
    private ArrayList<UserDetails> userDetailsList;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
        handleNoInternetConnection();
    }

    private void fetchDataFromInternet() {
        LiveData<ArrayList<UserDetails>> usersList = mainActivityViewModel.getRecyclerViewData();
        usersList.observe(this, new Observer<ArrayList<UserDetails>>() {
            @Override
            public void onChanged(ArrayList<UserDetails> userDetails) {
                progressBar.setVisibility(View.GONE);
                if(userDetails!=null) {
                    userDetailsList.clear();
                    userDetailsList.addAll(userDetails);
                    userDetailsAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
                    handleNoInternetConnection();
                }
            }
        });
    }

    private void init() {
        mainActivityViewModel = new ViewModelProvider(this).get(MainActivityViewModel.class);
        helper = new Helper();
        userDetailsList = new ArrayList<>();
        userDetailsAdapter = new UserDetailsAdapter(this, userDetailsList);
        refreshIcon = findViewById(R.id.refresh_icon);
        refreshText = findViewById(R.id.refresh_text);
        recyclerView = findViewById(R.id.recycler_view);
        progressBar = findViewById(R.id.progress_bar);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(userDetailsAdapter);
    }

    private void handleNoInternetConnection() {
        if(helper.checkInternet()){
            recyclerView.setVisibility(View.VISIBLE);
            refreshIcon.setVisibility(View.GONE);
            refreshText.setVisibility(View.GONE);
            fetchDataFromInternet();
        } else {
            progressBar.setVisibility(View.GONE);
            recyclerView.setVisibility(View.GONE);
            refreshIcon.setVisibility(View.VISIBLE);
            refreshText.setVisibility(View.VISIBLE);
            Toast.makeText(MainActivity.this, "No internet connection", Toast.LENGTH_LONG).show();
            refreshIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    progressBar.setVisibility(View.VISIBLE);
                    handleNoInternetConnection();
                }
            });
        }

    }
}