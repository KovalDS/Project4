package ua.training.model.entity;

import java.util.List;

public class Periodical {
    private int id;
    private int name;
    private int description;
    private int price;
    private List<Article> articles;
    private List<Order> orders;
    private List<User> users;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getName() {
        return name;
    }

    public void setName(int name) {
        this.name = name;
    }

    public int getDescription() {
        return description;
    }

    public void setDescription(int description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public static class PeriodicalBuilder {
        private int id;
        private int name;
        private int description;
        private int price;
        private List<Article> articles;
        private List<Order> orders;
        private List<User> users;

        public PeriodicalBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public PeriodicalBuilder buildName(int name) {
            this.name = name;
            return this;
        }

        public PeriodicalBuilder buildDescription(int description) {
            this.description = description;
            return this;
        }

        public PeriodicalBuilder buildPrice(int price) {
            this.price = price;
            return this;
        }

        public PeriodicalBuilder buildArticles(List<Article> articles) {
            this.articles = articles;
            return this;
        }

        public PeriodicalBuilder buildOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public PeriodicalBuilder buildUsers(List<User> users) {
            this.users = users;
            return this;
        }

        public Periodical buildPeriodical() {
            Periodical periodical = new Periodical();
            periodical.setId(id);
            periodical.setName(name);
            periodical.setDescription(description);
            periodical.setPrice(price);
            periodical.setArticles(articles);
            periodical.setOrders(orders);
            periodical.setUsers(users);
            return periodical;
        }
    }
}
