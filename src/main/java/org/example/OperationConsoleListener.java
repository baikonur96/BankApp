package org.example;

import org.example.operations.ConsoleOperationType;
import org.example.operations.OperationCommandProcessor;

import java.util.Map;
import java.util.Scanner;

public class OperationConsoleListener {
    private final Scanner scanner;
    private final Map<ConsoleOperationType, OperationCommandProcessor> processorMap;

    public OperationConsoleListener(Scanner scanner, Map<ConsoleOperationType, OperationCommandProcessor> processorMap) {
        this.scanner = scanner;
        this.processorMap = processorMap;
    }

    public void ListenUpdates(){
        while (true){
            var operationType = listenMextOperation();
            proceesNextOperation(operationType);


        }
    }

    public ConsoleOperationType listenMextOperation(){
        System.out.println("\nPlease type operations: ");
        printAllAvailableOperations();
        System.out.println();
        while(true){
        var nextOperation = scanner.nextLine();
        try {
            return ConsoleOperationType.valueOf(nextOperation);
        }catch (IllegalArgumentException e){
            System.out.println("No such command found");
        }
     }
    }

    private void printAllAvailableOperations() {
        processorMap.keySet().forEach(System.out::println);
    }



    private void proceesNextOperation(ConsoleOperationType operation){
        try{
            var processor = processorMap.get(operation);
            processor.processOperation();
                    }catch (Exception e){
            System.out.printf("Error executing command %s: error=%s%n", operation, e.getMessage());
        }

    }

    public void start(){
        System.out.println("Console listener started");
    }
    public void endListen(){
        System.out.println("Console listener end listen");
    }

}
