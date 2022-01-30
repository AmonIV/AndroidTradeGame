package com.example.tradegame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;

public class SailingActivity extends AppCompatActivity {

    //<global>
    static public String GlobalCurrentTownName;
    static public double InventoryCoin;
    static public int InventoryFood;
    static public int InventoryWood;
    static public int InventoryMetal;
    static public int InventoryHerb;
    //</global>

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sailing);
        //<Setup Global Variables>
        Bundle extras = getIntent().getExtras();
        GlobalCurrentTownName = extras.getString("CurrentTownName");
        InventoryCoin = extras.getDouble("InventoryCoin");
        InventoryFood = extras.getInt("InventoryFood");
        InventoryWood = extras.getInt("InventoryWood");
        InventoryMetal = extras.getInt("InventoryMetal");
        InventoryHerb = extras.getInt("InventoryHerb");
        //</Setup Global Variables>
        PlaySailingSequence();
    }

    public void PlaySailingSequence()
    {
        ImageView Boat = findViewById(R.id.BoatImage);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;
        ObjectAnimator animator = ObjectAnimator.ofFloat(Boat,"translationX",width);
        animator.setDuration(5000);

        animator.start();
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Button LandButton = findViewById(R.id.LandButton);
                LandButton.setVisibility(View.VISIBLE);
                LandButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        SwitchToHarbor();
                    }
                });
            }
        }, 5000);

    }

    public void SwitchToHarbor()
    {
        Intent SwitchToHarbor = new Intent(SailingActivity.this,MainActivity.class);
        SwitchToHarbor.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToHarbor.putExtra("InventoryCoin", InventoryCoin);
        SwitchToHarbor.putExtra("InventoryFood", InventoryFood);
        SwitchToHarbor.putExtra("InventoryWood", InventoryWood);
        SwitchToHarbor.putExtra("InventoryMetal", InventoryMetal);
        SwitchToHarbor.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToHarbor);
        finish();
    }
}