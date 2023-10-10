package com.company.jmixpm.security;

import io.jmix.security.role.annotation.ResourceRole;
import io.jmix.securityui.role.UiMinimalRole;

@ResourceRole(name = "Combined Manager Role", code = "combined-manager-role")
public interface CombineManagerRole extends ProjectManagmentRole, UiMinimalRole {
}
