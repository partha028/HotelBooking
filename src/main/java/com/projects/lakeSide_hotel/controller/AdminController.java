package com.projects.lakeSide_hotel.controller;

import com.projects.lakeSide_hotel.model.UserDetails;
import com.projects.lakeSide_hotel.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:5173")
public class AdminController {

    private final IAdminService adminService;

    @PostMapping("/login")
    public ResponseEntity<String> validateUserLogin(@RequestBody UserDetails userDetails) {
        boolean userValidated = adminService.validateUserLogin(userDetails);
        if (userValidated) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
        }
    }

    @PostMapping("/sign-up")
    public ResponseEntity<String> createUserAccount(@RequestBody UserDetails userDetails) {
        boolean saveUser = adminService.saveUser(userDetails);
        if (saveUser) {
            return ResponseEntity.ok("User has been created");
        }
        else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User Already Exists");
        }
    }

    @GetMapping("/user-profile")
    public ResponseEntity<?> getUserDetails() {
        Optional<UserDetails> userDetails = adminService.getUserDetails();
        if (userDetails.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(userDetails);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Profile not found");
        }
    }

    @PutMapping("/forgot-password")
    public ResponseEntity<String> resetPassword(@RequestBody UserDetails userDetails) {
        String saveUser = adminService.resetPassword(userDetails);
        if (saveUser.equalsIgnoreCase("updated")) {
            return ResponseEntity.ok("Password has been updated");
        } else if (saveUser.equalsIgnoreCase("user not exists")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not exists");
        } else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error fetching user details");
        }
    }
}
