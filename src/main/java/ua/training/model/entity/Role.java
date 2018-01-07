package ua.training.model.entity;

import java.util.List;

public class Role {
    private int id;
    private String name;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Role role = (Role) o;

        return name.equals(role.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    public static class RoleBuilder {
        private int id;
        private String name;
        private List<User> users;

        public RoleBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public RoleBuilder buildName(String name) {
            this.name = name;
            return this;
        }

        public RoleBuilder buildUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Role buildRole() {
            Role role = new Role();
            role.setId(id);
            role.setName(name);
            role.setUsers(users);
            return role;
        }
    }
}
