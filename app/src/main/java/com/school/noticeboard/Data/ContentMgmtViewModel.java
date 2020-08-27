package com.school.noticeboard.Data;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.school.noticeboard.utils.Utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ContentMgmtViewModel extends AndroidViewModel {

    private final String JSON_DATA_FILE = "data.json";
    private static final String TAG = "ContentMgmtViewModel";
    private MutableLiveData<ArrayList<Notice>> notificationsLiveData = new MutableLiveData<>();
    private ArrayList<Notice> listOfUsers;


    public ContentMgmtViewModel(@NonNull Application application) {
        super(application);
        fetchdata();
    }


    private void fetchdata() {
        String jsonFileString = Utils.getJsonFromAssets(JSON_DATA_FILE);
        Gson gson = new Gson();
        Type listUserType = new TypeToken<List<Notice>>() {
        }.getType();
        listOfUsers = gson.fromJson(jsonFileString, listUserType);
        notificationsLiveData.setValue(listOfUsers);
    }


    public MutableLiveData<ArrayList<Notice>> getListOfNotification() {
        return notificationsLiveData;
    }

    public Notice getNoitceObj(int position) {
        return listOfUsers.get(position);
    }
}
