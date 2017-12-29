package ua.training.model.entity;

import java.time.LocalDate;

public class Article {
    private int id;
    private String text;
    private LocalDate dateOfPublication;
    private Periodical periodical;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public LocalDate getDateOfPublication() {
        return dateOfPublication;
    }

    public void setDateOfPublication(LocalDate dateOfPublication) {
        this.dateOfPublication = dateOfPublication;
    }

    public Periodical getPeriodical() {
        return periodical;
    }

    public void setPeriodical(Periodical periodical) {
        this.periodical = periodical;
    }

    public static class ArticleBuilder {
        private int id;
        private String text;
        private LocalDate dateOfPublication;
        private Periodical periodical;

        public ArticleBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public ArticleBuilder buildText(String text) {
            this.text = text;
            return this;
        }

        public ArticleBuilder buildDateOfPublication(LocalDate dateOfPublication) {
            this.dateOfPublication = dateOfPublication;
            return this;
        }

        public ArticleBuilder buildPeriodical(Periodical periodical) {
            this.periodical = periodical;
            return this;
        }

        public Article buildArticle() {
            Article article = new Article();
            article.setId(id);
            article.setText(text);
            article.setDateOfPublication(dateOfPublication);
            article.setPeriodical(periodical);
            return article;
        }
    }
}
