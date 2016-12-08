
/**
 * Created by oerlex on 07.12.16.
 */
public class Main {

    public static void main(String[] args) {
        SQLHandler sqlHandler = new SQLHandler();
        try {
            int euh = sqlHandler.importIntoSQL();

            System.out.println(euh);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


}
