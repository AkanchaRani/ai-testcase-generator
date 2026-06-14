package com.ar.aitestcasegenerator.controller;

import com.ar.aitestcasegenerator.dto.ApiResponse;
import com.ar.aitestcasegenerator.dto.FeatureRequest;
import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import com.ar.aitestcasegenerator.service.AIService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ai")
public class AIController {

    private final AIService aiService;

    public AIController(AIService aiService) {
        this.aiService = aiService;
    }

    @PostMapping("/generate")
    public ResponseEntity<ApiResponse<GeneratedTestCaseResponse>> generateTestCases(
            @Valid @RequestBody FeatureRequest request) {
        GeneratedTestCaseResponse generatedTestCase =
                aiService.generateTestCases(request.getFeatureDescription());
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        HttpStatus.CREATED.value(),
                        "Test cases generated and saved successfully",
                        generatedTestCase));
    }
}
