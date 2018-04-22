package database.shopsDb;

public class Mall {
    private Integer id;
    private String name;
    private String address;
    private String mallGroup;

    public Mall(Integer id, String name, String address, String mallGroup) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.mallGroup = mallGroup;
    }
    public Mall(String name, String address, String mallGroup) {

        this.name = name;
        this.address = address;
        this.mallGroup = mallGroup;

    }

      public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMallGroup() {
        return mallGroup;
    }

    public void setMallGroup(String mallGroup) {
        this.mallGroup = mallGroup;
    }

    @Override
    public String toString() {

        return this.id + ". " + this.name + " " + address + " " + mallGroup + "\n";
    }
}
