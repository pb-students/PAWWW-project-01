package net.wvffle.java.pawww.pawwwproject.security;

import net.wvffle.java.pawww.pawwwproject.db.user.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Locale;

public record UserDetailsImpl(User user) implements org.springframework.security.core.userdetails.UserDetails {

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();

        String role = user.getRole();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + role.toUpperCase(Locale.ROOT)));

        if (role.equals("admin")) {
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        if (role.equals("admin") || role.equals("user")) {
            authorities.add(new SimpleGrantedAuthority("PUBLISH_INFORMATION"));
            authorities.add(new SimpleGrantedAuthority("ADD_CATEGORY"));
        }

        return authorities;
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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
}
