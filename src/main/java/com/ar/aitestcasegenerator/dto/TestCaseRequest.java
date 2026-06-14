package com.ar.aitestcasegenerator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class TestCaseRequest {

    @NotBlank(message = "Feature name is required")
    @Size(max = 150, message = "Feature name must not exceed 150 characters")
    private String featureName;

    @NotBlank(message = "Test scenario is required")
    @Size(max = 500, message = "Test scenario must not exceed 500 characters")
    private String testScenario;

    @NotBlank(message = "Test steps are required")
    @Size(max = 5000, message = "Test steps must not exceed 5000 characters")
    private String testSteps;

    @NotBlank(message = "Expected result is required")
    @Size(max = 2000, message = "Expected result must not exceed 2000 characters")
    private String expectedResult;

    public String getFeatureName() {
        return featureName;
    }

    public void setFeatureName(String featureName) {
        this.featureName = featureName;
    }

    public String getTestScenario() {
        return testScenario;
    }

    public void setTestScenario(String testScenario) {
        this.testScenario = testScenario;
    }

    public String getTestSteps() {
        return testSteps;
    }

    public void setTestSteps(String testSteps) {
        this.testSteps = testSteps;
    }

    public String getExpectedResult() {
        return expectedResult;
    }

    public void setExpectedResult(String expectedResult) {
        this.expectedResult = expectedResult;
    }
}
