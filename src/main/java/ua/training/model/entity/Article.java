package ua.training.model.entity;

import java.time.LocalDate;

public class Article {
    private int id;
    private String name;
    private String text;
    private LocalDate dateOfPublication;
    private Periodical periodical;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Article article = (Article) o;

        if (id != article.id) return false;
        if (!name.equals(article.name)) return false;
        if (!text.equals(article.text)) return false;
        return dateOfPublication.equals(article.dateOfPublication);
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + name.hashCode();
        result = 31 * result + text.hashCode();
        result = 31 * result + dateOfPublication.hashCode();
        return result;
    }

    public static class ArticleBuilder {
        private int id;
        private String name;
        private String text;
        private LocalDate dateOfPublication;
        private Periodical periodical;

        public ArticleBuilder buildId(int id) {
            this.id = id;
            return this;
        }

        public ArticleBuilder buildName(String name) {
            this.name = name;
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
            article.setName(name);
            article.setText(text);
            article.setDateOfPublication(dateOfPublication);
            article.setPeriodical(periodical);
            return article;
        }
    }
}
