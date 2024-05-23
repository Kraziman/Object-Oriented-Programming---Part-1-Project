public class sessionHistory {
    private Session session;
    private int currentImageIndex;

    public sessionHistory(Session session, int currentImageIndex) {
        this.session = session;
        this.currentImageIndex = currentImageIndex;
    }

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public int getCurrentImageIndex() {
        return currentImageIndex;
    }

    public void setCurrentImageIndex(int currentImageIndex) {
        this.currentImageIndex = currentImageIndex;
    }
}
