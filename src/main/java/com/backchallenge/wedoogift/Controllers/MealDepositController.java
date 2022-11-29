package com.backchallenge.wedoogift.Controllers;

import com.backchallenge.wedoogift.Dto.MealDepositInput;
import com.backchallenge.wedoogift.Exceptions.NotEnoughException;
import com.backchallenge.wedoogift.Exceptions.NotFoundException;
import com.backchallenge.wedoogift.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/meals")
public class MealDepositController {

    private final UserService userService;

    public MealDepositController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Allow companies to do a gift deposit for a user")
    @PostMapping
    public String doAGiftDepositToUser(
            @RequestBody MealDepositInput mealDeposit) throws NotEnoughException, NotFoundException {
        userService.doAMealDeposit(mealDeposit);
        return "Deposit done";
    }
}
