package com.philotv.startwarscollection.adapter;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.philotv.startwarscollection.data.SWGender;
import com.philotv.startwarscollection.ui.SWCharacterDetailActivity;
import com.philotv.startwarscollection.ui.SWCharacterDetailFragment;
import com.philotv.startwarscollection.ui.SWMainActivity;
import com.philotv.startwarscollection.R;
import com.philotv.startwarscollection.data.SWCharacter;
import com.philotv.startwarscollection.ui.SWMainPresenter;

import java.util.ArrayList;
import java.util.List;


public class SWClientAdapter
        extends RecyclerView.Adapter<SWClientAdapter.ViewHolder> {

    ViewHolder holder;

    private static final int DURATION = 500;
    private boolean on_attach = true;


    private final SWMainActivity mParentActivity;
    private final ArrayList<SWCharacter> swCharacterList;
    private final boolean mTwoPane;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            SWCharacter swCharacter = (SWCharacter) view.getTag();
            if (mTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putString(SWCharacterDetailFragment.HOME_PLANET_URL, swCharacter.homeworld);
                SWCharacterDetailFragment fragment = new SWCharacterDetailFragment();
                fragment.setArguments(arguments);
                mParentActivity.getSupportFragmentManager().beginTransaction()
                        .replace(R.id.item_detail_container, fragment)
                        .commit();
            } else {
                Context context = view.getContext();
                Intent intent = new Intent(context, SWCharacterDetailActivity.class);
                intent.putExtra(SWCharacterDetailFragment.HOME_PLANET_URL, swCharacter.homeworld);
                context.startActivity(intent);
            }
        }
    };
    private String TAG = SWClientAdapter.class.getSimpleName();

    public SWClientAdapter(SWMainActivity parent,
                           List<SWCharacter> swCharactersList,
                           boolean twoPane) {

        swCharacterList = new ArrayList<>(swCharactersList);
        mParentActivity = parent;
        mTwoPane = twoPane;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.character_recycler_list_content, parent, false);
        return new ViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        this.holder = holder;
        SWCharacter swCharacter = swCharacterList.get(position);

        switch (SWGender.valueOf(swCharacter.gender.replace("/", "").toUpperCase())) {
            case MALE:
                holder.layout.setBackground(mParentActivity.getResources().getDrawable(R.drawable.sw_male_character_background));
                break;
            case FEMALE:
                holder.layout.setBackground(mParentActivity.getResources().getDrawable(R.drawable.sw_female_character_background));
                break;
            case NA:
                holder.layout.setBackground(mParentActivity.getResources().getDrawable(R.drawable.sw_na_character_color));
                break;
            default:
                holder.layout.setBackground(mParentActivity.getResources().getDrawable(R.drawable.sw_male_character_background));
                break;
        }
        holder.itemView.setTag(swCharacterList.get(position));
        holder.itemView.setOnClickListener(mOnClickListener);

        holder.nameView.setText(swCharacterList.get(position).name);
        holder.heightView.setText("Height : " + swCharacterList.get(position).height);
        holder.massView.setText("Mass : " + swCharacterList.get(position).mass);
        holder.birthyearView.setText("Born : " + swCharacterList.get(position).birthYear);

        setAnimation(holder.itemView, position);

        String buildUrl = "https://www.superheroapi.com/api.php/10218899145883795/search/" + swCharacterList.get(position).name;

        ((SWMainPresenter) mParentActivity.getPresenter()).getCharacterImage(swCharacterList.get(position).name, this);

    }

    public void temp(String imageUrl) {
        if (holder != null)
            Glide.with(holder.image.getContext()).load(imageUrl).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return swCharacterList.size();
    }

    public ArrayList<? extends Parcelable> getSwCharacterList() {
        return swCharacterList;
    }


    class ViewHolder extends RecyclerView.ViewHolder {
        final ConstraintLayout layout;
        final TextView nameView;
        final ImageView image;
        final TextView birthyearView;
        final TextView heightView;
        final TextView massView;

        ViewHolder(View view) {
            super(view);
            layout = view.findViewById(R.id.sw_character_parent_view);
            nameView = view.findViewById(R.id.name);
            image = view.findViewById(R.id.image);
            birthyearView = view.findViewById(R.id.birth_year);
            heightView = view.findViewById(R.id.height);
            massView = view.findViewById(R.id.mass);
        }
    }

    private void setAnimation(View itemView, int i) {
        if (!on_attach) {
            i = -1;
        }
        boolean isNotFirstItem = i == -1;
        i++;
        itemView.setAlpha(0.f);
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator animator = ObjectAnimator.ofFloat(itemView, "alpha", 0.f, 0.5f, 1.0f);
        ObjectAnimator.ofFloat(itemView, "alpha", 0.f).start();
        animator.setStartDelay(isNotFirstItem ? DURATION / 2 : (i * DURATION / 3));
        animator.setDuration(DURATION);
        animatorSet.play(animator);
        animator.start();
    }

}