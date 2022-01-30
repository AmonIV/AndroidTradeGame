package com.example.tradegame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    static public String CurrentTownName = "Starter Cove";
    Town CurrentTown = new Town();
    static public double Coin = 1000;
    static public double CoinTemp = 1000;
    static public int FoodAmountOwned = 0;
    static public int FoodAmountInTown = 100;
    static public int FoodAmountOwnedTemp = 0;
    static public int FoodAmountInTownTemp = 100;
    static public double FoodPrice = 0.50;
    static public double FullFoodTempPrice = 0;
    static public int WoodAmountOwned = 0;
    static public int WoodAmountInTown = 100;
    static public int WoodAmountOwnedTemp = 0;
    static public int WoodAmountInTownTemp = 100;
    static public double WoodPrice = 1.25;
    static public double FullWoodTempPrice = 0;
    static public int MetalAmountOwned = 0;
    static public int MetalAmountInTown = 100;
    static public int MetalAmountOwnedTemp = 0;
    static public int MetalAmountInTownTemp = 100;
    static public double MetalPrice = 1.00;
    static public double FullMetalTempPrice = 0;
    static public int HerbAmountOwned = 0;
    static public int HerbAmountInTown = 100;
    static public int HerbAmountOwnedTemp = 0;
    static public int HerbAmountInTownTemp = 100;
    static public double HerbPrice = 0.75;
    static public double FullHerbTempPrice = 0;
    //<global>
    static public String GlobalCurrentTownName;
    static public double InventoryCoin;
    static public int InventoryFood;
    static public int InventoryWood;
    static public int InventoryMetal;
    static public int InventoryHerb;
    //</global>

    public void BuyFoodTemp()
    {
        if(FoodAmountInTownTemp>0&&CoinTemp>FoodPrice) {
            FoodAmountOwnedTemp++;
            FoodAmountInTownTemp--;
            CoinTemp = CoinTemp - FoodPrice;
            FullFoodTempPrice = FullFoodTempPrice + FoodPrice;
            UpdateFoodWithTemp();
        }
    }

    public void SellFoodTemp()
    {
        if(FoodAmountOwnedTemp>0) {
            FoodAmountInTownTemp++;
            FoodAmountOwnedTemp--;
            CoinTemp = CoinTemp + FoodPrice;
            FullFoodTempPrice = FullFoodTempPrice - FoodPrice;
            UpdateFoodWithTemp();
        }
    }

    public void BuyWoodTemp()
    {
        if(WoodAmountInTownTemp>0&&CoinTemp>WoodPrice) {
            WoodAmountOwnedTemp++;
            WoodAmountInTownTemp--;
            CoinTemp = CoinTemp - WoodPrice;
            FullWoodTempPrice = FullWoodTempPrice + WoodPrice;
            UpdateWoodWithTemp();
        }
    }

    public void SellWoodTemp()
    {
        if(WoodAmountOwnedTemp>0) {
            WoodAmountInTownTemp++;
            WoodAmountOwnedTemp--;
            CoinTemp = CoinTemp + WoodPrice;
            FullWoodTempPrice = FullWoodTempPrice - WoodPrice;
            UpdateWoodWithTemp();
        }
    }

    public void BuyMetalTemp()
    {
        if(MetalAmountInTownTemp>0&&CoinTemp>MetalPrice) {
            MetalAmountOwnedTemp++;
            MetalAmountInTownTemp--;
            CoinTemp = CoinTemp - MetalPrice;
            FullMetalTempPrice = FullMetalTempPrice + MetalPrice;
            UpdateMetalWithTemp();
        }
    }

    public void SellMetalTemp()
    {
        if(MetalAmountOwnedTemp>0) {
            MetalAmountInTownTemp++;
            MetalAmountOwnedTemp--;
            CoinTemp = CoinTemp + MetalPrice;
            FullMetalTempPrice = FullMetalTempPrice - MetalPrice;
            UpdateMetalWithTemp();
        }
    }

    public void BuyHerbTemp()
    {
        if(HerbAmountInTownTemp>0&&CoinTemp>HerbPrice) {
            HerbAmountOwnedTemp++;
            HerbAmountInTownTemp--;
            CoinTemp = CoinTemp - HerbPrice;
            FullHerbTempPrice = FullHerbTempPrice + HerbPrice;
            UpdateHerbWithTemp();
        }
    }

    public void SellHerbTemp()
    {
        if(HerbAmountOwnedTemp>0) {
            HerbAmountInTownTemp++;
            HerbAmountOwnedTemp--;
            CoinTemp = CoinTemp + HerbPrice;
            FullHerbTempPrice = FullHerbTempPrice - HerbPrice;
            UpdateHerbWithTemp();
        }
    }

    public void UpdateFoodWithTemp()
    {
        TextView FullFoodTempPriceText = findViewById(R.id.FoodFullPriceText);
        FullFoodTempPriceText.setText(String.format("%.2f",FullFoodTempPrice));
        FullFoodTempPriceText.setTextColor(Color.parseColor(PriceColorChanger(FullFoodTempPrice)));
        TextView FoodCurrentText = findViewById(R.id.FoodCurrentText);
        FoodCurrentText.setText(Integer.toString(FoodAmountOwnedTemp));
        TextView FoodInTownText = findViewById(R.id.FoodInTownText);
        FoodInTownText.setText(Integer.toString(FoodAmountInTownTemp));
    }

    public void UpdateWoodWithTemp()
    {
        TextView FullWoodTempPriceText = findViewById(R.id.WoodFullPriceText);
        FullWoodTempPriceText.setText(String.format("%.2f",FullWoodTempPrice));
        FullWoodTempPriceText.setTextColor(Color.parseColor(PriceColorChanger(FullWoodTempPrice)));
        TextView WoodCurrentText = findViewById(R.id.WoodCurrentText);
        WoodCurrentText.setText(Integer.toString(WoodAmountOwnedTemp));
        TextView WoodInTownText = findViewById(R.id.WoodInTownText);
        WoodInTownText.setText(Integer.toString(WoodAmountInTownTemp));
    }

    public void UpdateMetalWithTemp()
    {
        TextView FullMetalTempPriceText = findViewById(R.id.MetalFullPriceText);
        FullMetalTempPriceText.setText(String.format("%.2f",FullMetalTempPrice));
        FullMetalTempPriceText.setTextColor(Color.parseColor(PriceColorChanger(FullMetalTempPrice)));
        TextView MetalCurrentText = findViewById(R.id.MetalCurrentText);
        MetalCurrentText.setText(Integer.toString(MetalAmountOwnedTemp));
        TextView MetalInTownText = findViewById(R.id.MetalInTownText);
        MetalInTownText.setText(Integer.toString(MetalAmountInTownTemp));
    }

    public void UpdateHerbWithTemp()
    {
        TextView FullHerbTempPriceText = findViewById(R.id.HerbFullPriceText);
        FullHerbTempPriceText.setText(String.format("%.2f",FullHerbTempPrice));
        FullHerbTempPriceText.setTextColor(Color.parseColor(PriceColorChanger(FullHerbTempPrice)));
        TextView HerbCurrentText = findViewById(R.id.HerbCurrentText);
        HerbCurrentText.setText(Integer.toString(HerbAmountOwnedTemp));
        TextView HerbInTownText = findViewById(R.id.HerbInTownText);
        HerbInTownText.setText(Integer.toString(HerbAmountInTownTemp));
    }

    public String PriceColorChanger(double Price)
    {
        String PriceColor = "";
        if(Price<0)
        {
            PriceColor = "#40A32A";
        }
        else if(Price>0)
        {
            PriceColor = "#D31B1B";
        }
        else
        {
            PriceColor = "#FFFFFFFF";
        }
        return PriceColor;
    }

    public void UpdateCoin()
    {
        TextView CoinText = findViewById(R.id.CoinText);
        CoinText.setText(String.format("%.2f",Coin));
    }

    public void UpdatePrices()
    {
        TextView FoodPriceText = findViewById(R.id.FoodPriceText);
        FoodPriceText.setText(String.format("%.2f",FoodPrice));
        TextView WoodPriceText = findViewById(R.id.WoodPriceText);
        WoodPriceText.setText(String.format("%.2f",WoodPrice));
        TextView MetalPriceText = findViewById(R.id.MetalPriceText);
        MetalPriceText.setText(String.format("%.2f",MetalPrice));
        TextView HerbPriceText = findViewById(R.id.HerbPriceText);
        HerbPriceText.setText(String.format("%.2f",HerbPrice));
    }

    public void UpdateInventory()
    {
        TextView FoodCurrentText = findViewById(R.id.FoodCurrentText);
        FoodCurrentText.setText(Integer.toString(FoodAmountOwned));
        TextView WoodCurrentText = findViewById(R.id.WoodCurrentText);
        WoodCurrentText.setText(Integer.toString(WoodAmountOwned));
        TextView MetalCurrentText = findViewById(R.id.MetalCurrentText);
        MetalCurrentText.setText(Integer.toString(MetalAmountOwned));
        TextView HerbCurrentText = findViewById(R.id.HerbCurrentText);
        HerbCurrentText.setText(Integer.toString(HerbAmountOwned));
    }

    public void ResetTradeTransaction()
    {
        CoinTemp = Coin;
        FoodAmountOwnedTemp = FoodAmountOwned;
        FoodAmountInTownTemp = FoodAmountInTown;
        FullFoodTempPrice = 0;
        UpdateFoodWithTemp();
        WoodAmountOwnedTemp = WoodAmountOwned;
        WoodAmountInTownTemp = WoodAmountInTown;
        FullWoodTempPrice = 0;
        UpdateWoodWithTemp();
        MetalAmountOwnedTemp = MetalAmountOwned;
        MetalAmountInTownTemp = MetalAmountInTown;
        FullMetalTempPrice = 0;
        UpdateMetalWithTemp();
        HerbAmountOwnedTemp = HerbAmountOwned;
        HerbAmountInTownTemp = HerbAmountInTown;
        FullHerbTempPrice = 0;
        UpdateHerbWithTemp();
    }

    public void ConfirmTransaction()
    {
        Coin = CoinTemp;
        UpdateCoin();
        FoodAmountOwned = FoodAmountOwnedTemp;
        FoodAmountInTown = FoodAmountInTownTemp;
        FullFoodTempPrice = 0;
        UpdateFoodWithTemp();
        WoodAmountOwned = WoodAmountOwnedTemp;
        WoodAmountInTown = WoodAmountInTownTemp;
        FullWoodTempPrice = 0;
        UpdateWoodWithTemp();
        MetalAmountOwned = MetalAmountOwnedTemp;
        MetalAmountInTown = MetalAmountInTownTemp;
        FullMetalTempPrice = 0;
        UpdateMetalWithTemp();
        HerbAmountOwned = HerbAmountOwnedTemp;
        HerbAmountInTown = HerbAmountInTownTemp;
        FullHerbTempPrice = 0;
        UpdateHerbWithTemp();
        CurrentTown = new Town(CurrentTownName,FoodPrice,FoodAmountInTown,WoodPrice,WoodAmountInTown,MetalPrice,MetalAmountInTown,HerbPrice,HerbAmountInTown);
        if(!CurrentTownName.equals("Starter Cove")) {
            UpdateTownInDatabase(CurrentTown);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        DbHelper setupdbh = new DbHelper(this);
        //<First Time DB setup>
        boolean FirstTimeFlag = false;
        FirstTimeFlag = setupdbh.CheckIfTableIsEmpty();
        if(FirstTimeFlag == true)
        {
            InitialDBConfig();
        }
        //</First Time DB Setup>
        Bundle extras = getIntent().getExtras();
        //<Setup variables>
        if(extras != null)
        {
                CurrentTownName = extras.getString("CurrentTownName");
                Coin = extras.getDouble("InventoryCoin");
                CoinTemp = Coin;
                FoodAmountOwned = extras.getInt("InventoryFood");
                FoodAmountOwnedTemp = FoodAmountOwned;
                WoodAmountOwned = extras.getInt("InventoryWood");
                WoodAmountOwnedTemp = WoodAmountOwned;
                MetalAmountOwned = extras.getInt("InventoryMetal");
                MetalAmountOwnedTemp = MetalAmountOwned;
                HerbAmountOwned = extras.getInt("InventoryHerb");
                HerbAmountOwnedTemp = HerbAmountOwned;
                UpdateCoin();
                if(!CurrentTownName.equals("Starter Cove")) {
                    DbHelper dbh = new DbHelper(MainActivity.this);
                    ArrayList<Town> towns = dbh.getAllRecords();
                    Town NewTown;
                    int towni = 0;
                    for(int i =0;i<towns.size();i++)
                    {
                        if(towns.get(i).getTownName().equals(CurrentTownName))
                        {
                            towni = i;
                            /*NewTown.setTownName(towns.get(i).getTownName());
                            NewTown.setFoodAmount(towns.get(i).getFoodAmount());
                            NewTown.setFoodPrice(towns.get(i).getFoodPrice());
                            NewTown.setWoodAmount(towns.get(i).getWoodAmount());
                            NewTown.setWoodPrice(towns.get(i).getWoodPrice());
                            NewTown.setMetalAmount(towns.get(i).getMetalAmount());
                            NewTown.setMetalPrice(towns.get(i).getMetalPrice());
                            NewTown.setHerbAmount(towns.get(i).getHerbAmount());
                            NewTown.setHerbPrice(towns.get(i).getHerbPrice());*/
                        }
                    }
                    NewTown = new Town(towns.get(towni).getTownName(),towns.get(towni).getFoodPrice(),towns.get(towni).getFoodAmount(),
                            towns.get(towni).getWoodPrice(),towns.get(towni).getWoodAmount(),towns.get(towni).getMetalPrice(),towns.get(towni).getMetalAmount()
                            ,towns.get(towni).getHerbPrice(),towns.get(towni).getHerbAmount());
                    FoodAmountInTown = NewTown.getFoodAmount();
                    FoodAmountInTownTemp = FoodAmountInTown;
                    FoodPrice = NewTown.getFoodPrice();
                    WoodAmountInTown = NewTown.getWoodAmount();
                    WoodAmountInTownTemp = WoodAmountInTown;
                    WoodPrice = NewTown.getWoodPrice();
                    MetalAmountInTown = NewTown.getMetalAmount();
                    MetalAmountInTownTemp = MetalAmountInTown;
                    MetalPrice = NewTown.getMetalPrice();
                    HerbAmountInTown = NewTown.getHerbAmount();
                    HerbAmountInTownTemp = HerbAmountInTown;
                    HerbPrice = NewTown.getHerbPrice();
                }
                else
                {
                    FoodAmountInTown = 100 - FoodAmountOwned;
                    FoodAmountInTownTemp = FoodAmountInTown;
                    WoodAmountInTown = 100 - WoodAmountOwned;
                    WoodAmountInTownTemp = WoodAmountInTown;
                    MetalAmountInTown = 100 - MetalAmountOwned;
                    MetalAmountInTownTemp = MetalAmountInTown;
                    HerbAmountInTown = 100 - HerbAmountOwned;
                    HerbAmountInTownTemp = HerbAmountInTown;
                }

        }
        UpdateCoin();
        UpdatePrices();
        UpdateInventory();
        UpdateFoodWithTemp();
        UpdateWoodWithTemp();
        UpdateMetalWithTemp();
        UpdateHerbWithTemp();
        //</Setup variables>
        //<Buttons>
        Button FoodPlusButton = findViewById(R.id.FoodButton2);
        Button FoodMinusButton = findViewById(R.id.FoodButton1);
        Button WoodPlusButton = findViewById(R.id.WoodButton2);
        Button WoodMinusButton = findViewById(R.id.WoodButton1);
        Button MetalPlusButton = findViewById(R.id.MetalButton2);
        Button MetalMinusButton = findViewById(R.id.MetalButton1);
        Button HerbPlusButton = findViewById(R.id.HerbButton2);
        Button HerbMinusButton = findViewById(R.id.HerbButton1);
        Button DepartButton = findViewById(R.id.DepartButton);
        Button ResetButton = findViewById(R.id.ResetButton);
        Button ConfirmTransactionButton = findViewById(R.id.ConfirmTransactionButton);

        FoodPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyFoodTemp();
            }
        });
        FoodMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellFoodTemp();
            }
        });
        WoodPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyWoodTemp();
            }
        });
        WoodMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellWoodTemp();
            }
        });
        MetalPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyMetalTemp();
            }
        });
        MetalMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellMetalTemp();
            }
        });
        HerbPlusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BuyHerbTemp();
            }
        });
        HerbMinusButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SellHerbTemp();
            }
        });
        DepartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SwitchToDestinationActivity();
            }
        });
        ResetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ResetTradeTransaction();
            }
        });
        ConfirmTransactionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConfirmTransaction();
            }
        });
        //</Buttons>
    }

    public void UpdateTownInDatabase(Town town)
    {
        DbHelper dbh = new DbHelper(MainActivity.this);
        dbh.updateOne(town);
    }

    public void SwitchToDestinationActivity()
    {
        Intent SwitchToDestination = new Intent(MainActivity.this,DestinationActivity.class);
        GlobalCurrentTownName = CurrentTownName;
        InventoryCoin = Coin;
        InventoryFood = FoodAmountOwned;
        InventoryWood = WoodAmountOwned;
        InventoryMetal = MetalAmountOwned;
        InventoryHerb = HerbAmountOwned;
        SwitchToDestination.putExtra("CurrentTownName", GlobalCurrentTownName);
        SwitchToDestination.putExtra("InventoryCoin", InventoryCoin);
        SwitchToDestination.putExtra("InventoryFood", InventoryFood);
        SwitchToDestination.putExtra("InventoryWood", InventoryWood);
        SwitchToDestination.putExtra("InventoryMetal", InventoryMetal);
        SwitchToDestination.putExtra("InventoryHerb", InventoryHerb);
        startActivity(SwitchToDestination);
        finish();
    }
    //sets up the database entries on first startup
    public void InitialDBConfig()
    {
        DbHelper setup = new DbHelper(MainActivity.this);
        int RandomFoodAmount;
        int RandomWoodAmount;
        int RandomMetalAmount;
        int RandomHerbAmount;
        double RandomFoodPrice;
        double RandomWoodPrice;
        double RandomMetalPrice;
        double RandomHerbPrice;
        RandomFoodAmount = new Random().nextInt(100);
        RandomWoodAmount = new Random().nextInt(100);
        RandomMetalAmount = new Random().nextInt(100);
        RandomHerbAmount = new Random().nextInt(100);
        RandomFoodPrice = new Random().nextInt(20)*0.10;
        RandomWoodPrice = new Random().nextInt(20)*0.15;
        RandomMetalPrice = new Random().nextInt(20)*0.20;
        RandomHerbPrice = new Random().nextInt(20)*0.15;
        if(RandomFoodPrice == 0)
        {RandomFoodPrice = 0.20;}
        if(RandomWoodPrice == 0)
        {RandomWoodPrice = 0.20;}
        if(RandomMetalPrice == 0)
        {RandomMetalPrice = 0.20;}
        if(RandomHerbPrice == 0)
        {RandomHerbPrice = 0.20;}

        Town WestHarbor = new Town("West Harbor",RandomFoodPrice,RandomFoodAmount,RandomWoodPrice,RandomWoodAmount,RandomMetalPrice,RandomMetalAmount,RandomHerbPrice,RandomHerbAmount);
        setup.addTown(WestHarbor);
        RandomFoodAmount = new Random().nextInt(100);
        RandomWoodAmount = new Random().nextInt(100);
        RandomMetalAmount = new Random().nextInt(100);
        RandomHerbAmount = new Random().nextInt(100);
        RandomFoodPrice = new Random().nextInt(20)*0.10;
        RandomWoodPrice = new Random().nextInt(20)*0.15;
        RandomMetalPrice = new Random().nextInt(20)*0.20;
        RandomHerbPrice = new Random().nextInt(20)*0.15;
        if(RandomFoodPrice == 0)
        {RandomFoodPrice = 0.20;}
        if(RandomWoodPrice == 0)
        {RandomWoodPrice = 0.20;}
        if(RandomMetalPrice == 0)
        {RandomMetalPrice = 0.20;}
        if(RandomHerbPrice == 0)
        {RandomHerbPrice = 0.20;}
        Town Alcanlara = new Town("Alcanlara",RandomFoodPrice,RandomFoodAmount,RandomWoodPrice,RandomWoodAmount,RandomMetalPrice,RandomMetalAmount,RandomHerbPrice,RandomHerbAmount);
        setup.addTown(Alcanlara);
        RandomFoodAmount = new Random().nextInt(100);
        RandomWoodAmount = new Random().nextInt(100);
        RandomMetalAmount = new Random().nextInt(100);
        RandomHerbAmount = new Random().nextInt(100);
        RandomFoodPrice = new Random().nextInt(20)*0.10;
        RandomWoodPrice = new Random().nextInt(20)*0.15;
        RandomMetalPrice = new Random().nextInt(20)*0.20;
        RandomHerbPrice = new Random().nextInt(20)*0.15;
        if(RandomFoodPrice == 0)
        {RandomFoodPrice = 0.20;}
        if(RandomWoodPrice == 0)
        {RandomWoodPrice = 0.20;}
        if(RandomMetalPrice == 0)
        {RandomMetalPrice = 0.20;}
        if(RandomHerbPrice == 0)
        {RandomHerbPrice = 0.20;}
        Town Chalos = new Town("Chalos",RandomFoodPrice,RandomFoodAmount,RandomWoodPrice,RandomWoodAmount,RandomMetalPrice,RandomMetalAmount,RandomHerbPrice,RandomHerbAmount);
        setup.addTown(Chalos);
        RandomFoodAmount = new Random().nextInt(100);
        RandomWoodAmount = new Random().nextInt(100);
        RandomMetalAmount = new Random().nextInt(100);
        RandomHerbAmount = new Random().nextInt(100);
        RandomFoodPrice = new Random().nextInt(20)*0.10;
        RandomWoodPrice = new Random().nextInt(20)*0.15;
        RandomMetalPrice = new Random().nextInt(20)*0.20;
        RandomHerbPrice = new Random().nextInt(20)*0.15;
        if(RandomFoodPrice == 0)
        {RandomFoodPrice = 0.20;}
        if(RandomWoodPrice == 0)
        {RandomWoodPrice = 0.20;}
        if(RandomMetalPrice == 0)
        {RandomMetalPrice = 0.20;}
        if(RandomHerbPrice == 0)
        {RandomHerbPrice = 0.20;}
        Town Damoria = new Town("Damoria",RandomFoodPrice,RandomFoodAmount,RandomWoodPrice,RandomWoodAmount,RandomMetalPrice,RandomMetalAmount,RandomHerbPrice,RandomHerbAmount);
        setup.addTown(Damoria);
    }
}