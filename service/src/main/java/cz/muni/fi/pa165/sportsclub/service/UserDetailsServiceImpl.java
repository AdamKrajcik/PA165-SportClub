package cz.muni.fi.pa165.sportsclub.service;

import cz.muni.fi.pa165.sportsclub.entity.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * @author martin
 */
@Component
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    UserService userService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        final User user = userService.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found.");
        }

        UserDetails userDetails = new UserDetails() {

            final GrantedAuthority ROLE_USER = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_USER";
                }
            };
            final GrantedAuthority ROLE_ADMIN = new GrantedAuthority() {
                @Override
                public String getAuthority() {
                    return "ROLE_ADMIN";
                }
            };

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
                grantedAuthorities.add(ROLE_USER);
                if (userService.isAdmin(user)) {
                    grantedAuthorities.add(ROLE_ADMIN);
                }
                return grantedAuthorities;
            }

            @Override
            public String getPassword() {
                return user.getPasswordHash();
            }

            @Override
            public String getUsername() {
                return user.getEmail();
            }

            @Override
            public boolean isAccountNonExpired() {
                return true;
            }

            @Override
            public boolean isAccountNonLocked() {
                return true;
            }

            @Override
            public boolean isCredentialsNonExpired() {
                return true;
            }

            @Override
            public boolean isEnabled() {
                return true;
            }
        };

        return userDetails;
    }
}
