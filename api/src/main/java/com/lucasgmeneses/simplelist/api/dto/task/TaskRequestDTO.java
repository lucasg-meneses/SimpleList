package com.lucasgmeneses.simplelist.api.dto.task;

public record TaskRequestDTO(long position,
                             String description,
                             boolean checked,
                             String taskListId) {

}
