import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        Map<String, PriorityQueue<Students>> yale = new HashMap<>();
        Map<String, PriorityQueue<Students>> harvard = new HashMap<>();
        Map<String, PriorityQueue<Students>> princeton = new HashMap<>();
        try {
            File file = new File("students.txt");
            Scanner input = new Scanner(file);

            while (input.hasNext()) {
                String firstName = input.next();
                String lastName = input.next();
                int toefl = input.nextInt();
                int sat = input.nextInt();
                double transcript = input.nextDouble();
                String major = input.next();
                String university = input.next();
                Students student = new Students(firstName, lastName, toefl, sat, transcript, major, university);
                if(toefl>105 && sat>1200 && transcript > 5.50 ){
                    if(Objects.equals(university, "Yale")){
                        if(!yale.containsKey(major)) yale.put(major, new PriorityQueue<>());
                        yale.get(major).add(student);
                    }
                    if(Objects.equals(university, "Harvard")){
                        if(!harvard.containsKey(major)) harvard.put(major, new PriorityQueue<>());
                        harvard.get(major).add(student);
                    }
                    if(Objects.equals(university, "Princeton")){
                        if(!princeton.containsKey(major)) princeton.put(major, new PriorityQueue<>());
                        princeton.get(major).add(student);
                    }
                }
            }
            input.close();
        } catch (Exception e) {
            System.out.println("An error occurred!");
        }

        StudentsThread yaleThread = new StudentsThread();
        StudentsThread harvardThread = new StudentsThread();
        StudentsThread princetonThread = new StudentsThread();

        yaleThread.start();
        harvardThread.start();
        princetonThread.start();

        yaleThread.run("yale", yale);
        harvardThread.run("harvard", harvard);
        princetonThread.run("princeton", princeton);
    }
}