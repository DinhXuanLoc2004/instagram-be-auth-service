package com.example.auth_service.application.usecases;

import com.example.auth_service.application.interface_repositories.commands.IARAccountCommandRepositoty;
import com.example.auth_service.application.interface_repositories.commands.IEOTPCommandRepository;
import com.example.auth_service.application.ports.inputs.SignUpInput;
import com.example.auth_service.application.service_for_usecase.IOTPService;
import com.example.auth_service.application.service_for_usecase.IPasswordService;
import com.example.auth_service.core.exceptions.specific_case.EmailAlreadyExistsException;
import com.example.auth_service.domain.aggregate_roots.ARAccount;
import com.example.auth_service.domain.entities.EOTP;
import com.example.auth_service.domain.entities.entity_auth_provider.extensions.UserPassAuthProvider;
import com.example.auth_service.domain.value_objects.VOEmail;

public class SignUpUsecase {
    private final IARAccountCommandRepositoty commandRepositoty;
    private final IEOTPCommandRepository otpCommandRepository;
    private final IPasswordService passwordService;
    private final IOTPService otpService;

    public SignUpUsecase(IARAccountCommandRepositoty commandRepositoty, IEOTPCommandRepository otpCommandRepository,
            IPasswordService passwordService, IOTPService emailService) {
        this.commandRepositoty = commandRepositoty;
        this.passwordService = passwordService;
        this.otpCommandRepository = otpCommandRepository;
        this.otpService = emailService;
    }

    public void execute(SignUpInput input) {
        VOEmail voEmail = VOEmail.create(input.getEmail());

        boolean existsEmail = commandRepositoty.existsEmail(voEmail);

        if (existsEmail)
            throw new EmailAlreadyExistsException();

        String passwordHashed = passwordService.hash(input.getPassword());

        UserPassAuthProvider userPassAuthProvider = new UserPassAuthProvider(passwordHashed);

        ARAccount account = ARAccount.create(input.getEmail(), userPassAuthProvider);

        commandRepositoty.save(account);

        String code = otpService.generateCode();

        otpService.send(code, account.getEmail().getValue());

        String codeHashed = otpService.hash(code);

        EOTP otp = EOTP.create(account.getId(), codeHashed);

        otpCommandRepository.save(otp);

    }
}
