import DB.DBOperations;
import Entities.Teacher;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Teacher teacher = new Teacher("Carolina Capitan","0728555444");
        Teacher teacher2 = new Teacher("Cotiso Nistorica","0728555445");
        Teacher teacher3 = new Teacher("Dada Nistorica","0728550444");
        Teacher teacher4 = new Teacher("Andrada Calugar","0728555244");

        DBOperations.deleteRecord(4);

        ArrayList<Teacher> list = (ArrayList<Teacher>) DBOperations.getRecords();
        for(Teacher t: list){
            System.out.println(t.toString());
        }
    }
}
