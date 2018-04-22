package database.shopsDb;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserInterface {
    Controller controller;

    public UserInterface(Controller controller) {
        this.controller = controller;
    }

    public int getChoise() throws SQLException {
        int choice = 0;
        Scanner in = new Scanner(System.in);
        in.useDelimiter("\n");


        printMenu();
        while (choice != 8) {

            choice = in.nextInt();
            switch (choice) {
                case 1:
                    Chain newChain = getChainDetails();
                    controller.createNewChain(newChain);
                    break;
                case 2:
                    Store newStore = getStoreDetails();
                    controller.addStore(newStore);
                    break;
                case 3:
                    Employee employee = getEmployeeDetails();
                    controller.addEmployee(employee);
                    break;
                case 4:
                    int mallId = getMallId();
                    List<Store> mallStores = controller.getStoresOfCertainMall(mallId);
                    System.out.println(mallStores);
                    break;
                case 5:
                    String mallGroup = getMallGroup();
                    List<Store> groupStores = controller.getStoresOfCertainMallGroup(mallGroup);
                    System.out.println(groupStores);
                    break;
                case 6:
                    int chainId = getChainId();
                    List<Employee> employees = controller.getEmployeesOfCertainChain(chainId);
                    System.out.println(employees);
                    break;
                case 7:
                    int storeId = getStoreId();
                    Store store = controller.getAllDetailsOfAShop(storeId);
                    System.out.println(store);
                    break;
            }

            printMenu();
        }
        in.close();
        return choice;
    }




    // =============   Get info from user ======================================================

    /**
     * 1. Create a new Chain
     *
     * @return
     */
    private Chain getChainDetails() {

        Scanner in = new Scanner(System.in);
        System.out.println("Enter chain name: ");
        String chainName = in.next();

        System.out.println("Enter chain subname: ");
        String chainSubName = in.next();

        return new Chain(chainName, chainSubName);
    }

    /**
     * 2. Add a Store
     *
     * @return
     */
    private Store getStoreDetails() throws SQLException {

        Scanner in = new Scanner(System.in);
        int mallId = 0;
        String storeNum = null;
        String address = null;

        System.out.println(controller.getListOfChains());

        System.out.println("Insert chain id: ");
        int chainId = in.nextInt();


        System.out.println("Is store located in the mall (y/n)?: ");
        String isMoll = in.next();
        if (isMoll.toLowerCase().equals("y")) {

            System.out.println(controller.getListOfMalls());
            System.out.println("Insert moll id: ");
            mallId = in.nextInt();

            System.out.println("Insert store number in the moll: ");
            storeNum = in.next();
        } else {
            System.out.println("Insert store address: ");
            address = in.next();
        }
        return new Store(address, storeNum, mallId, chainId);
    }



    /**
     * 3. Add an Employee
     *
     * @return
     */
    private Employee getEmployeeDetails() throws SQLException {

        Scanner in = new Scanner(System.in);
        Integer storeId = null;
        Integer chainId = null;

        System.out.println("Insert new employee to Group Management (y/n)?: ");
        String isGroupManagement = in.next();
        if (isGroupManagement.toLowerCase().equals("y")) {
            System.out.println(controller.getListOfChains());
            System.out.println("Select chain id: ");
            chainId = in.nextInt();

        } else {
            System.out.println("Insert new employee to the selected store id: ");
            System.out.println(controller.getListOfStores());
            storeId = in.nextInt();
        }
        System.out.println("Insert new employee first name: ");
        String firstName = in.next();
        System.out.println("Insert new employee last name: ");
        String lastName = in.next();
        System.out.println("Insert new employee teudat zeut: ");
        String teudatZeut = in.next();
        return new Employee(firstName, lastName, teudatZeut, storeId, chainId);
    }


    /**
     * 4. Get a store from the certain mall
     *
     * @return
     */
    private int getMallId() throws SQLException {
        Scanner in = new Scanner(System.in);
            System.out.println(controller.getListOfMalls());
            System.out.println("Insert moll id: ");
            return in.nextInt();

    }
    private String getMallGroup() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println(controller.getListOfMalls());
        System.out.println("Select moll group: ");
        return in.next();
    }
    private int getStoreId() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println(controller.getListOfStores());
        System.out.println("Select store: ");
        return in.nextInt();
    }



    private int getChainId() throws SQLException {
        Scanner in = new Scanner(System.in);
        System.out.println(controller.getListOfChains());

        System.out.println("Insert chain id: ");
        return in.nextInt();
    }
    /**
     * Print the whole menu
     */
    private void printMenu() {
        Map<Integer, String> menu = new HashMap<>();
        menu.put(1, "Create a new Chain");
        menu.put(2, "Add a Store to a Chain");
        menu.put(3, "Add employee to a Chain/to Group Management");
        menu.put(4, "Present all shops that are in a certain Shopping Mall");
        menu.put(5, "Present all shops that are in a certain Shopping Mall Group");
        menu.put(6, "Present all Employees of a certain Chain");
        menu.put(7, "Present all details of a Shop");
        menu.put(8, "Quit");

        for (int key : menu.keySet()) {
            System.out.println(key + ". " + menu.get(key));
        }
    }

}
