package com.example.auth_service.application.interface_repositories.queries.views;

public class VOTP {
    private final String accountId;
    private final String codeHashed;

    public VOTP(String accountId, String codeHashed){
        this.accountId = accountId;
        this.codeHashed = codeHashed;
    }

    public String getAccountId() {
        return accountId;
    }

    public String getCodeHashed() {
        return codeHashed;
    }
}
