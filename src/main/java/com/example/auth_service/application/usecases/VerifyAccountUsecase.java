package com.example.auth_service.application.usecases;

import java.util.Optional;

import com.example.auth_service.application.interface_repositories.commands.IEOTPCommandRepository;
import com.example.auth_service.application.interface_repositories.queries.IEOTPQueryRepository;
import com.example.auth_service.application.interface_repositories.queries.views.VOTP;
import com.example.auth_service.application.ports.inputs.VerifyAccountInput;
import com.example.auth_service.application.ports.outputs.LoginOutput;
import com.example.auth_service.application.service_for_usecase.IOTPService;
import com.example.auth_service.application.service_for_usecase.ITokenService;
import com.example.auth_service.core.exceptions.specific_case.OTPExpiredException;
import com.example.auth_service.core.exceptions.specific_case.OTPInvalidException;

public class VerifyAccountUsecase {
    private final IEOTPQueryRepository otpQueryRepository;
    private final IEOTPCommandRepository otpCommandRepository;
    private final IOTPService otpService;
    private final ITokenService tokenService;

    public VerifyAccountUsecase(IOTPService otpService, IEOTPCommandRepository otpCommandRepository,
            IEOTPQueryRepository otpQueryRepository, ITokenService tokenService,
            IEOTPCommandRepository commandRepository) {
        this.otpService = otpService;
        this.otpQueryRepository = otpQueryRepository;
        this.tokenService = tokenService;
        this.otpCommandRepository = otpCommandRepository;
    }

    public LoginOutput execute(VerifyAccountInput input) {
        Optional<VOTP> otp = otpQueryRepository.findByUserId(input.getAccountId().toString());

        if (otp.isEmpty())
            throw new OTPExpiredException();

        if (!otpService.verify(input.getCode(), otp.get().getCodeHashed()))
            throw new OTPInvalidException();

        otpCommandRepository.delete(input.getAccountId());

        return tokenService.generateToken(input.getAccountId().toString());
    }
}
