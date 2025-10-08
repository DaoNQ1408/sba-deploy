package com.daoqonq1408.workshopbesql.controller;

import com.daoqonq1408.workshopbesql.dto.response.ApiResponseDTO;
import com.daoqonq1408.workshopbesql.dto.response.CreateRoleRequestDTO;
import com.daoqonq1408.workshopbesql.model.Role;
import com.daoqonq1408.workshopbesql.service.RoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin
@Tag(name = "Role Management", description = "APIs for managing roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Operation(summary = "Get all roles", description = "Retrieves a list of all roles")
    @GetMapping("/")
    @PreAuthorize("hasRole('USER') or hasRole('ADMIN')")
    public ResponseEntity<ApiResponseDTO<List<Role>>> getRoles() {
        return ResponseEntity.ok(
                ApiResponseDTO.success("200","Roles retrieved successfully", roleService.getRoles())
        );
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(@PathVariable int roleId) {
        return ResponseEntity.ok(roleService.getRoleById(roleId));
    }

    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Role> createRole(@RequestBody CreateRoleRequestDTO role) {
        return ResponseEntity.ok(roleService.addRole(role));
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Void> deleteRole(@PathVariable int roleId) {
        roleService.deleteRole(roleId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/")
    public ResponseEntity<Role> updateRole(@RequestBody Role role) {
        return ResponseEntity.ok(roleService.updateRole(role));
    }
}
