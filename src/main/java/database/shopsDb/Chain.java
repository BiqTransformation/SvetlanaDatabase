package database.shopsDb;

public class Chain {
    private String chainName;
    private String subChain;
    private int id;

    public Chain(String chainName, String subChain) {
        this.chainName = chainName;
        this.subChain = subChain;
    }

    public Chain(int id, String chainName, String subChain) {
        this.id = id;
        this.chainName = chainName;
        this.subChain = subChain;
    }

    public String getSubChain() {
        return subChain;
    }

    public void setSubChain(String subChain) {
        this.subChain = subChain;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    @Override
    public String toString() {

        return this.id + ". " + this.chainName + " " + this.subChain + "\n";
    }
}
