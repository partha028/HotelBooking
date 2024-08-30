package com.projects.lakeSide_hotel.serviceImpl;

import com.projects.lakeSide_hotel.model.UserDetails;
import com.projects.lakeSide_hotel.repository.AdminRepository;
import com.projects.lakeSide_hotel.service.IAdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdminImpl implements IAdminService{

    private final AdminRepository adminRepository;
    @Override
    public boolean validateUserLogin(UserDetails userDetails) {
        Optional<UserDetails> user = adminRepository.findByUsernameAndPassword(userDetails.getUserName(), userDetails.getPassword());
        return user.isPresent();
    }

    @Override
    public boolean saveUser(UserDetails userDetails) {
        Optional<String> userExists = adminRepository.findUserName(userDetails.getUserName());
        if(userExists.isEmpty()){
            adminRepository.save(userDetails);
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public Optional<UserDetails> getUserDetails() {
        return adminRepository.getUserDetails();
    }

    @Override
    public String resetPassword(UserDetails userDetails) {
        Optional<String> userExists = adminRepository.findUserName(userDetails.getUserName());
        {
            if(userExists.isPresent()){
                Optional<UserDetails> user = adminRepository.resetPassword(userDetails.getUserName(), userDetails.getSecurityQuestion(), userDetails.getSecurityAnswer());
                if(user.isPresent()){
                    adminRepository.updatePassword(userDetails.getUserName(), userDetails.getPassword());
                    return "updated";
                }
                else{
                    return "not updated";
                }
            }
            else{
                return "user not exists";
            }
        }
    }
}
