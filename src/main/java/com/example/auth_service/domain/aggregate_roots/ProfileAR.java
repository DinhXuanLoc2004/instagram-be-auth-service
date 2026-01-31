package com.example.auth_service.domain.aggregate_roots;

import java.time.LocalDate;
import java.util.UUID;

public class ProfileAR {
    private Long id;
    private final String username;
    private final String displayName;
    private final String avatarURL;
    private LocalDate birthday;
    private final UUID accountId;
    private boolean isBlueCheckMark;

    private ProfileAR(Long id, String username, String displayName, String avatarURL, LocalDate birthday,
            UUID accointId, boolean isBlueCheckMark) {
        this.id = id;
        this.username = username;
        this.displayName = displayName;
        this.avatarURL = avatarURL;
        this.birthday = birthday;
        this.accountId = accointId;
        this.isBlueCheckMark = isBlueCheckMark;
    }

    private ProfileAR(String username, String displayName, String avatarURL, LocalDate birthday, UUID accountId) {
        this.username = username;
        this.displayName = displayName;
        this.avatarURL = avatarURL;
        this.birthday = birthday;
        this.accountId = accountId;
    }

    public static ProfileAR create(String username, String displayName, String avatarURL, LocalDate birthday,
            UUID accountId) {
        return new ProfileAR(username, displayName, avatarURL, birthday, accountId);
    }

    public static ProfileAR create(String email, UUID accountId) {
        String username = email.substring(0, email.indexOf("@"));
        String defaultAvatar = "https://i.pinimg.com/236x/5e/e0/82/5ee082781b8c41406a2a50a0f32d6aa6.jpg";
        return new ProfileAR(username, username, defaultAvatar, null, accountId);
    }

    public static ProfileAR toAggregate(Long id, String username, String displayName, String avatarURL,
            LocalDate birthday, UUID accountId, boolean isBlueCheckMark) {
        return new ProfileAR(id, username, displayName, avatarURL, birthday, accountId, isBlueCheckMark);
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public UUID getAccountId() {
        return accountId;
    }

    public void grantBlueCheckMark() {
        this.isBlueCheckMark = true;
    }

    public void revokeBlueCheckMark() {
        this.isBlueCheckMark = false;
    }

    public boolean getIsBlueCheckMark() {
        return isBlueCheckMark;
    }
}
