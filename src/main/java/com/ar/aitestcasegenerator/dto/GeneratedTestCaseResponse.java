package com.ar.aitestcasegenerator.dto;

import java.util.List;

public class GeneratedTestCaseResponse {

    private final String featureName;
    private final int totalGenerated;
    private final List<GeneratedTestCaseItemResponse> testCases;

    public GeneratedTestCaseResponse(
            String featureName,
            List<GeneratedTestCaseItemResponse> testCases) {
        this.featureName = featureName;
        this.totalGenerated = testCases.size();
        this.testCases = testCases;
    }

    public String getFeatureName() {
        return featureName;
    }

    public int getTotalGenerated() {
        return totalGenerated;
    }

    public List<GeneratedTestCaseItemResponse> getTestCases() {
        return testCases;
    }
}
