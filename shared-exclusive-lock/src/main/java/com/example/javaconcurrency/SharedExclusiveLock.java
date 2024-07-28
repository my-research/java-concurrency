package com.example.javaconcurrency;

class SharedExclusiveLock {
    private int readers = 0;
    private boolean writer = false;

    // Acquires the shared (read) lock
    public synchronized void lockShared() throws InterruptedException {
        while (writer) {
            wait();
        }
        readers++;
    }

    // Releases the shared (read) lock
    public synchronized void unlockShared() {
        readers--;
        if (readers == 0) {
            notifyAll();
        }
    }

    // Acquires the exclusive (write) lock
    public synchronized void lockExclusive() throws InterruptedException {
        while (writer || readers > 0) {
            wait();
        }
        writer = true;
    }

    // Releases the exclusive (write) lock
    public synchronized void unlockExclusive() {
        writer = false;
        notifyAll();
    }
}
