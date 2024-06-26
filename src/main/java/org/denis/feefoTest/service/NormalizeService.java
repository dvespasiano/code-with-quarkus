package org.denis.feefoTest.service;

import jakarta.enterprise.context.ApplicationScoped;
import org.apache.commons.text.similarity.LevenshteinDistance;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@ApplicationScoped
public class NormalizeService {

    private static final Map<String, String> normalizedJobTitles = Map.of(
            "engineer", "Software engineer",
            "architect", "Architect",
            "surveyor", "Quantity surveyor",
            "accountant", "Accountant");

    public String normalizingJobTitles(String jobTitle) {
        AtomicReference<String> bestMatch = new AtomicReference<>(jobTitle);
        if (!Objects.isNull(jobTitle)) {
            LevenshteinDistance levenshteinDistance = new LevenshteinDistance();

            AtomicReference<Double> highestQualityScore = new AtomicReference<>(0.5);

            normalizedJobTitles
                    .keySet()
                    .forEach(key -> {
                        int distance = levenshteinDistance.apply(jobTitle, key);
                        int maxLength = Math.max(jobTitle.length(), key.length());
                        double qualityScore = 1.0 - (double) distance / maxLength;

                        if (qualityScore > highestQualityScore.get()) {
                            highestQualityScore.set(qualityScore);
                            bestMatch.set(key);
                        }
                    });
            return normalizedJobTitles.getOrDefault(bestMatch.get(), jobTitle);
        }
        return bestMatch.get();
    }
}
