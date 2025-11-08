package com.sabor.gourmet.service;

import com.sabor.gourmet.domain.Usuario;
import com.sabor.gourmet.repo.UsuarioRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JpaUserDetailsService implements UserDetailsService {

    private final UsuarioRepo usuarioRepo;

    public JpaUserDetailsService(UsuarioRepo usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + username));

        System.out.println("✅ Login encontrado: " + usuario.getUsername() + " - Rol: " + usuario.getRol());

        // 👇 Diagnóstico de password
        System.out.println("🔐 Hash almacenado en BD: " + usuario.getPassword());
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        boolean match = encoder.matches("admin123", usuario.getPassword());
        System.out.println("🔍 Verificación directa con 'admin123': " + match);

        List<GrantedAuthority> authorities =
                List.of(new SimpleGrantedAuthority(usuario.getRol().name()));

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword()) // 🔥 usa el hash directamente
                .authorities(authorities)
                .disabled(!usuario.isActivo())
                .build();
    }
}
