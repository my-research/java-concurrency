package com.example.javaconcurrency;

public class Main {
    public static void main(String[] args) {
        SharedExclusiveLockExample example = new SharedExclusiveLockExample();

        // Start a thread to read the resource
        new Thread(example::readResource).start();

        // Start another thread to write to the resource
        new Thread(() -> example.writeResource(42)).start();
    }
}
