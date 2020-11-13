import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile {

    private Thread t;
    private volatile boolean isDone = false;

    public void StartTask(String filename, String text)
    {
        t = new Thread(() ->
        {
            // Om het effect van een achtergrond taak te benadrukken, voegen we hier 10 seconden sleep toe.
            try {
                t.sleep(1000*20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            File file = new File(filename);

            // creates the file
            try
            {
                file.createNewFile();

                // creates a FileWriter Object
                FileWriter writer = new FileWriter(file);

                // Writes the content to the file
                writer.write(text);
                writer.flush();
                writer.close();

            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Wrote  file");
            isDone = true;
        });
        t.start();
    }

    public void waitOntask() throws InterruptedException {
        t.join();
    }

    public boolean taskIsDone()
    {
        return isDone;
    }
}
