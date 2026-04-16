package edu.phystech.hw2.analyzer;

import java.util.List;

public abstract class KeywordAnalyzer implements TextAnalyzer {

    @SuppressWarnings("unused")
    private final List<String> keywords;
    @SuppressWarnings("unused")
    private final Label label;

    public KeywordAnalyzer(List<String> keywords, Label label) {
        this.keywords = keywords;
        this.label = label;
    }

    @Override
    public Label processText(String text) {
        String[] keywords = getKeywords();
        String lowerText = " " + text.toLowerCase() + " ";
        for (String keyword : keywords) {
            String pattern = " " + keyword.toLowerCase() + " ";
            if (lowerText.contains(pattern)) {
                return getLabel();
            }
        }
        return Label.OK;
    }

    protected String[] getKeywords() {
        throw new UnsupportedOperationException("Unimplemented method 'getKeywords'");
    }

    protected Label getLabel() {
        throw new UnsupportedOperationException("Unimplemented method 'getLabel'");
    }
}
