package com.jjoaquin3.forohub.security;


import com.jjoaquin3.forohub.model.User;
import com.jjoaquin3.forohub.model.dto.UserRequestDTO;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController
{
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, TokenService tokenService)
    {
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    @PostMapping
    public ResponseEntity authenticateUser(@RequestBody @Valid UserRequestDTO userRequestDTO)
    {
        var authToken = new UsernamePasswordAuthenticationToken(userRequestDTO.getUsername(), userRequestDTO.getPassword());
        var authUser = authenticationManager.authenticate(authToken);
        var jwtToken = tokenService.getToken((User) authUser.getPrincipal());
        return ResponseEntity.ok(new JWTTokenDTO(jwtToken));
    }

}
