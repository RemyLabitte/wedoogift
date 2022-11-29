package com.backendchallenge.wedoogift;

import com.backchallenge.wedoogift.Dto.CompanyDto;
import com.backchallenge.wedoogift.Dto.GiftDepositInput;
import com.backchallenge.wedoogift.Dto.MealDepositInput;
import com.backchallenge.wedoogift.Dto.UserDto;
import com.backchallenge.wedoogift.Entities.User;
import com.backchallenge.wedoogift.Exceptions.NotEnoughException;
import com.backchallenge.wedoogift.Exceptions.NotFoundException;
import com.backchallenge.wedoogift.Repositories.UserRepository;
import com.backchallenge.wedoogift.Services.CompanyService;
import com.backchallenge.wedoogift.Services.UserService;
import com.backchallenge.wedoogift.WedoogiftApplication;
import com.backendchallenge.wedoogift.utils.JsonMockerUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest(classes = WedoogiftApplication.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private CompanyService companyService;

    @InjectMocks
    private UserService userService;

    @Test
    void should_returnUserBalance() throws NotFoundException {
        //GIVEN
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(retrieveUserFromJson()));

        //WHEN
        UserDto user = userService.calculateUserBalance("1");

        //THEN
        Assertions.assertNotNull(user);
        Assertions.assertEquals(100, user.getGiftBalance());
        Assertions.assertEquals(105, user.getMealBalance());
    }

    @Test
    void should_throwNotFoundExceptionWhenTryingToCalculateUserBalance() {
        //GIVEN
        when(userRepository.findById("2")).thenReturn(Optional.empty());

        //THEN
        Assertions.assertThrows(NotFoundException.class, () -> userService.calculateUserBalance("2"));
    }

    @Test
    void should_doAGiftDeposit() {
        //GIVEN
        GiftDepositInput giftDeposit = new GiftDepositInput();
        giftDeposit.setUserId("1");
        giftDeposit.setAmount(150);
        giftDeposit.setCompanyName("Samsung");

        CompanyDto company = retrieveCompanyByName("Samsung");

        when(companyService.retrieveCompanyByName("Samsung")).thenReturn(company);
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(retrieveUserFromJson()));

        //THEN
        Assertions.assertDoesNotThrow(() -> userService.doGiftDeposit(giftDeposit));
    }

    @Test
    void should_throwNotEnoughExceptionWhenTryingToDoDeposit() {
        //GIVEN
        GiftDepositInput giftDeposit = new GiftDepositInput();
        giftDeposit.setUserId("1");
        giftDeposit.setAmount(150);
        giftDeposit.setCompanyName("Apple");

        when(companyService.retrieveCompanyByName("Apple")).thenReturn(retrieveCompanyByName("Apple"));
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(retrieveUserFromJson()));

        //THEN
        Assertions.assertThrows(NotEnoughException.class, () -> userService.doGiftDeposit(giftDeposit));
    }

    @Test
    void should_doAMealDeposit() {
        //GIVEN
        MealDepositInput mealDeposit = new MealDepositInput();
        mealDeposit.setUserId("1");
        mealDeposit.setAmount(150);
        mealDeposit.setCompanyName("Samsung");

        CompanyDto company = retrieveCompanyByName("Samsung");

        when(companyService.retrieveCompanyByName("Samsung")).thenReturn(company);
        when(userRepository.findById("1")).thenReturn(Optional.ofNullable(retrieveUserFromJson()));

        //THEN
        Assertions.assertDoesNotThrow(() -> userService.doAMealDeposit(mealDeposit));
    }

    @Test
    void should_throwNotFoundExceptionWhenTryingToDoDeposit() {
        //GIVEN
        MealDepositInput mealDeposit = new MealDepositInput();
        mealDeposit.setUserId("1");
        mealDeposit.setAmount(150);
        mealDeposit.setCompanyName("Samsung");

        when(companyService.retrieveCompanyByName("Samsung")).thenReturn(retrieveCompanyByName("Samsung"));
        when(userRepository.findById("1")).thenReturn(Optional.empty());

        //THEN
        Assertions.assertThrows(NotFoundException.class, () -> userService.doAMealDeposit(mealDeposit));
    }

    private User retrieveUserFromJson() {
        return JsonMockerUtils.getClassFromJson(User.class, "user1");
    }

    private CompanyDto retrieveCompanyByName(String companyName) {
        return JsonMockerUtils.getClassFromJson(CompanyDto.class, companyName);
    }
}
