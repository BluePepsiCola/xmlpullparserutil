package com.xaskysad.xmlparser_master.structalgo;

import android.support.v4.util.Pair;

import java.util.EmptyStackException;
import java.util.Stack;



public class PairStack<T, R> {

    private Stack<T> tStack;
    private Stack<R> rStack;

    public PairStack() {
        tStack = new Stack<>();
        rStack = new Stack<>();
    }


    public synchronized void clear() {
        tStack.clear();
        rStack.clear();
    }

    public synchronized boolean containsKey(Object key) {

        return tStack.contains(key);

    }

    public synchronized boolean containsValue(Object value) {
        return rStack.contains(value);
    }


    public synchronized boolean isEmpty() {

        if (tStack.isEmpty() != rStack.isEmpty())
            throw new RuntimeException("Unknow error~!");

        return tStack.isEmpty() && rStack.isEmpty();
    }

    public synchronized Pair<Stack<T>,Stack<R>> pairSet() {

        return new Pair(tStack,rStack);
    }


    public synchronized void put(Pair<T, R> pair) {

        tStack.push(pair.first);
        rStack.push(pair.second);

    }

    public void pop() {

        popIfFrist(tStack.peek());
    }


    public synchronized Pair<T, R> peek() {
        return new Pair<>(tStack.peek(), rStack.peek());
    }

    public synchronized boolean popIfFrist(T t) {

        if (tStack.peek().equals(t)) {
            T temp = tStack.pop();
            try {
                rStack.pop();

                return true;
            } catch (EmptyStackException e) {
                tStack.push(temp);
                return false;
            }
        }

        return false;
    }

    public synchronized boolean popIfSecond(R r) {

        if (rStack.peek().equals(r)) {

            R remp = rStack.pop();

            try {
                tStack.pop();

                return true;

            } catch (EmptyStackException e) {
                rStack.push(remp);
                return false;
            }
        }

        return false;
    }


    public synchronized int size() {

        if (tStack.size() != rStack.size())
            throw new RuntimeException("Unknow error~!");
        return tStack.size();

    }


}
