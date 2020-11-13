public class Main
{
    public static void main(String [] args) throws InterruptedException {
        ThreadingPOC threadingPOC = new ThreadingPOC();
        threadingPOC.sumRaceCondition();
        threadingPOC.sumAtomically();

        WriteFile wf = new WriteFile();
        wf.StartTask("my async file", "my async file content");

        /* Now do other CPU work here and do not wait for the task */
        System.out.println("Dit gebeurt voordat de task is afgerond.");

        /* When done with other work sync with background task */
        wf.waitOntask();
    }
}
