package com.ashutosh.assignment.audioandroidapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        RecyclerView mainRecyclerView = view.findViewById(R.id.recyclerView);
        MainAdapter mainAdapter = new MainAdapter(requireContext(), ((BaseActivity)requireContext()).getCarouselItemsList());
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(requireContext());
        mainRecyclerView.setLayoutManager(linearLayoutManager);
        mainRecyclerView.setAdapter(mainAdapter);
        linearLayoutManager.scrollToPosition(((BaseActivity)requireContext()).getBaseViewModel().getVerticalScroll());
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)requireContext()).getPagerAdapter().setCount(1);
        ((BaseActivity)requireContext()).getPagerAdapter().notifyDataSetChanged();
    }
}
