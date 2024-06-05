import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.PriorityQueue;

public class StudentsThread extends Thread{
    private String name;
    public StudentsThread(String name){
        super();
        this.name=name;
    }

    public StudentsThread() {
    }

    public void run(String name, Map<String, PriorityQueue<Students>> mp) throws FileNotFoundException {
        File file = new File(name + ".txt");
        PrintWriter output = new PrintWriter(file);
        for(String major : mp.keySet()){
            output.println(major);
            for(int i = 0; i<5 && !mp.get(major).isEmpty(); i++){
                output.print(mp.get(major).peek().getFirstName() + " " + mp.get(major).peek().getLastName() + " " + mp.get(major).peek().score());
                mp.get(major).poll();
            }
            output.println();
        }
        output.close();
    }
}
