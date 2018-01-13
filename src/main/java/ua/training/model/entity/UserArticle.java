package ua.training.model.entity;

public class UserArticle {
    private User user;
    private Article article;
    private boolean read;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public boolean isRead() {
        return read;
    }

    public void setRead(boolean read) {
        this.read = read;
    }

    public static class UserArticleBuilder {
        private User user;
        private Article article;
        private boolean read;

        public UserArticleBuilder buildUser(User user) {
            this.user = user;
            return this;
        }

        public UserArticleBuilder buildArticle(Article article) {
            this.article = article;
            return this;
        }

        public UserArticleBuilder buildRead(boolean read) {
            this.read = read;
            return this;
        }

        public UserArticle buildUserArticle() {
            UserArticle userArticle = new UserArticle();
            userArticle.setUser(user);
            userArticle.setArticle(article);
            userArticle.setRead(read);
            return userArticle;
        }
    }
}
