package com.skyworth.mapper;

import com.skyworth.model.Permission;
import org.springframework.stereotype.Repository;

@Repository
public interface PermissionMapper {
    void save(Permission permission);

    void update(Permission permission);

    Permission findPermission(String perName);
}
