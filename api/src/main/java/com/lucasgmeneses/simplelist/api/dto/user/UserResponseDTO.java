package com.lucasgmeneses.simplelist.api.dto.user;

import java.util.Date;

public record UserResponseDTO(String nickname, String email, Date dateUpdated) {
}
