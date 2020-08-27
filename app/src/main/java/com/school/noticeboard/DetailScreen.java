package com.school.noticeboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.school.noticeboard.Data.ContentMgmtViewModel;
import com.school.noticeboard.Data.Notice;
import com.school.noticeboard.utils.MyApplication;
import com.school.noticeboard.utils.Utils;

public class DetailScreen extends Fragment {

    private TextView title;
    private ImageView logo;
    private TextView time;
    private TextView description;
    private TextView teacherName;
    private TextView subject;
    private TextView teacherContact;
    private String packName;
    public static final String TAG = "DetailScreen";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.detail_screen_layout, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initializeViews(view);
        if (getArguments() != null && getActivity() != null) {
            ContentMgmtViewModel viewModel = ViewModelProviders.of(getActivity()).get(ContentMgmtViewModel.class);
            Notice notice = viewModel.getNoitceObj(getArguments().getInt(Utils.POSITION));
            setData(notice);
        }
    }

    private void initializeViews(View view) {
        title = view.findViewById(R.id.title);
        logo = view.findViewById(R.id.logo);
        time = view.findViewById(R.id.time);
        description = view.findViewById(R.id.description);
        teacherName = view.findViewById(R.id.teacherName);
        subject = view.findViewById(R.id.subjectName);
        teacherContact = view.findViewById(R.id.teacherContact);
        packName = MyApplication.getPackName();
    }

    private void setData(Notice notice) {
        if (notice != null) {
            setLogo(notice.getImage());
            title.setText(notice.getTitle());
            time.setText(notice.getTime());
            description.setText(notice.getDescription());
            teacherName.setText(notice.getTeacher());
            subject.setText(notice.getSubject());
            teacherContact.setText(notice.getTeacher_contact_number());
        }
    }

    private void setLogo(String image) {
        Glide.with(this).load(getResources().getDrawable(getResources().getIdentifier(image, "drawable", packName))).thumbnail(0.2f).into(logo);

    }

}
