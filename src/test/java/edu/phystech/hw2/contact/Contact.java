package edu.phystech.hw2.contact;

public record Contact(String username, String email) implements Comparable<Contact> {

    public static final String UNKNOWN_EMAIL = "unknown@gmail.com";

    public Contact {
        if (username == null || username.isBlank()) {
            throw new InvalidContactFieldException("username");
        }
        
        if (email == null || email.isBlank() || !email.endsWith("@gmail.com")) {
            throw new InvalidContactFieldException("email");
        }
    }

    public Contact(String username) {
        this(username, UNKNOWN_EMAIL);
    }

    @Override
    public int compareTo(Contact other) {
        return Integer.compare(this.username.length(), other.username.length());
    }
}
