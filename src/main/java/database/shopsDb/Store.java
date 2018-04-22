package database.shopsDb;

public class Store {
    private int id;
    private String address;
    private String storeNum;
    private Integer mallId;
    private Integer chainId;
    private String mallName;
    private String mallAddress;
    private String chainName;

    public Store(String address, String storeNum, Integer mallId, Integer chainId) {
        this.address = address;
        this.storeNum = storeNum;
        this.mallId = mallId;
        this.chainId = chainId;
    }

    public Store(int id,String chainName,String address, String mallName, String mallAddress, String storeNum) {
        this.id = id;
        this.chainName = chainName;
        this.address = address;
        this.mallName = mallName;
        this.mallAddress = mallAddress;
        this.storeNum = storeNum;
     }

    public void setMallName(String mallName) {
        this.mallName = mallName;
    }

    public void setMallAddress(String mallAddress) {
        this.mallAddress = mallAddress;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public String getMallName() {

        return mallName;
    }

    public String getMallAddress() {
        return mallAddress;
    }

    public String getChainName() {
        return chainName;
    }

    public Integer getMallId() {
        return mallId;
    }

    public void setMallId(Integer mallId) {
        this.mallId = mallId;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStoreNum() {
        return storeNum;
    }

    public void setStoreNum(String storeNum) {
        this.storeNum = storeNum;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {

        return this.id + ". " + this.chainName + " " + this.address
                + mallName + " " + mallAddress + " " + storeNum + "\n";
    }

    public int getId() {
        return id;
    }
}
