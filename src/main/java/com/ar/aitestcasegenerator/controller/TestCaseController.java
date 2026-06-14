package com.ar.aitestcasegenerator.controller;

import com.ar.aitestcasegenerator.dto.ApiResponse;
import com.ar.aitestcasegenerator.dto.TestCaseRequest;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.service.TestCaseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/testcases")
public class TestCaseController {

    private final TestCaseService testCaseService;

    public TestCaseController(TestCaseService testCaseService) {
        this.testCaseService = testCaseService;
    }

    @PostMapping
    public ResponseEntity<ApiResponse<TestCase>> saveTestCase(
            @Valid @RequestBody TestCaseRequest request) {
        TestCase savedTestCase = testCaseService.saveTestCase(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(
                ApiResponse.success(
                        HttpStatus.CREATED.value(),
                        "Test case created successfully",
                        savedTestCase));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<TestCase>>> getAllTestCases() {
        List<TestCase> testCases = testCaseService.getAllTestCases();
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Test cases retrieved successfully",
                        testCases));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<TestCase>> getTestCaseById(@PathVariable Long id) {
        TestCase testCase = testCaseService.getTestCaseById(id);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Test case retrieved successfully",
                        testCase));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteTestCase(@PathVariable Long id) {
        testCaseService.deleteTestCase(id);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Test case deleted successfully",
                        null));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<TestCase>> updateTestCase(
            @PathVariable Long id,
            @Valid @RequestBody TestCaseRequest request) {
        TestCase updatedTestCase = testCaseService.updateTestCase(id, request);
        return ResponseEntity.ok(
                ApiResponse.success(
                        HttpStatus.OK.value(),
                        "Test case updated successfully",
                        updatedTestCase));
    }
}
