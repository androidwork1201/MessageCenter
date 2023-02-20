package com.example.messagecenter.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.messagecenter.databinding.LayoutItemBinding;
import com.example.messagecenter.network.contentpage.ContentListData;
import com.example.messagecenter.value.ValuePage;

import java.util.ArrayList;
import java.util.Objects;

public class ContentListAdapter extends ListAdapter<ContentListData.DataDTO, ContentListAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> userId;
    private ArrayList<String> unread;
    private ArrayList<String> userName;
    private ArrayList<String> userUid;


    public ContentListAdapter(
            Context context,
            ArrayList<String> userId,
            ArrayList<String> unread,
            ArrayList<String> userName,
            ArrayList<String> userUid
    ) {
        super(Diff_callback);
        this.context = context;
        this.userId = userId;
        this.unread = unread;
        this.userName = userName;
        this.userUid = userUid;

    }

    public static final DiffUtil.ItemCallback<ContentListData.DataDTO> Diff_callback = new DiffUtil.ItemCallback<ContentListData.DataDTO>() {
        @Override
        public boolean areItemsTheSame(@NonNull ContentListData.DataDTO oldItem, @NonNull ContentListData.DataDTO newItem) {
            return true;
        }

        @Override
        public boolean areContentsTheSame(@NonNull ContentListData.DataDTO oldItem, @NonNull ContentListData.DataDTO newItem) {
            return Objects.equals(oldItem.getUserId(), newItem.getUserId()) &&
                    Objects.equals(oldItem.getName(), newItem.getName()) &&
                    Objects.equals(oldItem.getUid(), newItem.getUid()) &&
                    Objects.equals(oldItem.getUnread(), newItem.getUnread());
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutItemBinding.inflate(LayoutInflater.from(context), parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String id = userId.get(position);
        String userUnread = unread.get(position);
        String name = userName.get(position);
        String uid = userUid.get(position);

        holder.binding.userId.setText(id);
        holder.binding.unreadNumber.setText(userUnread);
        holder.binding.userName.setText(name);
        if (!Objects.equals(userUnread, "0")) {
            holder.binding.isRead.setVisibility(View.VISIBLE);
            holder.binding.unreadNumber.setVisibility(View.VISIBLE);
            holder.binding.userUId.setText(uid);
        }
        holder.itemView.setOnClickListener(v -> {
            v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,
                    Uri.parse("https://talkmate.page.link/ga8iJedJ38deNwPn8")));
        });
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        LayoutItemBinding binding;

        public ViewHolder(@NonNull LayoutItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;


        }
    }

}
