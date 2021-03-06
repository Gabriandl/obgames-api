package com.obgames.obgamesapi.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javax.validation.Valid;

import com.obgames.obgamesapi.dto.request.LoginRequest;
import com.obgames.obgamesapi.dto.request.ResetPassword;
import com.obgames.obgamesapi.dto.request.SignupRequest;
import com.obgames.obgamesapi.dto.response.JwtResponse;
import com.obgames.obgamesapi.dto.response.MessageResponse;
import com.obgames.obgamesapi.exceptions.ResourceNotFoundException;
import com.obgames.obgamesapi.model.EnumRole;
import com.obgames.obgamesapi.model.Role;
import com.obgames.obgamesapi.model.Usuario;
import com.obgames.obgamesapi.repository.RoleRepo;
import com.obgames.obgamesapi.security.jwt.JwtUtils;
import com.obgames.obgamesapi.security.service.UserDetailsImpl;
import com.obgames.obgamesapi.service.UsuarioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
    UsuarioService usuarioService;
	@Autowired
	RoleRepo roleRepo;
	@Autowired
	PasswordEncoder encoder;
	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());
		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				// userDetails.getEmail(),
				roles));
	}

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (usuarioService.verifyUsuarioExistsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}
		Usuario user = new Usuario(
				signUpRequest.getNomeCompleto(),
				signUpRequest.getUsername(),
				signUpRequest.getDataNasc(),
				signUpRequest.getEstado(),
				signUpRequest.getPais(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRoles();
		Set<Role> roles = new HashSet<>();
		if (strRoles == null) {
			Role userRole = roleRepo.findByNome(EnumRole.ROLE_MEMBRO)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepo.findByNome(EnumRole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					default:
						Role userRole = roleRepo.findByNome(EnumRole.ROLE_MEMBRO)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}
		user.setRoles(roles);
		usuarioService.createUsuario(user);
		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<?> resetPassword(@Valid @RequestBody ResetPassword resetPasswordequest) throws ResourceNotFoundException {
		
		Usuario user = usuarioService.getUsuarioById(resetPasswordequest.getIdUsuario());
		System.out.println(user.getSenha());
		user.setSenha(encoder.encode(resetPasswordequest.getNewPassword()));
		System.out.println(user.getSenha());
		usuarioService.updateUsuario(user, resetPasswordequest.getIdUsuario());
		return ResponseEntity.ok(new MessageResponse("Password updated successfully!"));
	}
}