package com.example.hawk.usj_realm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import com.example.hawk.usj_realm.TabsFragments.PagerAdapter;
import com.example.hawk.usj_realm.TabsFragments.PersonFragment;
import com.example.hawk.usj_realm.TabsFragments.TipsFragment;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.LinearLayout;

import java.util.ArrayList;

import io.realm.Realm;

public class MainActivity extends Connect {

    public static final String TAG = MainActivity.class.getName();
    private LinearLayout rootLayout = null;
    ArrayList<String> tipsData = new ArrayList<String>();

    private Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().show();

        tipsData = (ArrayList<String>) getIntent().getSerializableExtra(SplashActivity.TIPSDATA);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Persons"));
        tabLayout.addTab(tabLayout.newTab().setText("Tips"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewPager = (ViewPager) findViewById(R.id.pager);
        PagerAdapter adapter = new PagerAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), tipsData);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.addPerson) {
            Intent intent = new Intent(this, AddPersonActivity.class);
            // intent.putExtra("connection",(Serializable) realm);
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

}
