package com.lucasgmeneses.simplelist.api.dto.task.tasklist;

import java.util.Date;

public record TaskListResponseDTO(String id, String title, String color, Date dateUpdated, Date created) {
}
