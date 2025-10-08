package com.daoqonq1408.workshopbesql.dto.request;

import com.daoqonq1408.workshopbesql.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateUserRequestDTO {
    private String username;
    private String password;
    private Role role;
}
