package ua.training.model.entity;

import java.time.LocalDate;
import java.util.List;

public class Order {
    private int id;
    private String status;
    private int totalPrice;
    private LocalDate date;
    private User user;
    private List<Periodical> periodicals;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Periodical> getPeriodicals() {
        return periodicals;
    }

    public void setPeriodicals(List<Periodical> periodicals) {
        this.periodicals = periodicals;
    }

    public static class OrderBuilder {
        private int id;
        private String status;
        private int totalPrice;
        private LocalDate date;
        private User user;
        private List<Periodical> periodicals;

        public OrderBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public OrderBuilder buildStatus(String status) {
            this.status = status;
            return this;
        }

        public OrderBuilder buildTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public OrderBuilder buildDate(LocalDate date) {
            this.date = date;
            return this;
        }

        public OrderBuilder buildUser(User user) {
            this.user = user;
            return this;
        }

        public OrderBuilder buildPeriodicals(List<Periodical> periodicals) {
            this.periodicals = periodicals;
            return this;
        }

        public Order buildOrder() {
            Order order = new Order();
            order.setId(id);
            order.setStatus(status);
            order.setTotalPrice(totalPrice);
            order.setDate(date);
            order.setUser(user);
            order.setPeriodicals(periodicals);
            return order;
        }
    }
}
