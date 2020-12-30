package net.fungriml.elog4j.api;

import java.util.Map;

public class Payload {

    public static class Node {

        public static Node of(String s) {
            return null;
        }

        public static Node of(Number s) {
            return null;
        }

        public static Node of(Boolean s) {
            return null;
        }
    }

    public static Payload of(Map<String, ?> values) {

        return null;
    }

    public static Payload empty() {
        // TODO Auto-generated method stub
        return null;
    }

    public Payload() {
    }

    public Payload(Payload p) {
        // TODO Auto-generated constructor stub
    }

    public void integrate(Payload p) {
        // TODO Auto-generated method stub

    }

    public boolean set(String name, Node value) {
        // TODO Auto-generated method stub

        return false;
    }

    public void clear() {
        // TODO Auto-generated method stub

    }

}
