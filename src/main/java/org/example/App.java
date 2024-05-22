package org.example;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext("org.example");
        OperationConsoleListener operationConsoleListener = context.getBean(OperationConsoleListener.class);
        operationConsoleListener.start();
        operationConsoleListener.ListenUpdates();
        operationConsoleListener.endListen();

    }
}
