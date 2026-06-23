package com.talentboozt.edu_service.domains.edu.acl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Standalone/no-op implementation of SsoGatewayPort.
 * Used when EDU service runs independently without the monolith.
 * Returns null for SSO lookups, allowing EDU to manage its own identity.
 */
@Component
@ConditionalOnProperty(name = "edu.sso.enabled", havingValue = "false", matchIfMissing = true)
public class StandaloneSsoGatewayAdapter implements SsoGatewayPort {

    private static final Logger log = LoggerFactory.getLogger(StandaloneSsoGatewayAdapter.class);

    @Override
    public SsoIdentity findOrCreateGlobalIdentity(String email, String firstName, String lastName, String encryptedPassword) {
        log.debug("Standalone mode: SSO identity creation skipped for {}", email);
        return null;
    }

    @Override
    public SsoIdentity findGlobalIdentityByEmail(String email) {
        log.debug("Standalone mode: SSO identity lookup skipped for {}", email);
        return null;
    }

    @Override
    public java.util.Optional<SsoIdentity> findById(String globalUserId) {
        log.debug("Standalone mode: SSO identity lookup by id skipped for {}", globalUserId);
        return java.util.Optional.empty();
    }

    @Override
    public void updatePassword(String globalUserId, String encryptedPassword) {
        log.debug("Standalone mode: password update skipped for user {}", globalUserId);
    }

    @Override
    public void syncRoles(String userId, List<String> roles) {
        log.debug("Standalone mode: role sync skipped for user {}", userId);
    }
}
