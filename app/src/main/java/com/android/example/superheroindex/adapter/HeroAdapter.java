package com.android.example.superheroindex.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.superheroindex.R;
import com.android.example.superheroindex.model.Hero;
import com.android.example.superheroindex.model.Human;
import com.android.example.superheroindex.model.PowerIndex;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import static com.android.example.superheroindex.MainActivity.flag;

public class HeroAdapter extends RecyclerView.Adapter<HeroAdapter.HeroViewHolder> {

    ArrayList<Hero> mHero;

    final private ListItemClickListener mListener;

    public interface ListItemClickListener{
        void onListItemClicked(int clickedItemIndex);
    }

    public HeroAdapter(ArrayList<Hero> heroes, ListItemClickListener listener) {
        mHero = heroes;
        mListener = listener;
    }

    @NonNull
    @NotNull
    @Override
    public HeroViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.hero_list,parent,false);

        return new HeroViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull @NotNull HeroAdapter.HeroViewHolder holder, int position) {
        holder.bind(mHero,position,holder.itemView.getContext());
    }

    @Override
    public int getItemCount() {
        return mHero.size();
    }

    class HeroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView humanName;
        TextView heroName;
        TextView strengthValue;
        TextView intelligenceValue;
        TextView speedValue;
        ImageView heroImage;

        public HeroViewHolder(@NonNull @org.jetbrains.annotations.NotNull View itemView) {
            super(itemView);
            humanName = itemView.findViewById(R.id.humanNameTextView);
            heroName = itemView.findViewById(R.id.heroNameTextView);
            strengthValue = itemView.findViewById(R.id.strengthValueTextView);
            intelligenceValue = itemView.findViewById(R.id.intelligenceValueTextView);
            speedValue = itemView.findViewById(R.id.speedValueTextView);
            heroImage = itemView.findViewById(R.id.heroImageView);
            itemView.setOnClickListener(this);

        }

        public void bind(ArrayList<Hero> mHero, int index, Context context)  {

            Hero hero = mHero.get(index);

            Human human = hero.getHumanForm();

            PowerIndex powerIndex = hero.getPowerIndexHero();

            if(!(human.getHumanName() == null)) {
                humanName.setText(human.getHumanName());
            }else
            {
                humanName.setText("-");
            }

            if(!(hero.getHeroName() == null)) {
                heroName.setText(hero.getHeroName());            }else
            {
                heroName.setText("-");
            }

            if(!(powerIndex.getStrength().equals("null"))) {
                int strength = Integer.parseInt(powerIndex.getStrength());
                strengthValue.setText(powerIndex.getStrength());
                strengthValue.setBackgroundColor(context.getResources().getColor(colorPicker(strength)));
            }else
            {
                strengthValue.setText("-");

            }



            if(!(powerIndex.getIntelligence().equals("null"))) {
                int intelligence = Integer.parseInt(powerIndex.getIntelligence());
                intelligenceValue.setText(powerIndex.getIntelligence());
                intelligenceValue.setBackgroundColor(context.getResources().getColor(colorPicker(intelligence)));
            }else
            {
                intelligenceValue.setText("-");

            }


            if(!(powerIndex.getSpeed().equals("null"))){
                int speed = Integer.parseInt(powerIndex.getSpeed());
                speedValue.setText(powerIndex.getSpeed());
                speedValue.setBackgroundColor(context.getResources().getColor(colorPicker(speed)));
            }else
            {
                speedValue.setText("-");

            }



            if(!(mHero.get(index).heroImage.getUrl() == null)) {
                Uri uri = Uri.parse(mHero.get(index).heroImage.getUrl());

                Picasso.get().load(uri).placeholder(R.drawable.hero_generic).into(heroImage);
            }else
            {
                heroImage.setImageResource(R.drawable.hero_generic);
            }

        }

        public int colorPicker(int value){
            if(value >= 90){
                return R.color.red;
            }else if(value >= 80){
                return R.color.orange;
            }else{
                return R.color.yellow;
            }
        }

        @Override
        public void onClick(View v) {
            int clickedItemIndex = getAdapterPosition();
            mListener.onListItemClicked(clickedItemIndex);
            Log.i("we are here","onClickHeroAdapter" + String.valueOf(flag));
        }
    }


}
