package com.example.tradegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;
//Unused Class
public class CoinHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "CoinsManager";
    private static final String TABLE_COINS = "Coin";
    private static final String KEY_ID = "ID";
    private static final String KEY_COIN_AMOUNT = "CoinAmount";

    public CoinHelper(@Nullable Context context) {
        super(context, "CoinManager", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_COINS_TABLE = "CREATE TABLE " + TABLE_COINS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_COIN_AMOUNT + " REAL"
                + ")";
        db.execSQL(CREATE_COINS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_COINS);
        onCreate(db);
    }

    public boolean addCoin(Double coin){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_COIN_AMOUNT, coin);
        long insert = db.insert(TABLE_COINS,null,values);
        if (insert == -1) return false;
        else return true;
//db.close();
    }

    public Double GetCoinAmount()
    {
        Double CoinAmount = 1000.0;
        String SelectQuery = "SELECT * FROM " + TABLE_COINS + " Where ID = 1";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor coinCursor = db.rawQuery(SelectQuery,null);
        if(coinCursor.moveToFirst())
        {
            double query_coin = coinCursor.getDouble(1);
        }
        return CoinAmount;
    }
}
