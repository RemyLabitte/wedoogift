package com.backchallenge.wedoogift.Controllers;

import com.backchallenge.wedoogift.Dto.GiftDepositInput;
import com.backchallenge.wedoogift.Exceptions.NotEnoughException;
import com.backchallenge.wedoogift.Exceptions.NotFoundException;
import com.backchallenge.wedoogift.Services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gifts")
public class GiftDepositController {

    private final UserService userService;

    public GiftDepositController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Allow companies to do a gift deposit for a user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successful operation",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "404", description = "User not found",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class))),
            @ApiResponse(responseCode = "400", description = "Company has not enough to do a deposit",
                    content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = String.class)))
    })
    @PostMapping
    public ResponseEntity<String> doAGiftDepositToUser(@RequestBody GiftDepositInput giftDeposit) {
        try {
            userService.doGiftDeposit(giftDeposit);
            return ResponseEntity.ok("Deposit done");
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (NotEnoughException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

}
