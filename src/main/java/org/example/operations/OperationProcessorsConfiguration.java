package org.example.operations;

import org.example.account.AccountService;
import org.example.operations.processors.*;
import org.example.user.User;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class OperationProcessorsConfiguration {

    @Bean
    public CreateUserProcessor createUserProcessor(Scanner scanner, UserService userService){
        return new CreateUserProcessor(scanner, userService);
    }

    @Bean
    public CreateAccountProcessor createAccountProcessor(Scanner scanner, UserService userService, AccountService accountService){
        return new CreateAccountProcessor(scanner, userService, accountService);
    }

    @Bean
    public ShowAllUsersProcessor showAllUsersProcessor(UserService userService){
        return new ShowAllUsersProcessor(userService);
    }

    @Bean
    public DepositAccountProcessor depositAccountProcessor(Scanner scanner, AccountService accountService){
        return new DepositAccountProcessor(scanner, accountService);
    }

    @Bean
    public AccountWithdrawProcessor accountWithdrawProcessor(Scanner scanner, AccountService accountService){
        return new AccountWithdrawProcessor(scanner, accountService);
    }

}
