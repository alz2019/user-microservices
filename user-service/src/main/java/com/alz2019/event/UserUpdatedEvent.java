package com.alz2019.event;

import java.io.Serializable;
import java.util.UUID;

public record UserUpdatedEvent(UUID id) implements Serializable {
}
