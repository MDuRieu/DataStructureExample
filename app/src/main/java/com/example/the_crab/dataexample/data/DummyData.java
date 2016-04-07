package com.example.the_crab.dataexample.data;

/**
 * Created by the_crab on 7/04/16.
 */
public class DummyData {
    private String word, meaning;

    public DummyData(String word, String meaning){
        this.word = word;
        this.meaning=meaning;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }
}
