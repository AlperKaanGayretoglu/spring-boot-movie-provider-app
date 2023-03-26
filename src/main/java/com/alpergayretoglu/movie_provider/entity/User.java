package com.alpergayretoglu.movie_provider.entity;

import com.alpergayretoglu.movie_provider.entity.enums.UserRole;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.ZonedDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = "email")

})
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String surname;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    @Column(nullable = false)
    private UserRole role = UserRole.GUEST;

    @Builder.Default
    private boolean verified = false;

    private String verificationCode;
    private ZonedDateTime verificationCodeExpiredDate;

    private String recoveryCode;
    private ZonedDateTime recoveryCodeExpiredDate;


    // TODO: Do we need to specify with which table ???
    @OneToMany
    private Set<ContractRecord> subscription = new HashSet<>();

    // TODO: Do we need to specify with which table ???
    @ManyToMany
    private Set<Category> followedCategories = new HashSet<>();

    // TODO: Do we need to specify with which table ???
    @ManyToMany
    private Set<Movie> favoriteMovies = new HashSet<>();


    // security layer
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getUsername() {
        return email;
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