package com.harshaapps.nerdlauncher;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class NerdLauncherFragment extends Fragment {
    private RecyclerView mRecyclerView;
    private ResolveInfo ri;
    List<ResolveInfo> activities;
    public static NerdLauncherFragment newInstance() {
        NerdLauncherFragment fragment = new NerdLauncherFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_nerd_launcher, container, false);
        mRecyclerView=v.findViewById(R.id.recyclerView);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        setupAdapter();
                return v;
    }
    public void setupAdapter()
    {
        Intent i=new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_LAUNCHER);
        PackageManager pm=getActivity().getPackageManager();
        activities=pm.queryIntentActivities(i, 0);
                mRecyclerView.setAdapter(new AppAdapter(activities));
            }
class AppHolder extends RecyclerView.ViewHolder implements View.OnClickListener
{
    private TextView appName;
        private ResolveInfo appInfo;
    public AppHolder(@NonNull View itemView) {
        super(itemView);
                appName=itemView.findViewById(R.id.app);
                appName.setOnClickListener(this);
    }
        public void bind(ResolveInfo appName) {
            PackageManager pm = getActivity().getPackageManager();
            appInfo = appName;
            this.appName.setText(appName.loadLabel(pm).toString());
            this.appName.setBackground(appName.loadIcon(pm));
                    }

    @Override
public void onClick(View v)
{
        Intent i=new Intent(Intent.ACTION_MAIN);
    i.setClassName(appInfo.activityInfo.applicationInfo.packageName, appInfo.activityInfo.name);
    i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
    startActivity(i);
}
}
public class AppAdapter            extends RecyclerView.Adapter<AppHolder>
{
List<ResolveInfo> apps;
    public AppAdapter(List<ResolveInfo> apps) {
        this.apps = apps;
            }
    @NonNull
    @Override
    public AppHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(getActivity());
        View v=inflater.inflate(R.layout.list_item_app, parent, false);
                return new AppHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull AppHolder holder, int position) {
        holder.bind(apps.get(position));
    }

    @Override
    public int getItemCount() {
        return apps.size();
    }
}
    }

