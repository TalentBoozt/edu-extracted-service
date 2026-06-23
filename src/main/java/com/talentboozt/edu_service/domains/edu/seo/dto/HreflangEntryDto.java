package com.talentboozt.edu_service.domains.edu.seo.dto;

/**
 * Immutable Data Transfer Object mapping alternate language links for SEO response headers.
 */
public record HreflangEntryDto(
    String lang,
    String href
) {}
