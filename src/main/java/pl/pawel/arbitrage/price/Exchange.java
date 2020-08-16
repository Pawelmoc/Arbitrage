package pl.pawel.arbitrage.price;

public enum Exchange {

    BINANCE("Binance"), COINBASE("Coinbase Pro");

    private String displayName;

    Exchange(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}