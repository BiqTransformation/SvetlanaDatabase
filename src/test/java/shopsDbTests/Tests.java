package shopsDbTests;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;
import database.shopsDb.*;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class Tests {
    private Controller contoller = new Controller();

    public Tests() throws SQLException {
    }

    @Test
    public void goodTestCreateNewChain() throws SQLException {
        String chainName = "Test" + System.currentTimeMillis();
        Chain newChain = new Chain(chainName, "SubTest");
        assertTrue(contoller.createNewChain(newChain));

    }

    @Test
    public void goodTestCreateNewChainNotUnique() throws SQLException {
        String chainName = "Test" + System.currentTimeMillis();
        Chain newChain = new Chain(chainName, "SubTest");
        contoller.createNewChain(newChain);

        Throwable exception = assertThrows(MySQLIntegrityConstraintViolationException.class,
                () -> {
                    contoller.createNewChain(newChain);
                }
        );
        assertTrue(exception.getMessage().contains("Duplicate"));
    }

    @Test
    public void badTestCreateNewChainLongName() throws SQLException {
        Chain newChain = new Chain("Test01234567890123456789", "SubTest");
        //TODO - assertThrows
        assertFalse(contoller.createNewChain(newChain));

    }

    @Test
    public void goodTestCreateNewStoreInMall() throws SQLException {
        Chain newChain = contoller.getListOfChains().get(3);
        Store newStore = new Store(null, "25A", 2, newChain.getId());

        assertTrue(contoller.addStore(newStore));

    }

    @Test
    public void goodTestCreateNewStoreWithAddress() throws SQLException {
        Chain newChain = contoller.getListOfChains().get(5);
        Store newStore = new Store("CityTest,Test Street," + System.currentTimeMillis(), null, null, newChain.getId());

        assertTrue(contoller.addStore(newStore));

    }

    @Test
    public void badTestCreateNewStoreNegativeChainId() throws SQLException {

        Store newStore = new Store("CityTest,Test Street", null, null, -11);
//TODO - assertThrows
        assertFalse(contoller.addStore(newStore));

    }

    @Test
    public void goodTestAddEmployeeToGroupManagement() throws SQLException {
        Chain newChain = contoller.getListOfChains().get(3);
        assertTrue(contoller.addEmployee(new Employee("Jane", "Manager", "II" + System.currentTimeMillis(), null, newChain.getId())));

    }

    @Test
    public void goodTestAddEmployeeToStore() throws SQLException {
        Chain newChain = contoller.getListOfChains().get(6);

        Store newStore1= new Store("CityTest,Test Street, " + System.currentTimeMillis(), null, null, newChain.getId());
        contoller.addStore(newStore1);
        assertTrue(contoller.addEmployee(new Employee("Jinnie", "Decorator", ""+System.currentTimeMillis(), newStore1.getId(), null)));

    }

    @Test
    public void goodTestAddNotUniqueEmployee() throws SQLException {
        String chainName = "Test" + System.currentTimeMillis();
        Chain newChain = new Chain(chainName, "SubTest");
        contoller.createNewChain(newChain);

        Throwable exception = assertThrows(MySQLIntegrityConstraintViolationException.class,
                () -> {
                    contoller.addEmployee(new Employee("Jane",
                            "Manager", "1111111", null, newChain.getId()));
                    contoller.addEmployee(new Employee("Jane",
                            "Manager", "1111111", null, newChain.getId()));
                }
        );
        assertTrue(exception.getMessage().contains("Duplicate"));
    }

    @Test
    public void badTestAddEmployeeNotExistingChain() throws SQLException {
        //TODO - assertThrows
        assertFalse(contoller.addEmployee(new Employee("A", "B", "1111111", null, 98765432)));
    }

    @Test
    public void badTestAddEmployeeNotExistingStore() throws SQLException {
        //TODO - assertThrows
        assertFalse(contoller.addEmployee(new Employee("A", "B", "1111111", 98765432, null)));
    }

    @Test
    public void goodTestPresentAllShopsOfAMall() throws SQLException {
        Mall newMall = new Mall("Children World", "Moon", null);
        contoller.createNewMall(newMall);
        String chain = "toys" + System.currentTimeMillis();
        Chain newChain = new Chain(chain, "koala");
        contoller.createNewChain(newChain);
        Store newStore = new Store(null, "33B", newMall.getId(), newChain.getId());
        contoller.addStore(newStore);

        String chain1 = "toys" + System.currentTimeMillis();
        Chain newChain1 = new Chain(chain1, "alibaba");
        contoller.createNewChain(newChain1);
        Store newStore1 = new Store(null, "44A", newMall.getId(), newChain1.getId());
        contoller.addStore(newStore1);

        newStore.setMallName("Children World");
        newStore1.setMallName("Children World");

        newStore.setMallAddress("Moon");
        newStore1.setMallAddress("Moon");

        newStore.setChainName(chain);
        newStore1.setChainName(chain1);

        List<Store> expected = new ArrayList<>();
        expected.add(newStore);
        expected.add(newStore1);

        List<Store> actual = contoller.getStoresOfCertainMall(newMall.getId());
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void goodTestPresentAllShopsOfAMallGroup() throws SQLException {
        Mall newMall = new Mall("Children World", "Moon", "Sky");
        contoller.createNewMall(newMall);

        String chain = "toys" + System.currentTimeMillis();
        Chain newChain = new Chain(chain, "koala");
        contoller.createNewChain(newChain);
        Store newStore = new Store(null, "33B", newMall.getId(), newChain.getId());
        contoller.addStore(newStore);

        String chain1 = "toys" + System.currentTimeMillis();
        Chain newChain1 = new Chain(chain1, "alibaba");
        contoller.createNewChain(newChain1);
        Store newStore1 = new Store(null, "44A", newMall.getId(), newChain1.getId());
        contoller.addStore(newStore1);

        newStore.setMallName("Children World");
        newStore1.setMallName("Children World");

        newStore.setMallAddress("Moon");
        newStore1.setMallAddress("Moon");

        newStore.setChainName(chain);
        newStore1.setChainName(chain1);

        List<Store> expected = new ArrayList<>();
        expected.add(newStore);
        expected.add(newStore1);
//TODO - clean before the test
        List<Store> actual = contoller.getStoresOfCertainMallGroup("Sky");
        assertEquals(expected.toString(), actual.toString());

    }

    @Test
    public void goodTestPresentAllEmployeesOfAChain() throws SQLException {
        String chain = "food" + System.currentTimeMillis();
        Chain newChain = new Chain(chain, "japan");
        contoller.createNewChain(newChain);

        Store newStore = new Store("TelAviv, Sarona", null, null, newChain.getId());
        contoller.addStore(newStore);
        Employee jennifer = new Employee("Jennifer", "Cooker", "tz" + System.currentTimeMillis(), newStore.getId(), null);
        contoller.addEmployee(jennifer);
        jennifer.setChainName(chain);
        Employee bigboss = new Employee("Boss", "Big", "tz" + System.currentTimeMillis(), null, newChain.getId());
        contoller.addEmployee(bigboss);
        bigboss.setChainName(chain);
        List<Employee> actual = contoller.getEmployeesOfCertainChain(newChain.getId());

        List<Employee> expected = new ArrayList<>();
        expected.add(bigboss);
        expected.add(jennifer);
//TODO - clean before test

        assertEquals(expected.toString(), actual.toString());
    }

    @Test
    public void goodTestPresentShopDetails() throws SQLException {
        Mall newMall = new Mall("Fitness", "Health street", null);
        contoller.createNewMall(newMall);
        String chain = "yoga" + System.currentTimeMillis();
        Chain newChain = new Chain(chain, "pilates");
        contoller.createNewChain(newChain);
        Store newStore = new Store(null, "33B", newMall.getId(), newChain.getId());
        contoller.addStore(newStore);
        newStore.setChainName(chain);
        newStore.setMallName("Fitness");
        newStore.setMallAddress("Health street");

        assertEquals(newStore.toString(),contoller.getAllDetailsOfAShop(newStore.getId()).toString());

    }
}
