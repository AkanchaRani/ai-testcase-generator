package com.ar.aitestcasegenerator.service;

import com.ar.aitestcasegenerator.dto.GeneratedTestCaseResponse;
import com.ar.aitestcasegenerator.entity.TestCase;
import com.ar.aitestcasegenerator.enums.Severity;
import com.ar.aitestcasegenerator.enums.TestCaseStatus;
import com.ar.aitestcasegenerator.enums.TestType;
import com.ar.aitestcasegenerator.repository.TestCaseRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AIServiceTest {

    @Mock
    private TestCaseRepository testCaseRepository;

    @InjectMocks
    private AIService aiService;

    @Test
    void shouldGenerateAndSaveCategorizedTestCases() {
        when(testCaseRepository.saveAll(anyList())).thenAnswer(invocation -> {
            List<TestCase> testCases = invocation.getArgument(0);
            for (int index = 0; index < testCases.size(); index++) {
                testCases.get(index).setId((long) index + 1);
            }
            return testCases;
        });

        GeneratedTestCaseResponse response = aiService.generateTestCases(" Login Page ");

        ArgumentCaptor<List<TestCase>> captor = ArgumentCaptor.forClass(List.class);
        verify(testCaseRepository).saveAll(captor.capture());
        List<TestCase> savedTestCases = captor.getValue();

        assertThat(response.getFeatureName()).isEqualTo("Login Page");
        assertThat(response.getTotalGenerated()).isEqualTo(7);
        assertThat(response.getTestCases()).hasSize(7);
        assertThat(savedTestCases).allMatch(testCase ->
                testCase.getStatus() == TestCaseStatus.OPEN);
        assertThat(savedTestCases)
                .extracting(TestCase::getTestType)
                .containsExactlyInAnyOrderElementsOf(EnumSet.allOf(TestType.class));
        assertThat(savedTestCases)
                .filteredOn(testCase -> testCase.getTestType() == TestType.SECURITY)
                .singleElement()
                .extracting(TestCase::getSeverity)
                .isEqualTo(Severity.CRITICAL);
    }
}
