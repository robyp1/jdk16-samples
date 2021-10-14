package sample.jdk16.it.various;

public record User(String name, String surname) {

    public record Id(int value) {
    }
}
