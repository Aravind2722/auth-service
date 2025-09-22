package org.ecommerce.authservice.controllers;


import org.ecommerce.authservice.dtos.requests.LogInRequestDto;
import org.ecommerce.authservice.dtos.requests.SignUpRequestDto;
import org.ecommerce.authservice.dtos.responses.BaseResponse;
import org.ecommerce.authservice.dtos.responses.LogInResponseDto;
import org.ecommerce.authservice.dtos.responses.SignUpResponseDto;
import org.ecommerce.authservice.exceptions.UserAlreadyExistsException;
import org.ecommerce.authservice.exceptions.UserNotFoundException;
import org.ecommerce.authservice.exceptions.WrongPasswordException;
import org.ecommerce.authservice.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    @Autowired
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/sign-up")
    public ResponseEntity<BaseResponse<SignUpResponseDto>> signUp(@RequestBody SignUpRequestDto request) throws UserAlreadyExistsException {
        SignUpResponseDto response = new SignUpResponseDto();
        boolean signUpSuccess = authService.signUp(request.getName(), request.getEmail(), request.getPassword());
        if (signUpSuccess) {
            response.setSignUpSuccess(signUpSuccess);
            return new ResponseEntity<>(
                    BaseResponse.success(response, "Sign up successful."),
                    HttpStatus.CREATED
            );
        }
//            if (authService.signUp(request.getEmail(), request.getPassword())) {
//                response.setRequestStatus(RequestStatus.SUCCESS);
//            } else {
//                response.setRequestStatus(RequestStatus.FAILURE);
//            }
//
//            return new ResponseEntity<>(response, HttpStatus.OK);
//        } catch (Exception e) {
//            response.setRequestStatus(RequestStatus.FAILURE);
//            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//        }
        return new ResponseEntity<>(
                BaseResponse.failure(response, "Something went wrong. Sign up failed."),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LogInResponseDto>> login(@RequestBody LogInRequestDto request) throws UserNotFoundException, WrongPasswordException {
        LogInResponseDto response = new LogInResponseDto();
        String token = authService.login(request.getEmail(), request.getPassword());
        response.setToken(token);

//        MultiValueMap<String, Object> headers = new LinkedMultiValueMap<>();
//        headers.add("AUTH_TOKEN", token);

        return ResponseEntity
                .ok()
                .header("AUTH_TOKEN", token)
                .body(BaseResponse.success(response, "Log in successful.")
        );
//        try {
//            String token = authService.login(request.getEmail(), request.getPassword());
//            LogInResponseDto loginDto = new LogInResponseDto();
//            loginDto.setRequestStatus(RequestStatus.SUCCESS);
//            MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
//            headers.add("AUTH_TOKEN", token);
//
//            ResponseEntity<LogInResponseDto> response = new ResponseEntity<>(
//                    loginDto, headers , HttpStatus.OK
//            );
//            return response;
//        } catch (Exception e) {
//            LogInResponseDto loginDto = new LogInResponseDto();
//            loginDto.setRequestStatus(RequestStatus.FAILURE);
//            ResponseEntity<LogInResponseDto> response = new ResponseEntity<>(
//                    loginDto, null , HttpStatus.BAD_REQUEST
//            );
//            return response;
//        }
    }

    @GetMapping("/validate")
    public boolean validate(@RequestParam("token") String token) {
        return authService.validate(token);
    }

}