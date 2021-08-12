package com.android.example.superheroindex;

import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.example.superheroindex.model.Hero;
import com.android.example.superheroindex.model.MyPojo;
import com.android.example.superheroindex.util.ObjectWrapperForBinder;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;

import static com.android.example.superheroindex.MainActivity.flag;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FightFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FightFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FightFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment fight_fragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FightFragment newInstance(Hero selectedHero) {
        FightFragment fragment = new FightFragment();
        Bundle args = new Bundle();
        args.putBinder("my_bundle", new ObjectWrapperForBinder(selectedHero));
        //args.putBundle("my_bundle",args);
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fight, container, false);
    }

    @Override
    public void onViewCreated(@NonNull @NotNull View view, @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Hero selectedHero = null;
        if (getArguments() != null) {
            selectedHero = ((ObjectWrapperForBinder) getArguments().getBinder("my_bundle")).getData();
            //getArguments().getBundle("my_bundle");
            //mParam1 = getArguments().getString(ARG_PARAM1);
            //mParam2 = getArguments().getString(ARG_PARAM2);
            System.out.println(selectedHero.getHeroName() + "we in fragment");
        } else {
            return;
        }

        TextView humanName;
        TextView heroName;
        TextView heroRace;
        TextView aliases;
        TextView strengthValue;
        TextView intelligenceValue;
        TextView speedValue;
        TextView durabilityValue;
        TextView combatValue;
        TextView strengthText;
        TextView intelligenceText;
        TextView speedText;
        TextView durabilityText;
        TextView combatText;
        TextView firstAppearanceText;
        ImageView heroImage;
        TextView affiliations;

        heroName = view.findViewById(R.id.heroNameTextViewFragment);
        if (!(selectedHero.getHeroName() == null)) {
            heroName.setText(selectedHero.getHeroName());
        } else {
            heroName.setText("-");
        }

        humanName = view.findViewById(R.id.nameTextViewFragment);

        if (!(selectedHero.humanForm.getHumanName() == null)) {
            humanName.setText(selectedHero.humanForm.getHumanName());
        } else {
            humanName.setText("-");
        }

        heroImage = view.findViewById(R.id.HeroImageViewFragment);

        if (!(selectedHero.heroImage.getUrl() == null)) {
            Uri uri = Uri.parse(selectedHero.heroImage.getUrl());

            Picasso.get().load(uri).placeholder(R.drawable.hero_generic).into(heroImage);
        } else {
            heroImage.setImageResource(R.drawable.hero_generic);
        }

        heroRace = view.findViewById(R.id.raceTextView);

        if (selectedHero.lifeForm.getRace() != null) {
            if (selectedHero.lifeForm.getRace().equals("null")) {
                heroRace.setText("Unknown Species");
            } else {
                heroRace.setText(selectedHero.lifeForm.getRace());
            }
        }

        aliases = view.findViewById(R.id.aliasesValueTextView);
        if (selectedHero.getHumanForm().aliases != null) {
            if (selectedHero.getHumanForm().aliases.size() == 1) {
                aliases.append(" " + selectedHero.getHumanForm().aliases.get(0));
            } else {
                for (String str : selectedHero.getHumanForm().aliases) {
                    aliases.append(" " + str + ",");
                }
            }
        } else {
            aliases.setText("-");
        }

        firstAppearanceText = view.findViewById(R.id.appearanceValueTextView);
        if (selectedHero.getHumanForm().first_appearance != null) {
            firstAppearanceText.append(" " + selectedHero.getHumanForm().first_appearance);
        } else {
            firstAppearanceText.setText("-");
        }

        affiliations = view.findViewById(R.id.groupsValueTextView);
        if (selectedHero.getConnections().getGroup_affiliation() != null) {
            affiliations.setText(" " + selectedHero.getConnections().getGroup_affiliation());
        } else {
            affiliations.setText("-");
        }

        LinearLayout mStrengthLayout = view.findViewById(R.id.strengthValue);
        LinearLayout mIntelligenceLayout = view.findViewById(R.id.intelligenceValue);
        LinearLayout mSpeedLayout = view.findViewById(R.id.speedValue);
        LinearLayout mCombatLayout = view.findViewById(R.id.combatValue);
        LinearLayout mDurabilityLayout = view.findViewById(R.id.durabilityValue);

        int strengthIndex = 0;
        int intelligenceIndex = 0;
        int speedIndex = 0;
        int combatIndex = 0;
        int durabilityIndex = 0;

        if(!(selectedHero.getPowerIndexHero().getStrength().equals("null"))){
            strengthIndex = Integer.parseInt(selectedHero.getPowerIndexHero().getStrength());
        }

        if(!(selectedHero.getPowerIndexHero().getIntelligence().equals("null"))){
            intelligenceIndex = Integer.parseInt(selectedHero.getPowerIndexHero().getIntelligence());
        }

        if(!(selectedHero.getPowerIndexHero().getSpeed().equals("null"))){
            speedIndex = Integer.parseInt(selectedHero.getPowerIndexHero().getSpeed());
        }

        if(!(selectedHero.getPowerIndexHero().getDurability().equals("null"))){
            durabilityIndex = Integer.parseInt(selectedHero.getPowerIndexHero().getDurability());
        }

        if(!(selectedHero.getPowerIndexHero().getCombat().equals("null"))){
            combatIndex = Integer.parseInt(selectedHero.getPowerIndexHero().getCombat());
        }

        setColor(mStrengthLayout,strengthIndex);
        setColor(mIntelligenceLayout,intelligenceIndex);
        setColor(mSpeedLayout,speedIndex);
        setColor(mCombatLayout,combatIndex);
        setColor(mDurabilityLayout,durabilityIndex);


    }

    public void setColor(ViewGroup viewGroup, int index) {
        LinearLayout myLayout = (LinearLayout) viewGroup;
        int count = myLayout.getChildCount();
        TextView v = null;

        //for(int i=0; i<count; i++) {
        if (index > 90) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_40));
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_60));
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_80));
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.red_for_val_90));
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.red));
            v = null;
            return;
        }
        if (index > 80) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_40));
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_60));
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_80));
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.red_for_val_90));
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            return;
        }
        if (index > 60) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_40));
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_60));
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_80));
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            return;
        }
        if (index > 40) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_40));
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.orange_for_val_60));
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            return;
        }
        if (index > 20) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_40));
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            return;
        }
        if (index > 0) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(getResources().getColor(R.color.yellow_for_val_20));
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setVisibility(View.INVISIBLE);//
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            return;
        }
        if (index == 0) {
            v = (TextView) myLayout.getChildAt(0);
            assert v != null;
            v.setBackgroundColor(0x00000000);
            v.setTypeface(null, Typeface.BOLD);
            v.setTextSize(16);
            v.setText("-");
            v = null;
            v = (TextView) myLayout.getChildAt(1);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(2);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(3);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(4);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;
            v = (TextView) myLayout.getChildAt(5);
            assert v != null;
            v.setVisibility(View.INVISIBLE);
            v = null;


        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        flag = true;
        Log.i("FragmentOnDestroyView","I was called");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        Log.i("FragmentOnDetach","I was called");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("FragmentOnDestroy","I was called");
    }
}

