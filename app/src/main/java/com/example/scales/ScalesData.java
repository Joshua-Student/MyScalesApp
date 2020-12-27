package com.example.scales;

import java.util.ArrayList;
import java.util.Arrays;

public class ScalesData {
    static ArrayList<String> notes = new ArrayList<>(
            Arrays.asList("A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab", "A", "Bb", "B", "C", "Db", "D", "Eb", "E", "F", "Gb", "G", "Ab"));
    private ArrayList<String> list;

    public String major(String s) {
        list = new ArrayList<>();
        int index = notes.indexOf(s);
        list.add(notes.get(index));
        list.add(notes.get(index + 2));
        list.add(notes.get(index + 4));
        list.add(notes.get(index + 5));
        list.add(notes.get(index + 7));
        list.add(notes.get(index + 9));
        list.add(notes.get(index + 11));
        return getString(list);
    }

    public String minor(String s) {
        list = new ArrayList<>();
        int index = notes.indexOf(s);
        list.add(notes.get(index));
        list.add(notes.get(index + 2));
        list.add(notes.get(index + 3));
        list.add(notes.get(index + 5));
        list.add(notes.get(index + 7));
        list.add(notes.get(index + 8));
        list.add(notes.get(index + 10));
        return getString(list);
    }

    public String pentatonicMajor(String s) {
        list = new ArrayList<>();
        int index = notes.indexOf(s);
        list.add(notes.get(index));
        list.add(notes.get(index + 2));
        list.add(notes.get(index + 4));
        list.add(notes.get(index + 7));
        list.add(notes.get(index + 9));
        return getString(list);
    }

    public String pentatonicMinor(String s) {
        list = new ArrayList<>();
        int index = notes.indexOf(s);
        list.add(notes.get(index));
        list.add(notes.get(index + 3));
        list.add(notes.get(index + 5));
        list.add(notes.get(index + 7));
        list.add(notes.get(index + 10));
        return getString(list);
    }

    public String melodicAndHarmonicMinor(String s) {
        list = new ArrayList<>();
        int index = notes.indexOf(s);
        list.add(notes.get(index));
        list.add(notes.get(index + 2));
        list.add(notes.get(index + 3));
        list.add(notes.get(index + 5));
        list.add(notes.get(index + 7));
        list.add(notes.get(index + 9));
        list.add(notes.get(index + 11));
        return getString(list);
    }

    public String getString(ArrayList<String> list) {
        String scale = " ";
        for (String s : list) {
            scale += s + "   ";
        }
        return scale;
    }
}
