package com.jjoaquin3.forohub.model;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@ToString
@Entity
@Table(name = "USER")
@EqualsAndHashCode(of = "id")
public class User implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "username", nullable = false, length = 128)
    private String username;

    @Column(name = "password", nullable = false, length = 128)
    private String password;

    @Column(name = "email", nullable = false, length = 128)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Topic> topics;

    @Override
    public String getUsername()
    {
        return this.username;
    }

    @Override
    public String getPassword()
    {
        return this.password;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }

    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired()
    {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled()
    {
        return true;
    }
}
