package com.lucasgmeneses.simplelist.api.dto.task;

import java.util.Date;

public record TaskResponseDTO(String id,
                              long position,
                              String description,
                              boolean checked,
                              String taskListId,
                              Date dateCreated) {
}
