package com.philotv.startwarscollection.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.philotv.startwarscollection.ui.SWCharacterDetailActivity;
import com.philotv.startwarscollection.ui.SWCharacterDetailFragment;
import com.philotv.startwarscollection.ui.SWCharacterMainActivity;
import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.data.SWCharacter;

import java.util.List;

public  class SWCharacterClientAdapter
        extends RecyclerView.Adapter<SWCharacterClientAdapter.ViewHolder> {

    private final SWCharacterMainActivity mParentActivity;
    private final List<SWCharacter> mValues;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SWCharacter item = (SWCharacter) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(SWCharacterDetailFragment.ARG_ITEM_ID, item.id);
                SWCharacterDetailFragment fragment = new SWCharacterDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, SWCharacterDetailActivity.class);
                intent.putExtra(SWCharacterDetailFragment.ARG_ITEM_ID, item.id);

                context.startActivity(intent);
            }
        }
    };

    public SWCharacterClientAdapter(SWCharacterMainActivity parent,
                                    List<SWCharacter> items,
                                    boolean twoPane) {
        mValues = items;
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_list_content, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mIdView.setText(mValues.get(position).id);
        holder.mContentView.setText(mValues.get(position).content);

        holder.itemView.setTag(mValues.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView mIdView;
        final TextView mContentView;

        ViewHolder(View view) {
            super(view);
            mIdView = (TextView) view.findViewById(R.id.id_text);
            mContentView = (TextView) view.findViewById(R.id.content);
        }
    }
}