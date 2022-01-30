package com.example.tradegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class BattleActivity extends AppCompatActivity {


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
        setContentView(R.layout.activity_battle);

        //<Setup Global Variables>
        Bundle extras = getIntent().getExtras();
        GlobalCurrentTownName = extras.getString("CurrentTownName");
        InventoryCoin = extras.getDouble("InventoryCoin");
        InventoryFood = extras.getInt("InventoryFood");
        InventoryWood = extras.getInt("InventoryWood");
        InventoryMetal = extras.getInt("InventoryMetal");
        InventoryHerb = extras.getInt("InventoryHerb");
        //</Setup Global Variables>
        ImageView LeftEnemyShip = findViewById(R.id.leftEnemyShipImage);
        ImageView RightEnemyShip = findViewById(R.id.rightEnemyShipImage);
        ImageView PlayerShip = findViewById(R.id.playerShipImage);
        Button LeftFireButton = findViewById(R.id.leftFireButton);
        Button LeftDodgeButton = findViewById(R.id.leftDodgeButton);
        Button RightFireButton = findViewById(R.id.rightFireButton);
        Button RightDodgeButton = findViewById(R.id.rightDodgeButton);
        TextView WonScreenText = findViewById(R.id.wonScreenText);
        Button WonScreenButton = findViewById(R.id.wonBattleButton);
        TextView LostScreenText = findViewById(R.id.lostScreenText);
        Button LostScreenButton = findViewById(R.id.lostBattleButton);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int ScreenWidth = displayMetrics.widthPixels;

        WonScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToHarbor();
            }
        });

        LostScreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RestartGame();
            }
        });


        GameLoop gameLoop = new GameLoop(ScreenWidth,LeftEnemyShip,RightEnemyShip,PlayerShip,LeftFireButton,LeftDodgeButton,RightFireButton,RightDodgeButton,WonScreenText,WonScreenButton,LostScreenText,LostScreenButton);


    }

    public void SwitchToHarbor()
    {
        Intent SwitchToHarbor = new Intent(BattleActivity.this,MainActivity.class);
        SwitchToHarbor.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToHarbor.putExtra("InventoryCoin", InventoryCoin);
        SwitchToHarbor.putExtra("InventoryFood", InventoryFood);
        SwitchToHarbor.putExtra("InventoryWood", InventoryWood);
        SwitchToHarbor.putExtra("InventoryMetal", InventoryMetal);
        SwitchToHarbor.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToHarbor);
        finish();
    }

    public void RestartGame()
    {
        finish();
    }

}