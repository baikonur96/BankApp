package org.example;

import org.example.account.AccountService;
import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;
import org.example.operations.processors.CreateAccountProcessor;
import org.example.operations.processors.CreateUserProcessor;
import org.example.operations.processors.ShowAllUsersProcessor;
import org.example.user.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Configuration
public class ApplicationConfiguration {
    @Bean
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    @Bean OperationConsoleListener operationConsoleListener(Scanner scanner,
                                                            List<OperationCommandProcessor> commandProcessors){
        Map<ConsoleOperationType, OperationCommandProcessor> map = commandProcessors.stream().collect(
                Collectors.toMap(
                        OperationCommandProcessor::getOperationType,
                        processor -> processor
                )
        );
        return new OperationConsoleListener(scanner, map);
    }

    @Bean
    public UserService userService(AccountService accountService){
        return new UserService(accountService);
    }

    @Bean
    public AccountService accountService(){
        return new AccountService();
    }

}
