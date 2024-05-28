package org.example;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Component
public class ConsoleListenerStarter {
    private final OperationConsoleListener operationConsoleListener;
    private Thread consoleListenerThread;

    public ConsoleListenerStarter(OperationConsoleListener operationConsoleListener) {
        this.operationConsoleListener = operationConsoleListener;
    }

    @PostConstruct
    public void postContruct(){
        consoleListenerThread = new Thread(() -> {
            operationConsoleListener.start();
            operationConsoleListener.ListenUpdates();
        });
        consoleListenerThread.start();
    }

    @PreDestroy
    public void preDestroy(){
        consoleListenerThread.interrupt();
        operationConsoleListener.endListen();
    }
}
