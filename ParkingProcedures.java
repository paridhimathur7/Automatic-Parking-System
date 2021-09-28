import java.util.*;

public class ParkingProcedures {
  int MAX_SIZE = 0;
  
  private class Car {
    String regNo;
    String age;

    public Car(String regNo, String age) {
      this.regNo = regNo;
      this.age = age;
    }
  }
  ArrayList<Integer> availableSlotList;
  // Map of Slot, Car
  Map<String, Car> carMap;
  // Map of RegNo, Slot
  Map<String, String> regNoMap;
  // Map of Age, List of RegNo
  Map<String, ArrayList<String>> regNoWithAgeMap;

  public void createParkingLot(String lotCount) {
    try {
      this.MAX_SIZE = Integer.parseInt(lotCount);
    } catch (Exception e) {
      System.out.println("Invalid lot entered.Please enter the correct input");
    }
    this.availableSlotList = new ArrayList<Integer>() {
    };
    for (int i = 1; i <= this.MAX_SIZE; i++) {
      availableSlotList.add(i);
    }
    this.carMap = new HashMap<String, Car>();
    this.regNoMap = new HashMap<String, String>();
    this.regNoWithAgeMap = new HashMap<String, ArrayList<String>>();
    System.out.println("Created parking of " + lotCount + " slots");
  }

  public void park(String regNo, String driver_age, String age) {
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");
    } else if (this.carMap.size() == this.MAX_SIZE) {
      System.out.println("Sorry, parking lot is full. Please come back later");
    } else {
      Collections.sort(availableSlotList);
      String slot = availableSlotList.get(0).toString();
      Car car = new Car(regNo, age);
      this.carMap.put(slot, car);
      this.regNoMap.put(regNo, slot);
      if (this.regNoWithAgeMap.containsKey(age)) {
        ArrayList<String> regNoList = this.regNoWithAgeMap.get(age);
        this.regNoWithAgeMap.remove(age);
        regNoList.add(regNo);
        this.regNoWithAgeMap.put(age, regNoList);
      } else {
        ArrayList<String> regNoList = new ArrayList<String>();
        regNoList.add(regNo);
        this.regNoWithAgeMap.put(age, regNoList);
      }
      System.out.println("Car with vehicle registration number " + "'"+regNo+ "'" + " has been parked at slot number "+slot);
      availableSlotList.remove(0);
    }
  }

  public void leave(String slotNo) {
    
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");
    } else if (this.carMap.size() > 0) {
      Car carToLeave = this.carMap.get(slotNo);
      if (carToLeave != null) {
        this.carMap.remove(slotNo);
        this.regNoMap.remove(carToLeave.regNo);
        ArrayList<String> regNoList = this.regNoWithAgeMap.get(carToLeave.age);
        if (regNoList.contains(carToLeave.regNo)) {
          regNoList.remove(carToLeave.regNo);
        }
        // Add the Lot No. back to available slot list.
        this.availableSlotList.add(Integer.parseInt(slotNo));
        System.out.println("Slot number " + slotNo + " vacated, the car with vehicle registration number "+"'"+carToLeave.regNo+"'"+" left the space, the driver of the car was of age "+carToLeave.age);
      } else {
        System.out.println("Slot number " + slotNo + " is not allocated to any vehicle");
      }
    } else {
      System.out.println("Parking lot is empty");
    }
  }

  public void status() {
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");
    } else if (this.carMap.size() > 0) {
      // Print the current status.
      System.out.println("Slot No.\tRegistration No.\tage");
      Car car;
      for (int i = 1; i <= this.MAX_SIZE; i++) {
        String key = Integer.toString(i);
        if (this.carMap.containsKey(key)) {
          car = this.carMap.get(key);
          System.out.println("  " + i + "\t\t\t" + car.regNo + "\t\t" + car.age);
        }
      }
    } else {
      System.out.println("Parking lot is empty");
      System.out.println();
    }
  }

  public void getRegistrationNumbersForAge(String age) {
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");
      System.out.println();
    } else if (this.regNoWithAgeMap.containsKey(age)) {
      ArrayList<String> regNoList = this.regNoWithAgeMap.get(age);
      for (int i = 0; i < regNoList.size(); i++) {
        if (!(i == regNoList.size() - 1)) {
          System.out.print(regNoList.get(i) + ",");
        } else {
          System.out.print(regNoList.get(i));
        }
      }
    } else {
      System.out.println("   ");

    }
  }

  public void getSlotNumbersForAge(String age) {
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");

    } else if (this.regNoWithAgeMap.containsKey(age)) {
      ArrayList<String> regNoList = this.regNoWithAgeMap.get(age);
      ArrayList<Integer> slotList = new ArrayList<Integer>();
      System.out.println();
      for (int i = 0; i < regNoList.size(); i++) {
        slotList.add(Integer.valueOf(this.regNoMap.get(regNoList.get(i))));
      }
      Collections.sort(slotList);
      for (int j = 0; j < slotList.size(); j++) {
        if (!(j == slotList.size() - 1)) {
          System.out.print(slotList.get(j) + ",");
        } else {
          System.out.print(slotList.get(j));
        }
      }

    } else {
      System.out.println("Not found");
    }
    System.out.println();
  }

  public void getSlotNumberForRegNo(String regNo) {
    if (this.MAX_SIZE == 0) {
      System.out.println("Sorry, parking lot is not created.Please create the lot with 'Create_parking_lot' command");
      System.out.println();
    } else if (this.regNoMap.containsKey(regNo)) {
      System.out.println(this.regNoMap.get(regNo));
    } else {
      System.out.println("Not found");
    }
  }
}
