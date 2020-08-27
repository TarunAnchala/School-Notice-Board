package com.school.noticeboard;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.school.noticeboard.Data.ContentMgmtViewModel;
import com.school.noticeboard.Data.Notice;
import com.school.noticeboard.adapter.VerticalAdapter;
import com.school.noticeboard.utils.InjectManager;
import com.school.noticeboard.utils.Utils;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements InjectManager.InjectedEventNotifier {
    private static final String TAG = "MainActivity";
    private ArrayList<Notice> listOfNotices = new ArrayList<>();
    private ContentMgmtViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.notificationsList);
        viewModel = ViewModelProviders.of(this).get(ContentMgmtViewModel.class);
        viewModel.getListOfNotification().observe(this, new Observer<ArrayList<Notice>>() {
            @Override
            public void onChanged(ArrayList<Notice> notices) {
                Log.e(TAG, "onChanged: called ");
                listOfNotices.clear();
                listOfNotices.addAll(notices);
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));
        VerticalAdapter verticalAdapter = new VerticalAdapter();
        verticalAdapter.setData(listOfNotices);
        recyclerView.setAdapter(verticalAdapter);
    }


    @Override
    protected void onStart() {
        super.onStart();
        InjectManager.getInstance().addListener(InjectManager.LAUNCH_DETAIL_SCREEN, this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        InjectManager.getInstance().removeListener(InjectManager.LAUNCH_DETAIL_SCREEN, this);
    }

    @Override
    public void onReceiveEvent(int eventType, Object object) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        int position = (int) object;
        Bundle bundle = new Bundle();
        bundle.putInt(Utils.POSITION, position);
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        DetailScreen detailScreen = new DetailScreen();
        detailScreen.setArguments(bundle);
        fragmentTransaction.replace(R.id.container, detailScreen, DetailScreen.TAG);
        fragmentTransaction.addToBackStack(DetailScreen.TAG);
        fragmentTransaction.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        viewModel.getListOfNotification().removeObservers(this);
    }
}