import java.lang.reflect.Method;
import java.util.Map;
import java.util.HashMap;


public class InputFromUser  {

    //Fetching the user input from user and adding into the map
    public Map<String, Method> inputFromUserMap;

    public InputFromUser() {
        inputFromUserMap = new HashMap<String, Method>();
        
        try {

        inputFromUserMap.put("Create_parking_lot", ParkingProcedures.class.getMethod("createParkingLot",String.class));
        inputFromUserMap.put("Park", ParkingProcedures.class.getDeclaredMethod("park", String.class, String.class, String.class));
        inputFromUserMap.put("Leave", ParkingProcedures.class.getMethod("leave", String.class));
        inputFromUserMap.put("status", ParkingProcedures.class.getMethod("status"));
        inputFromUserMap.put("Vehicle_registration_number_for_driver_of_age", ParkingProcedures.class.getMethod("getRegistrationNumbersForAge", String.class));
        inputFromUserMap.put("Slot_numbers_for_driver_of_age", ParkingProcedures.class.getMethod("getSlotNumbersForAge", String.class));
        inputFromUserMap.put("Slot_number_for_car_with_number", ParkingProcedures.class.getMethod("getSlotNumberForRegNo", String.class));

    }
        catch (NoSuchMethodException e) {
            e.printStackTrace();
            
        }
    }
    
}
