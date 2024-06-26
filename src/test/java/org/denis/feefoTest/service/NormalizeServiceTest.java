package org.denis.feefoTest.service;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@QuarkusTest
public class NormalizeServiceTest {

    @Inject
    NormalizeService normalizeService;

    @Test
    public void testExactMatch() {
        String input = "engineer";
        String expected = "Software engineer";
        assertEquals(expected, normalizeService.normalizingJobTitles(input));
    }

    @Test
    public void testPartialMatch() {
        String input = "enginer";
        String expected = "Software engineer";
        assertEquals(expected, normalizeService.normalizingJobTitles(input));
    }

    @Test
    public void testNoMatch() {
        String input = "doctor";
        String expected = "doctor";
        assertEquals(expected, normalizeService.normalizingJobTitles(input));
    }

    @Test
    public void testExtraCharacters() {
        String input = "chief accountant";
        String expected = "Accountant";
        assertEquals(expected, normalizeService.normalizingJobTitles(input));
    }

    @Test
    public void testNullInput() {
        assertNull(normalizeService.normalizingJobTitles(null));
    }
}
