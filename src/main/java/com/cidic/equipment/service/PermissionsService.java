package com.cidic.equipment.service;

import com.cidic.equipment.model.Permission;

public interface PermissionsService {

	public int createPermission(Permission permission);

	public int deletePermission(Long permissionId);
}
