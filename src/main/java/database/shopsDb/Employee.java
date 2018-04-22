package database.shopsDb;

public class Employee {
    private Integer id;
    private String firstName;
    private String lastName;
    private String passport;
    private Integer storeId;
    private Integer chainId;
    private String chainName;

    public Employee(String firstName, String lastName, String passport, Integer storeId, Integer chainId) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.storeId = storeId;
        this.chainId = chainId;
    }

    public Employee(int id, String firstName, String lastName, String passport, Integer storeId, Integer chainId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.storeId = storeId;
        this.chainId = chainId;
    }

    public Employee(int id, String firstName, String lastName, String passport, String chainName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.passport = passport;
        this.chainName = chainName;
    }

    public Integer getId() {
        return id;
    }

    public String getChainName() {
        return chainName;
    }

    public void setChainName(String chainName) {
        this.chainName = chainName;
    }

    public void setId(Integer id) {

        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public Integer getStoreId() {
        return storeId;
    }

    public void setStoreId(Integer storeId) {
        this.storeId = storeId;
    }

    public Integer getChainId() {
        return chainId;
    }

    public void setChainId(Integer chainId) {
        this.chainId = chainId;
    }
    @Override
    public String toString() {

        return this.id + ". " + this.firstName + " " + this.lastName + " "
                + passport + " " + chainName + "\n";
    }
}
