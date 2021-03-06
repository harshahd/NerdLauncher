package com.harshaapps.nerdlauncher;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public abstract class SingleFragmentActivity  extends AppCompatActivity {
    public abstract Fragment createFragment();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentManager fm=getSupportFragmentManager();
        Fragment f=fm.findFragmentById(R.id.fragment_container);
        if(f==null) {
            f = createFragment();
            fm.beginTransaction().add(R.id.fragment_container, f).commit();
        }
    }
}
