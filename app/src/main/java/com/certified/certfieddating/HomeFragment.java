package com.certified.certfieddating;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.certified.certfieddating.model.User;
import com.certified.certfieddating.util.Users;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private RecyclerView mRecyclerView;
    private ArrayList<User> mMatches = new ArrayList<>();
    private StaggeredGridLayoutManager mStaggeredGridLayoutManager;
    private MainRecyclerViewAdapter mMainRecyclerViewAdapter;
    private static final int mSpanCount = 2;

    public HomeFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        Log.d(TAG, "onCreateView: started");
        mRecyclerView = view.findViewById(R.id.recycler_view);

        findMatches();
        return view;
    }

    private void findMatches() {
        Users users = new Users();
        if (mMatches != null) {
            mMatches.clear();
        }
        for (User user : users.USERS) {
            mMatches.add(user);
        }
        if (mMainRecyclerViewAdapter == null) {
            initRecyclerView();
        }
    }

    private void initRecyclerView() {
        Log.d(TAG, "initRecyclerView: init recycler view.");
        mStaggeredGridLayoutManager = new StaggeredGridLayoutManager(mSpanCount, LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(mStaggeredGridLayoutManager);
        mMainRecyclerViewAdapter = new MainRecyclerViewAdapter(getActivity(), mMatches);
        mRecyclerView.setAdapter(mMainRecyclerViewAdapter);
    }


}