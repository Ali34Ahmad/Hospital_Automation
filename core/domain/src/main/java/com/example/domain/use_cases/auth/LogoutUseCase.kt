package com.example.domain.use_cases.auth

import com.example.domain.repositories.AuthRepository
import com.example.model.auth.logout.LogoutResponse
import com.example.utility.network.Result
import com.example.utility.network.rootError

class LogoutUseCase(
    private val authRepository: AuthRepository
) {
    suspend operator fun invoke(): Result<LogoutResponse, rootError> {
        // This might also involve clearing local session data, tokens, etc.
        // That logic could either be here or orchestrated by the ViewModel after
        // a successful logout from the repository. For simplicity, we directly call repo here.
        val result = authRepository.logout()
        // if (result is Result.Success) {
        //     // Perform local data cleanup if the use case is responsible for it
        //     // localUserSessionManager.clearSession()
        // }
        return result
    }
}