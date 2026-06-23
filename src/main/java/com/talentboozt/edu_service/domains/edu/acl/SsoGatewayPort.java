package com.talentboozt.edu_service.domains.edu.acl;

/**
 * Anti-Corruption Layer port for SSO (Single Sign-On) integration.
 * Decouples the EDU domain from the monolith's CredentialsService.
 * 
 * In standalone mode, the no-op adapter is used.
 * In integrated mode, the REST adapter calls the monolith's API.
 */
public interface SsoGatewayPort {

    /**
     * Finds or creates a global SSO identity for the given email.
     * Returns the global user ID that should be used as EDU user ID.
     */
    SsoIdentity findOrCreateGlobalIdentity(String email, String firstName, String lastName, String encryptedPassword);

    /**
     * Finds a global SSO identity by email.
     * Returns null if no identity exists.
     */
    SsoIdentity findGlobalIdentityByEmail(String email);

    /**
     * Syncs EDU roles to the global SSO identity.
     */
    void syncRoles(String userId, java.util.List<String> roles);

    /**
     * Finds a global SSO identity by its unique ID.
     */
    java.util.Optional<SsoIdentity> findById(String globalUserId);

    /**
     * Updates the password of a global SSO identity.
     */
    void updatePassword(String globalUserId, String encryptedPassword);

    /**
     * Represents a global SSO identity.
     */
    record SsoIdentity(String globalUserId, String email, String firstName, String lastName, String encryptedPassword) {
    }
}
