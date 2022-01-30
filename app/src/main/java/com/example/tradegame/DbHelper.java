package com.example.tradegame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DbHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;

    private static final String DATABASE_NAME = "TownsManager";
    private static final String TABLE_TOWNS = "Town";
    private static final String KEY_ID = "ID";
    private static final String KEY_TOWN_NAME = "TownName";
    private static final String KEY_FOOD_PRICE = "FoodPrice";
    private static final String KEY_FOOD_AMOUNT = "FoodAmount";
    private static final String KEY_WOOD_PRICE = "WoodPrice";
    private static final String KEY_WOOD_AMOUNT = "WoodAmount";
    private static final String KEY_METAL_PRICE = "MetalPrice";
    private static final String KEY_METAL_AMOUNT = "MetalAmount";
    private static final String KEY_HERB_PRICE = "HerbPrice";
    private static final String KEY_HERB_AMOUNT = "HerbAmount";
    public DbHelper(@Nullable Context context) {
        super(context, "TownsManager", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TOWNS_TABLE = "CREATE TABLE " + TABLE_TOWNS + "("
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_TOWN_NAME + " TEXT, "
                + KEY_FOOD_PRICE + " REAL, " + KEY_FOOD_AMOUNT + " INTEGER, "
                + KEY_WOOD_PRICE + " REAL, " + KEY_WOOD_AMOUNT + " INTEGER, "
                + KEY_METAL_PRICE + " REAL, " + KEY_METAL_AMOUNT + " INTEGER, "
                + KEY_HERB_PRICE + " REAL, " + KEY_HERB_AMOUNT + " INTEGER"
                + ")";
        db.execSQL(CREATE_TOWNS_TABLE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TOWNS);
        onCreate(db);
    }
    public boolean addTown(Town town){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(KEY_TOWN_NAME, town.getTownName());
        values.put(KEY_FOOD_PRICE, town.getFoodPrice());
        values.put(KEY_FOOD_AMOUNT, town.getFoodAmount());
        values.put(KEY_WOOD_PRICE, town.getWoodPrice());
        values.put(KEY_WOOD_AMOUNT, town.getWoodAmount());
        values.put(KEY_METAL_PRICE, town.getMetalPrice());
        values.put(KEY_METAL_AMOUNT, town.getMetalAmount());
        values.put(KEY_HERB_PRICE, town.getHerbPrice());
        values.put(KEY_HERB_AMOUNT, town.getHerbAmount());
        long insert = db.insert(TABLE_TOWNS,null,values);
        if (insert == -1) return false;
        else return true;
//db.close();
    }
    public ArrayList<Town> getAllRecords(){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_TOWNS, null, null, null, null, null, null, null);
        ArrayList<Town> towns = new ArrayList<Town>();
        Town townModel;
        if (cursor.getCount()> 0 ){
            for (int i=0; i < cursor.getCount(); i++){
                cursor.moveToNext();
                townModel = new Town();
                townModel.setID(cursor.getInt(0));
                townModel.setTownName(cursor.getString(1));
                townModel.setFoodPrice(cursor.getDouble(2));
                townModel.setFoodAmount(cursor.getInt(3));
                townModel.setWoodPrice(cursor.getDouble(4));
                townModel.setWoodAmount(cursor.getInt(5));
                townModel.setMetalPrice(cursor.getDouble(6));
                townModel.setMetalAmount(cursor.getInt(7));
                townModel.setHerbPrice(cursor.getDouble(8));
                townModel.setHerbAmount(cursor.getInt(9));
                towns.add(townModel);
            }
        }
        cursor.close();
        db.close();
        return towns;
    }

    public boolean deleteOne (Town town){
        SQLiteDatabase db = this.getWritableDatabase();
        String queryString = " DELETE FROM " + TABLE_TOWNS + " WHERE "
                + KEY_ID + " = " + town.getID();
        Cursor cursor = db.rawQuery(queryString,null);
        if (cursor.moveToFirst()) return true;
        else return false;
    }
    public boolean updateOne (Town town) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(KEY_TOWN_NAME, town.getTownName());
        contentValues.put(KEY_FOOD_PRICE, town.getFoodPrice());
        contentValues.put(KEY_FOOD_AMOUNT, town.getFoodAmount());
        contentValues.put(KEY_WOOD_PRICE, town.getWoodPrice());
        contentValues.put(KEY_WOOD_AMOUNT, town.getWoodAmount());
        contentValues.put(KEY_METAL_PRICE, town.getMetalPrice());
        contentValues.put(KEY_METAL_AMOUNT, town.getMetalAmount());
        contentValues.put(KEY_HERB_PRICE, town.getHerbPrice());
        contentValues.put(KEY_HERB_AMOUNT, town.getHerbAmount());
        db.update(TABLE_TOWNS, contentValues, " TownName = ?", new
                String[]{String.valueOf(town.getTownName())});
        return true;
    }
    public ArrayList<Town> getSelection(){
        ArrayList<Town> query_result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String select_query = " SELECT * FROM " + TABLE_TOWNS + " ORDER BY " + KEY_TOWN_NAME + " DESC ";
        Cursor res = db.rawQuery(select_query,null);
        if (res.moveToFirst()){
            do {
                String query_town_name = res.getString(1);
                double query_food_price = res.getFloat(2);
                int query_food_amount = res.getInt(3);
                double query_wood_price = res.getFloat(4);
                int query_wood_amount = res.getInt(5);
                double query_metal_price = res.getFloat(6);
                int query_metal_amount = res.getInt(7);
                double query_herb_price = res.getFloat(8);
                int query_herb_amount = res.getInt(9);
                Town new_query_town = new Town(query_town_name, query_food_price, query_food_amount, query_wood_price, query_wood_amount, query_metal_price, query_metal_amount, query_herb_price, query_herb_amount);
                query_result.add(new_query_town);
            } while (res.moveToNext());
        }
        res.close();
        db.close();
        return query_result;
    }

    public Town getTownByTownName(String town_name){
        Town query_result;
        SQLiteDatabase db = this.getReadableDatabase();
        String select_query = "SELECT * FROM " + TABLE_TOWNS + " Where TownName=" + town_name;
        Cursor res = db.rawQuery(select_query,null);
        res.moveToFirst();
        String query_town_name = res.getString(1);
        double query_food_price = res.getFloat(2);
        int query_food_amount = res.getInt(3);
        double query_wood_price = res.getFloat(4);
        int query_wood_amount = res.getInt(5);
        double query_metal_price = res.getFloat(6);
        int query_metal_amount = res.getInt(7);
        double query_herb_price = res.getFloat(8);
        int query_herb_amount = res.getInt(9);
        query_result = new Town(query_town_name, query_food_price, query_food_amount, query_wood_price, query_wood_amount, query_metal_price, query_metal_amount, query_herb_price, query_herb_amount);
        res.close();
        db.close();
        return query_result;
    }

    public boolean CheckIfTableIsEmpty()
    {
        SQLiteDatabase db = this.getReadableDatabase();
        String test = "SELECT count(*) From " + TABLE_TOWNS;
        Cursor cursor = db.rawQuery(test,null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        if(count > 0)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

}
