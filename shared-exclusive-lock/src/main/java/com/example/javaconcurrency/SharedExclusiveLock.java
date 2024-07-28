package com.example.javaconcurrency;

import static java.lang.Thread.currentThread;

class SharedExclusiveLock {
    private int readers = 0;
    private boolean writer = false; // 오로지 하나

    public synchronized void lockShared() throws InterruptedException {
        System.out.println("[S] 획득 시도중 " + currentThread().getName());
        while (writer) {
            System.out.println("[S] writer 스레드 대기 " + currentThread().getName());
            wait();
        }
        System.out.println("[S] 획득 " + currentThread().getName());
        readers++;
    }

    // Releases the shared (read) lock
    public synchronized void unlockShared() {
        System.out.println("[S] 반환 시도중 " + currentThread().getName());
        readers--;
        if (readers == 0) {
            notifyAll();
            System.out.println("[S] 대기 중인 스레드 알림 " + currentThread().getName());
        }
    }

    // Acquires the exclusive (write) lock
    public synchronized void lockExclusive() throws InterruptedException {
        System.out.println("[X] 획득 시도중 " + currentThread().getName());
        while (writer || readers > 0) {
            System.out.println("[X] 대기중 " + currentThread().getName());
            wait();
        }
        System.out.println("[X] 획득 " + currentThread().getName());
        writer = true;
    }

    // Releases the exclusive (write) lock
    public synchronized void unlockExclusive() {
        writer = false;
        notifyAll();
    }
}
