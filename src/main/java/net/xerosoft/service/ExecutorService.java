package net.xerosoft.service;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.inject.Singleton;
import java.util.concurrent.Executors;

@Log
@Singleton
public class ExecutorService {
    private final java.util.concurrent.ExecutorService executor;

    public ExecutorService() {
        executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    @PostConstruct
    private void init() {
        log.info("Executor threads: " + Runtime.getRuntime().availableProcessors());
    }

    @PreDestroy
    private void shutdown() {
        executor.shutdown();
    }

    public void submit(Runnable runnable) throws Exception {
        executor.submit(runnable);
    }
}
