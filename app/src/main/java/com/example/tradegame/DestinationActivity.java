package com.example.tradegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class DestinationActivity extends AppCompatActivity {


    public static String CurrentChoiceTemp;
    //global
    static public String GlobalCurrentTownName;
    static public double InventoryCoin;
    static public int InventoryFood;
    static public int InventoryWood;
    static public int InventoryMetal;
    static public int InventoryHerb;
    //global

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destination);

        Bundle extras = getIntent().getExtras();
        GlobalCurrentTownName = extras.getString("CurrentTownName");
        InventoryCoin = extras.getDouble("InventoryCoin");
        InventoryFood = extras.getInt("InventoryFood");
        InventoryWood = extras.getInt("InventoryWood");
        InventoryMetal = extras.getInt("InventoryMetal");
        InventoryHerb = extras.getInt("InventoryHerb");

        //<buttons>
        Button WestHarborButton = findViewById(R.id.WestHarborButton);
        Button AlcanlaraButton = findViewById(R.id.AlcanlaraButton);
        Button ChalosButton = findViewById(R.id.ChalosButton);
        Button DamoriaButton = findViewById(R.id.DamoriaButton);
        Button BackToTownButton = findViewById(R.id.BackToTownButton);
        Button SetSailButton = findViewById(R.id.SetSailButton);
        TextView CurrentlyChosenDestinationText = findViewById(R.id.CurrentlyChosenDestinationText);

        WestHarborButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentChoiceTemp = "West Harbor";
                CurrentlyChosenDestinationText.setText("West Harbor");
            }
        });

        AlcanlaraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentChoiceTemp = "Alcanlara";
                CurrentlyChosenDestinationText.setText("Alcanlara");
            }
        });

        ChalosButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentChoiceTemp = "Chalos";
                CurrentlyChosenDestinationText.setText("Chalos");
            }
        });

        DamoriaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CurrentChoiceTemp = "Damoria";
                CurrentlyChosenDestinationText.setText("Damoria");
            }
        });

        BackToTownButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToHarbor();
            }
        });

        SetSailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AmbushDecider();
            }
        });
        //</buttons>

    }

    public void SwitchToHarbor()
    {
        Intent SwitchToHarbor = new Intent(DestinationActivity.this,MainActivity.class);
        SwitchToHarbor.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToHarbor.putExtra("InventoryCoin", InventoryCoin);
        SwitchToHarbor.putExtra("InventoryFood", InventoryFood);
        SwitchToHarbor.putExtra("InventoryWood", InventoryWood);
        SwitchToHarbor.putExtra("InventoryMetal", InventoryMetal);
        SwitchToHarbor.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToHarbor);
        finish();
    }

    public void SwitchToSailing()
    {
        Intent SwitchToSailing = new Intent(DestinationActivity.this,SailingActivity.class);
        GlobalCurrentTownName = CurrentChoiceTemp;
        SwitchToSailing.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToSailing.putExtra("InventoryCoin", InventoryCoin);
        SwitchToSailing.putExtra("InventoryFood", InventoryFood);
        SwitchToSailing.putExtra("InventoryWood", InventoryWood);
        SwitchToSailing.putExtra("InventoryMetal", InventoryMetal);
        SwitchToSailing.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToSailing);
        finish();
    }

    public void SwitchToBattle()
    {
        Intent SwitchToBattle = new Intent(DestinationActivity.this,BattleActivity.class);
        GlobalCurrentTownName = CurrentChoiceTemp;
        SwitchToBattle.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToBattle.putExtra("InventoryCoin", InventoryCoin);
        SwitchToBattle.putExtra("InventoryFood", InventoryFood);
        SwitchToBattle.putExtra("InventoryWood", InventoryWood);
        SwitchToBattle.putExtra("InventoryMetal", InventoryMetal);
        SwitchToBattle.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToBattle);
        finish();
    }

    public void AmbushDecider()
    {
        Random random = new Random();
        int roll = random.nextInt(10);
        if(roll>6)
        {
            SwitchToBattle();
        }
        else
        {
            SwitchToSailing();
        }
    }
}