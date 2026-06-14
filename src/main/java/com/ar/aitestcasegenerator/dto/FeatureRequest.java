package com.ar.aitestcasegenerator.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class FeatureRequest {

    @NotBlank(message = "Feature description is required")
    @Size(
            min = 5,
            max = 2000,
            message = "Feature description must contain between 5 and 2000 characters")
    private String featureDescription;

    public FeatureRequest() {
    }

    public String getFeatureDescription() {
        return featureDescription;
    }

    public void setFeatureDescription(String featureDescription) {
        this.featureDescription = featureDescription;
    }
}
