package ua.training.model.entity;

import java.util.List;

public enum  Role {

    ADMIN("admin"), USER("user"), GUEST("guest");

    private String name;

    Role(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
