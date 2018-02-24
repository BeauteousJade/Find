package com.example.pby.find.fragment.base;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by pby on 2018/2/1.
 */

public abstract  class BaseFragment extends Fragment {
    private int layoutId = 0;

    public BaseFragment(int layoutId){
        this.layoutId = layoutId;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(layoutId, null);
        init(view);
        return view;
    }
    public abstract  void init(View view);
}
