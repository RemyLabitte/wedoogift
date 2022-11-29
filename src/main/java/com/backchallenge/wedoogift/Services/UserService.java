package com.backchallenge.wedoogift.Services;

import com.backchallenge.wedoogift.Dto.CompanyDto;
import com.backchallenge.wedoogift.Dto.GiftDepositInput;
import com.backchallenge.wedoogift.Dto.GiftDto;
import com.backchallenge.wedoogift.Dto.MealDepositInput;
import com.backchallenge.wedoogift.Dto.MealDto;
import com.backchallenge.wedoogift.Entities.User;
import com.backchallenge.wedoogift.Exceptions.NotEnoughException;
import com.backchallenge.wedoogift.Exceptions.NotFoundException;
import com.backchallenge.wedoogift.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private final CompanyService companyService;

    public UserService(UserRepository userRepository, CompanyService companyService) {
        this.userRepository = userRepository;
        this.companyService = companyService;
    }

    public void doGiftDeposit(GiftDepositInput giftDeposit) throws NotFoundException, NotEnoughException {
        Optional<User> user = userRepository.findById(giftDeposit.getUserId());

        CompanyDto company = companyService.retrieveCompanyByName(giftDeposit.getCompanyName());

        if (company.getBalance() >= giftDeposit.getAmount()) {
            if (user.isPresent()) {
                GiftDto newGiftDto = new GiftDto();
                newGiftDto.setAmount(giftDeposit.getAmount());
                newGiftDto.setStartDate(new Date());
                newGiftDto.setCompanyName(giftDeposit.getCompanyName());

                if (null != user.get().getGiftDeposits()) {
                    user.get().getGiftDeposits().add(newGiftDto);
                } else {
                    user.get().setGiftDeposits(new ArrayList<>());
                    user.get().getGiftDeposits().add(newGiftDto);
                }

                company.setBalance(company.getBalance() - newGiftDto.getAmount());
                userRepository.save(user.get());
                companyService.updateCompanyBalance(company);
            } else {
                throw new NotFoundException("User with id " + giftDeposit.getUserId() + " was not found");
            }
        } else {
            throw new NotEnoughException("Company balance is not enough.");
        }

    }

    public void doAMealDeposit(MealDepositInput mealDeposit) throws NotEnoughException, NotFoundException {
        Optional<User> user = userRepository.findById(mealDeposit.getUserId());

        CompanyDto company = companyService.retrieveCompanyByName(mealDeposit.getCompanyName());

        if (company.getBalance() >= mealDeposit.getAmount()) {
            if (user.isPresent()) {
                MealDto newGift = new MealDto();
                newGift.setAmount(mealDeposit.getAmount());
                newGift.setStartDate(new Date());
                newGift.setCompanyName(mealDeposit.getCompanyName());

                if (null != user.get().getMealDeposits()) {
                    user.get().getMealDeposits().add(newGift);
                } else {
                    user.get().setMealDeposits(new ArrayList<>());
                    user.get().getMealDeposits().add(newGift);
                }

                company.setBalance(company.getBalance() - newGift.getAmount());
                userRepository.save(user.get());
                companyService.updateCompanyBalance(company);
            } else {
                throw new NotFoundException("User with id " + mealDeposit.getUserId() + " was not found");
            }
        } else {
            throw new NotEnoughException("Company balance is not enough.");
        }
    }

}
