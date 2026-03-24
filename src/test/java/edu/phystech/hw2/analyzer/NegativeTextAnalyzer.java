package edu.phystech.hw2.analyzer;

import java.util.List;

public class NegativeTextAnalyzer extends KeywordAnalyzer {
    @SuppressWarnings("unused")
    private static final List<String> NEGATIVE_SMILES = List.of(":(", "=(", ":|");
    private final List<String> keywords;

    public NegativeTextAnalyzer(List<String> keywords) {
        super(keywords, Label.NEGATIVE);
        this.keywords = keywords;
    }

    @Override
    protected String[] getKeywords() {
        return keywords.toArray(new String[0]);
    }

    @Override
    protected Label getLabel() {
        return Label.NEGATIVE;
    }
}

