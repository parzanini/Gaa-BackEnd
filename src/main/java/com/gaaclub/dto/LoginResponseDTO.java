package com.gaaclub.dto;

import com.gaaclub.model.Role;

public record LoginResponseDTO(String token, Role role, String name) {
}
