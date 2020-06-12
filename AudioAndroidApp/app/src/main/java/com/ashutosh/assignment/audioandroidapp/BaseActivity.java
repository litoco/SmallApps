package com.ashutosh.assignment.audioandroidapp;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;

public class BaseActivity extends AppCompatActivity {

    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;
    private ArrayList<Fragment> fragmentsList = new ArrayList<>();

    private ArrayList<CarouselItem> carouselItemsList = new ArrayList<>();
    private BaseViewModel baseViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);

        fragmentsList.add(new HomeFragment());
        fragmentsList.add(new AudioFragment());
        viewPager = findViewById(R.id.view_pager);
        pagerAdapter = new PagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, fragmentsList);
        viewPager.setAdapter(pagerAdapter);

        baseViewModel = new ViewModelProvider(this).get(BaseViewModel.class);
        if(baseViewModel.getCarouselItemsList()==null) {
            for (int i = 0; i < 10; i++) {
                ArrayList<SingleItem> singleItemArrayList = new ArrayList<>();
                for (int j = 0; j < 20; j++) {
                    singleItemArrayList.add(new SingleItem(R.drawable.soothing));
                }
                carouselItemsList.add(new CarouselItem("TitleNo: " + (i + 1), singleItemArrayList));
            }
            baseViewModel.setCarouselItemsList(carouselItemsList);
        }else
            carouselItemsList = baseViewModel.getCarouselItemsList();
    }

    public ArrayList<CarouselItem> getCarouselItemsList() {
        return carouselItemsList;
    }

    public BaseViewModel getBaseViewModel() {
        return baseViewModel;
    }

    public void setHorizontalScrollFor(int itemNumber, int position) {
        carouselItemsList.get(itemNumber).setCurrentHorizontalScroll(position);
    }

    public ViewPager getViewPager() {
        return viewPager;
    }

    public PagerAdapter getPagerAdapter() {
        return pagerAdapter;
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(pagerAdapter!=null) {
            pagerAdapter.setCount(baseViewModel.getCount());
            pagerAdapter.notifyDataSetChanged();
            viewPager.setCurrentItem(baseViewModel.getCount()-1);
        }
    }
}
