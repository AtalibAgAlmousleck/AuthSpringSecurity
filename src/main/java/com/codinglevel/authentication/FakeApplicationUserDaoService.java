package com.codinglevel.authentication;

import com.codinglevel.security.ApplicationUserRole;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public FakeApplicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return getApplicationUsers()
                .stream().filter(
                        applicationUser -> username.equals(
                                applicationUser.getUsername()
                        )
                ).findFirst();
    }

    private List<ApplicationUser> getApplicationUsers() {
        List<ApplicationUser> applicationUsers = Lists.newArrayList(
                new ApplicationUser(
                        "Hamdi",
                        passwordEncoder.encode("1234"),
                        ApplicationUserRole.STUDENT.getSimpleGrantedAuthorities(),
                        true, true,
                        true,true
                ),
                new ApplicationUser(
                        "Mohamed",
                        passwordEncoder.encode("password"),
                        ApplicationUserRole.ADMIN.getSimpleGrantedAuthorities(),
                        true, true,
                        true,true
                ),
                new ApplicationUser(
                        "Mark",
                        passwordEncoder.encode("password"),
                        ApplicationUserRole.ADMINTRAINEE.getSimpleGrantedAuthorities(),
                        true, true,
                        true,true
                )
        );

        return applicationUsers;
    }
}
