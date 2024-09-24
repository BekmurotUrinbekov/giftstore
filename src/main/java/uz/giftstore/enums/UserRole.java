package uz.giftstore.enums;

import com.google.common.collect.Sets;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Set;
import java.util.stream.Collectors;

import static uz.giftstore.enums.UserPermission.*;


public enum UserRole {
    USER_ROLE(Sets.newHashSet(SHOW)),
    ADMIN(Sets.newHashSet(MONITORING, SHOW)),
    CEO(Sets.newHashSet(SHOW, MONITORING, CONTROL));

    private final Set<UserPermission> permissions;

    UserRole(Set<UserPermission> permissions) {
        this.permissions = permissions;
    }

    public Set<UserPermission> getPermissions() {
        return permissions;
    }

    public Set<SimpleGrantedAuthority> getGrantedAuthority() {
        Set<SimpleGrantedAuthority> collect = getPermissions().stream().map(e -> new SimpleGrantedAuthority(e.getPermission())).collect(Collectors.toSet());
        collect.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return collect;
    }
}
