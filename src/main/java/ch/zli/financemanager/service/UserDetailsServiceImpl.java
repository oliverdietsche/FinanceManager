package ch.zli.financemanager.service;

import ch.zli.financemanager.entity.Role;
import ch.zli.financemanager.entity.User;
import ch.zli.financemanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Service("CustomUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String name)
            throws UsernameNotFoundException {
        try {
            User user = userRepository.findByName(name);

            List<GrantedAuthority> authorities = new ArrayList<>();
            if (user.hasRoles()) {
                for (Role role : user.getRoles()) {
                    authorities.add(new SimpleGrantedAuthority(role.getName()));
                }
            }

            return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), authorities);
        } catch (NullPointerException e) {
            throw new UsernameNotFoundException(e.getMessage());
        }
    }
}