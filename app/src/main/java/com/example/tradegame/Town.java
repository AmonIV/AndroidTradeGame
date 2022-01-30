package com.example.tradegame;

public class Town {
    private Integer ID;
    private String TownName;
    private Double FoodPrice;
    private int FoodAmount;
    private Double WoodPrice;
    private Integer WoodAmount;
    private Double MetalPrice;
    private Integer MetalAmount;
    private Double HerbPrice;
    private Integer HerbAmount;
    public Integer getID() {
        return ID;
    }

    public void setID(Integer ID) {
        this.ID = ID;
    }

    public String getTownName() {
        return TownName;
    }

    public void setTownName(String townName) {
        TownName = townName;
    }

    public Double getFoodPrice() {
        return FoodPrice;
    }

    public void setFoodPrice(Double foodPrice) {
        FoodPrice = foodPrice;
    }

    public int getFoodAmount() {
        return FoodAmount;
    }

    public void setFoodAmount(int foodAmount) {
        FoodAmount = foodAmount;
    }

    public Double getWoodPrice() {
        return WoodPrice;
    }

    public void setWoodPrice(Double woodPrice) {
        WoodPrice = woodPrice;
    }

    public Integer getWoodAmount() {
        return WoodAmount;
    }

    public void setWoodAmount(Integer woodAmount) {
        WoodAmount = woodAmount;
    }

    public Double getMetalPrice() {
        return MetalPrice;
    }

    public void setMetalPrice(Double metalPrice) {
        MetalPrice = metalPrice;
    }

    public Integer getMetalAmount() {
        return MetalAmount;
    }

    public void setMetalAmount(Integer metalAmount) {
        MetalAmount = metalAmount;
    }

    public Double getHerbPrice() {
        return HerbPrice;
    }

    public void setHerbPrice(Double herbPrice) {
        HerbPrice = herbPrice;
    }

    public Integer getHerbAmount() {
        return HerbAmount;
    }

    public void setHerbAmount(Integer herbAmount) {
        HerbAmount = herbAmount;
    }

    public Town()
    {}

    public Town(String TownName, Double FoodPrice, Integer FoodAmount, Double WoodPrice, Integer WoodAmount, Double MetalPrice, Integer MetalAmount, Double HerbPrice, Integer HerbAmount)
    {
        this.ID = ID;
        this.TownName = TownName;
        this.FoodPrice = FoodPrice;
        this.FoodAmount = FoodAmount;
        this.WoodPrice = WoodPrice;
        this.WoodAmount = WoodAmount;
        this.MetalPrice = MetalPrice;
        this.MetalAmount = MetalAmount;
        this.HerbPrice = HerbPrice;
        this.HerbAmount = HerbAmount;
    }
}
