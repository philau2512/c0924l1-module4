package com.example.dictionary;

public class DictionaryService {
    public String search(String word) {
        String dictionary[][] = {
                {"hello", "xin chào"},
                {"how", "thế nào"},
                {"book", "quyển vở"},
                {"computer", "máy tính"},
                {"cat", "mèo"}
        };
        for (int i = 0; i < dictionary.length; i++) {
            if (dictionary[i][0].equals(word)) {
                return dictionary[i][1];
            }
        }
        return null;
    }
}
