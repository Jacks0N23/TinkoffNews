package ru.andrroider.apps.tinkoffnews.newsList.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import ru.andrroider.apps.tinkoffnews.databinding.FeedHolderBinding;
import ru.andrroider.apps.tinkoffnews.newsList.model.dto.Payload;
import ru.andrroider.apps.tinkoffnews.newsList.view.NewsListRouter;

/**
 * Created by Jackson on 21/11/2017.
 */

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.FeedViewHolder> {
    private List<Payload> mList = new ArrayList<>();
    private NewsListRouter mRouter;

    public NewsListAdapter(NewsListRouter mRouter) {
        this.mRouter = mRouter;
    }

    public void setList(List<Payload> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }

    @Override
    public FeedViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //а на котлине это одна строка (с экстеншенами)………
        return new FeedViewHolder(FeedHolderBinding.inflate(LayoutInflater.from(parent.getContext()),
                parent, false));
    }

    @Override
    public void onBindViewHolder(FeedViewHolder holder, int position) {
        holder.binding.date.setText(mList.get(position).getHumanReadableDate());
        holder.binding.title.setText(mList.get(position).getText());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    class FeedViewHolder extends RecyclerView.ViewHolder {
        FeedHolderBinding binding;

        FeedViewHolder(FeedHolderBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

            binding.card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mRouter.loadArticle(mList.get(getAdapterPosition()).getId());
                }
            });
        }
    }
}