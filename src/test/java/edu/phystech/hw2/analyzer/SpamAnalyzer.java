package edu.phystech.hw2.analyzer;

import java.util.Arrays;
import java.util.List;

public class SpamAnalyzer extends KeywordAnalyzer {
    private final List<String> keywords;

    public SpamAnalyzer(List<String> keywords) {
        super(keywords, Label.SPAM);
        this.keywords = keywords;
    }

    public SpamAnalyzer(String[] keywords) {
        super(Arrays.asList(keywords), Label.SPAM);
        this.keywords = Arrays.asList(keywords);
    }

    @Override
    protected String[] getKeywords() {
        return keywords.toArray(new String[0]);
    }

    @Override
    protected Label getLabel() {
        return Label.SPAM;
    }
}