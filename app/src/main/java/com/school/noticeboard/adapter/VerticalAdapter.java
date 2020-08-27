package com.school.noticeboard.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.school.noticeboard.Data.Notice;
import com.school.noticeboard.utils.InjectManager;
import com.school.noticeboard.utils.MyApplication;
import com.school.noticeboard.R;

import java.util.ArrayList;

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private ArrayList<Notice> listOfNotice;
    private static final String TAG = "VerticalAdapter";

    public VerticalAdapter() {
        layoutInflater= (LayoutInflater) MyApplication.getAppContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.notification_item_layout,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Notice notice=listOfNotice.get(position);
        holder.getNotificationsTitle().setText(notice.getTitle());
        holder.getNotificationsDateAndTime().setText(notice.getTime());
        holder.itemView.setTag(notice);

    }

    public void setData(ArrayList<Notice> listOfNotice){
        this.listOfNotice=listOfNotice;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return listOfNotice!=null?listOfNotice.size():0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView notificationsTitle;
        private TextView notificationsDateAndTime;
        public ViewHolder(@NonNull final View itemView) {
            super(itemView);
            notificationsTitle =itemView.findViewById(R.id.notificationTitle);
            notificationsDateAndTime =itemView.findViewById(R.id.notificationDateAndTime);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Notice notice= (Notice) itemView.getTag();
                    InjectManager.getInstance().inject(InjectManager.LAUNCH_DETAIL_SCREEN,getAdapterPosition());
                }
            });
        }

        public TextView getNotificationsTitle() {
            return notificationsTitle;
        }

        public TextView getNotificationsDateAndTime() {
            return notificationsDateAndTime;
        }
    }
}
