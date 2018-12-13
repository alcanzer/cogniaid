package com.cogniaid.cogniaid.Model;

import java.util.HashMap;

/**
 * Created by alcanzer on 12/5/18.
 */

public class QueryInput {
    private HashMap<String, String> text;

    public HashMap<String, String> getText() {
        return text;
    }

    public void setText(HashMap<String, String> text) {
        this.text = text;
    }
}
