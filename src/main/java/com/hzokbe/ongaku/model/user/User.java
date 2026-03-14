package com.hzokbe.ongaku.model.user;

import java.util.UUID;

public record User(UUID id, String username, String passwordHash) {
}
