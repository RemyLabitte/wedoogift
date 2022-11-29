package com.backendchallenge.wedoogift;

import com.backchallenge.wedoogift.Dto.UserDto;
import com.backchallenge.wedoogift.Entities.User;
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

    private User retrieveUserFromJson() {
        return JsonMockerUtils.getClassFromJson(User.class, "user1");
    }
}
