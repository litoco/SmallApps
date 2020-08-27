package com.assl.appassessment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;


public class AllUsersListFragment extends Fragment {

    private MainActivityViewModel mainActivityViewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        return inflater.inflate(R.layout.fragment_all_users_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        RecyclerView allUsers = view.findViewById(R.id.all_users);
        final ArrayList<UserDetails> userDetailsArrayList = new ArrayList<>();
        final AllUsersRecyclerView allUsersRecyclerView = new AllUsersRecyclerView(requireContext(), userDetailsArrayList);
        allUsers.setLayoutManager(new LinearLayoutManager(requireContext()));
        allUsers.setAdapter(allUsersRecyclerView);
        mainActivityViewModel.getAllUsers().observe(getViewLifecycleOwner(), new Observer<List<UserDetails>>() {
            @Override
            public void onChanged(List<UserDetails> userDetails) {
                userDetailsArrayList.clear();
                userDetailsArrayList.addAll(userDetails);
                allUsersRecyclerView.notifyDataSetChanged();
            }
        });
    }
}