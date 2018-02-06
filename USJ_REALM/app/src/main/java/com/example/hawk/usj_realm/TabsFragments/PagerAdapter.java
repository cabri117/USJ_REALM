package com.example.hawk.usj_realm.TabsFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;

/**
 * Created by usuario on 5/2/18.
 */

public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<String> data;

    public PagerAdapter(FragmentManager fm, int NumOfTabs, ArrayList<String> data) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                PersonFragment tabPerson = new PersonFragment();
                return tabPerson;
            case 1:
                TipsFragment tabTips = new TipsFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("TIPSDATA", data);
                tabTips.setArguments(bundle);
                return tabTips;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }}
