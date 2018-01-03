package ua.training.model.entity;

import java.util.List;

public class Periodical {
    private int id;
    private String name;
    private String description;
    private int price;
    private List<Article> articles;
    private List<Order> orders;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
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

    public static class PeriodicalBuilder {
        private int id;
        private String name;
        private String description;
        private int price;
        private List<Article> articles;
        private List<Order> orders;

        public PeriodicalBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public PeriodicalBuilder buildName(String name) {
            this.name = name;
            return this;
        }

        public PeriodicalBuilder buildDescription(String description) {
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


        public Periodical buildPeriodical() {
            Periodical periodical = new Periodical();
            periodical.setId(id);
            periodical.setName(name);
            periodical.setDescription(description);
            periodical.setPrice(price);
            periodical.setArticles(articles);
            periodical.setOrders(orders);
            return periodical;
        }
    }
}
