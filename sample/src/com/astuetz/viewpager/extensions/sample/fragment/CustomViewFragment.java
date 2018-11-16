package com.astuetz.viewpager.extensions.sample.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.canvas_action.BasicBarView;
import com.astuetz.viewpager.extensions.sample.R;

import java.util.ArrayList;

public class CustomViewFragment extends Fragment {
    private BasicBarView basicBarView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_custom_view,container,false);
        initView(rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private void initView(View view){
        basicBarView = view.findViewById(R.id.seekBar);
        ArrayList<Integer> integers = new ArrayList<>();
        integers.add(12);
        integers.add(24);
        integers.add(1);
        integers.add(67);
        basicBarView.setDataList(integers,100);
    }

}
