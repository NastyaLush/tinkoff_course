package edu.hw8.task2;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class FixedThreadPoolImpl implements ThreadPool {

    private final Integer countOfThreads;
    private final LinkedBlockingQueue<Runnable> queue;
    private final List<Thread> threads = new ArrayList<>();
    private final Integer waitSeconds = 50;

    public FixedThreadPoolImpl(Integer countOfThreads) {
        this.countOfThreads = countOfThreads;
        this.queue = new LinkedBlockingQueue<>();
    }

    private Thread createThread() {
        log.info("create thread");
        return new Thread(() -> {
            log.info("run");
            Runnable task;
            while (true) {
                try {
                    task = queue.take();
                    task.run();
                } catch (InterruptedException e) {
                    log.info("interrapt");
                    break;
                }

            }
        });
    }

    @Override
    public void start() {
        for (int i = 0; i < countOfThreads; i++) {
            Thread thread = createThread();
            threads.add(thread);
            thread.start();
        }
    }

    @Override
    public synchronized void execute(Runnable runnable) {
        queue.add(runnable);
    }

    @Override
    public synchronized void close() throws Exception {
        while (!queue.isEmpty()) {
            wait(waitSeconds);
        }
        log.info("queue is empty, close threads");
        for (Thread thread : threads) {
            thread.interrupt();
            thread.join();
        }
    }
}
