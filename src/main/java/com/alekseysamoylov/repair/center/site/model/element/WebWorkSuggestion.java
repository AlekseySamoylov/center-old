package com.alekseysamoylov.repair.center.site.model.element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by alekseysamoylov on 7/9/16.
 */
public class WebWorkSuggestion implements Comparable<WebWorkSuggestion>{
    private String workName;
    private List<String> suggestions = new ArrayList<>();

    public String getWorkName() {
        return workName;
    }

    public void setWorkName(String workName) {
        this.workName = workName;
    }

    public List<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(List<String> suggestions) {
        this.suggestions = suggestions;
    }

    @Override
    public int compareTo(WebWorkSuggestion o) {
        return workName.compareTo(o.getWorkName());
    }
}
