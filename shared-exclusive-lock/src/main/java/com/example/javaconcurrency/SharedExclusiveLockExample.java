package com.example.javaconcurrency;

class SharedExclusiveLockExample {
    private final SharedExclusiveLock lock = new SharedExclusiveLock();
    private int sharedResource = 0;

    public void readResource() {
        try {
            lock.lockShared();
            try {
                System.out.println("Reading resource: " + sharedResource);
            } finally {
                lock.unlockShared();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public void writeResource(int value) {
        try {
            lock.lockExclusive();
            try {
                System.out.println("Writing value: " + value);
                sharedResource = value;
            } finally {
                lock.unlockExclusive();
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
