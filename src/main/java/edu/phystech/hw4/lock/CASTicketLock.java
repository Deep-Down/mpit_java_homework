package edu.phystech.hw4.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kzlv4natoly
 */
public class CASTicketLock {
    private final AtomicInteger nextTicket = new AtomicInteger(0);
    private final AtomicInteger currentTicket = new AtomicInteger(0);

    public void lock() {
        int myTicket = nextTicket.getAndIncrement();
        while (currentTicket.get() != myTicket) {
            Thread.onSpinWait();
        }
    }

    public void unlock() {
        int next = currentTicket.get() + 1;
        currentTicket.set(next);
    }
}
