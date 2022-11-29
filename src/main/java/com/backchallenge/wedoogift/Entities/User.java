package com.backchallenge.wedoogift.Entities;

import com.backchallenge.wedoogift.Dto.GiftDto;
import com.backchallenge.wedoogift.Dto.MealDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document("users")
public class User {

    @Id
    private String id;

    private String userName;

    private String userLastName;

    private List<GiftDto> giftDeposits;

    private List<MealDto> mealDeposits;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserLastName() {
        return userLastName;
    }

    public void setUserLastName(String userLastName) {
        this.userLastName = userLastName;
    }

    public List<GiftDto> getGiftDeposits() {
        return giftDeposits;
    }

    public void setGiftDeposits(List<GiftDto> giftDeposits) {
        this.giftDeposits = giftDeposits;
    }

    public List<MealDto> getMealDeposits() {
        return mealDeposits;
    }

    public void setMealDeposits(List<MealDto> mealDeposits) {
        this.mealDeposits = mealDeposits;
    }
}
