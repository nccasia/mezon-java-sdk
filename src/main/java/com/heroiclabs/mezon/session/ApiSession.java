package com.heroiclabs.mezon.session;

import lombok.Data;

@Data
public class ApiSession {
    // True if the corresponding account was just created, false otherwise.
    private Boolean created;

    // Refresh token that can be used for session token renewal.
    private String refreshToken;

    // Authentication credentials.
    private String token;

    // UserId
    private String userId;
}
