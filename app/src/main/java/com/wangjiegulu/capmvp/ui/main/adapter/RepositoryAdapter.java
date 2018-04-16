package com.wangjiegulu.capmvp.ui.main.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wangjiegulu.capmvp.R;
import com.wangjiegulu.capmvp.ui.main.GithubRepositoryMainVO;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: wangjie
 * Email: tiantian.china.2@gmail.com
 * Date: 27/03/2018.
 */
public class RepositoryAdapter extends RecyclerView.Adapter<RepositoryAdapter.RepositoryNormalViewHolder> {
    public interface OnRepositoryAdapterItemClickListener {
        void onRepositoryAdapterItemClick(GithubRepositoryMainVO githubRepositoryMainVO, int position);
    }

    private List<GithubRepositoryMainVO> list = new ArrayList<>();

    private OnRepositoryAdapterItemClickListener onRepositoryAdapterItemClickListener;

    public void setOnRepositoryAdapterItemClickListener(OnRepositoryAdapterItemClickListener onRepositoryAdapterItemClickListener) {
        this.onRepositoryAdapterItemClickListener = onRepositoryAdapterItemClickListener;
    }

    public void setList(List<GithubRepositoryMainVO> list) {
        this.list = null == list ? new ArrayList<>() : list;
    }

    @NonNull
    @Override
    public RepositoryNormalViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_main_repository, parent, false);
        final RepositoryNormalViewHolder viewHolder = new RepositoryNormalViewHolder(view);
        view.setOnClickListener(v -> {
            if (null != onRepositoryAdapterItemClickListener) {
                int position = viewHolder.getAdapterPosition();
                onRepositoryAdapterItemClickListener.onRepositoryAdapterItemClick(list.get(position), position);
            }
        });
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RepositoryNormalViewHolder holder, int position) {
        holder.onBindViewHolder(list.get(position), position);
    }

    static class RepositoryNormalViewHolder extends RecyclerView.ViewHolder {
        private TextView titleTv;
        private TextView stargazersCountTv;

        public RepositoryNormalViewHolder(View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.item_main_repository_title_tv);
            stargazersCountTv = itemView.findViewById(R.id.item_main_repository_stargazers_count_tv);

        }

        public void onBindViewHolder(GithubRepositoryMainVO githubRepository, int position) {
            stargazersCountTv.setText(githubRepository.getReadableStargazersCount());
            titleTv.setText(githubRepository.getName());
        }


    }

}
