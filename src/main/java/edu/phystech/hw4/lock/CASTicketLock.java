package edu.phystech.hw4.lock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author kzlv4natoly
 */
public class CASTicketLock {
    @SuppressWarnings("unused")
    private final AtomicInteger nextTicket = new AtomicInteger();
    @SuppressWarnings("unused")
    private final AtomicInteger currentTicket = new AtomicInteger();

    public void lock() {}

    public void unlock() {}
}
