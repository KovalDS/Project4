package ua.training.model.entity;

import jdk.nashorn.internal.runtime.UserAccessorProperty;

import java.util.List;

public class User {
    private int id;
    private String email;
    private String password;
    private String firstName;
    private String secondName;
    private int balance;
    private List<UserArticle> userArticles;
    private List<Order> orders;
    private Role role;
    private List<Periodical> periodicals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public List<UserArticle> getUserArticles() {
        return userArticles;
    }

    public void setUserArticles(List<UserArticle> unreadArticles) {
        this.userArticles = unreadArticles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public Role getRole() {
        return role;
    }

    public void setRoles(Role role) {
        this.role = role;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public static class UserBuilder {
        private int id;
        private String email;
        private String password;
        private String firstName;
        private String secondName;
        private int balance;
        private List<UserArticle> userArticles;
        private List<Order> orders;
        private Role role;
        private List<Periodical> periodicals;

        public UserBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public UserBuilder buildEmail(String email) {
            this.email = email;
            return this;
        }

        public UserBuilder buildPassword(String password) {
            this.password = password;
            return this;
        }

        public UserBuilder buildFirstName(String firstName) {
            this.firstName = firstName;
            return this;
        }

        public UserBuilder buildSecondName(String secondName) {
            this.secondName = secondName;
            return this;
        }

        public UserBuilder buildBalance(int balance) {
            this.balance = balance;
            return this;
        }

        public UserBuilder buildUserArticles(List<UserArticle> userArticle) {
            this.userArticles = userArticles;
            return this;
        }

        public UserBuilder buildOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public UserBuilder buildRole(Role role) {
            this.role = role;
            return this;
        }

        public UserBuilder buildPeriodicals(List<Periodical> periodicals) {
            this.periodicals = periodicals;
            return this;
        }

        public User buildUser() {
            User user = new User();
            user.setId(id);
            user.setEmail(email);
            user.setPassword(password);
            user.setFirstName(firstName);
            user.setSecondName(secondName);
            user.setBalance(balance);
            user.setUserArticles(userArticles);
            user.setOrders(orders);
            user.setRoles(role);
            user.setPeriodicals(periodicals);
            return user;
        }
    }
}
