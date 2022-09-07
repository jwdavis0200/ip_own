package dukechatbot.utility;
import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * The Storage class encapsulates the operations on the file to be read and written.
 *
 * @author A0233290M
 * @version Week3
 */
public class Storage {
    /**
     * Defines the file to be associated with this instance of the Storage class.
     */
    private static final File FILE = new File("storage.txt");
    /**
     * Defines the instance of the BufferedReader to allow the instance of Storage to read from input.
     */
    private BufferedReader br;
    /**
     * Encapsulates the instance of TaskList associated with the instance of Storage.
     */
    private TaskList tasks;
    /**
     * The instance of ui to be passed in for use by the class to interact with user.
     */
    private Ui ui;
    /**
     * Constructs the instance of storage to read and write from and to the file passed in.
     *
     * @param fileName the name of the file to be processed.
     * @param tasks the task list to be manipulated by the instance of Storage.
     * @param ui the ui associated with this current run of the program.
     * @throws IOException when createNewFile fails to create the file.
     */
    public Storage(String fileName, TaskList tasks, Ui ui) throws IOException {
        this.br = new BufferedReader(new FileReader(FILE));
        this.tasks = tasks;
        this.ui = ui;
        if (FILE.exists()) {
            this.load();
        } else {
            if (FILE.createNewFile()) {
                this.load();
            } else {
                throw new IOException("File failed creation!");
            }
        }
    }

    /**
     * Loads the content of the file and into the list of tasks stored in the associated txt file.
     *
     * @throws IOException if the file fails to be read.
     */
    public void load() throws IOException {
        if (FILE.canRead()) {
            String ln = this.br.readLine();
            while (ln != null) {
                this.tasks.add(ln);
                ln = br.readLine();
            }
        } else {
            this.ui.showLoadingError();
        }
    }

    /**
     * Saves the tasks of the task list into the associated file to be loaded in the next run of the program.
     *
     * @throws IOException when method fails to write into the associated file.
     */
    public static void save(ArrayList<Task> taskArrayList) throws IOException {
        FileWriter fw = new FileWriter(FILE.getAbsolutePath());
        for (Iterator<Task> it = taskArrayList.iterator(); it.hasNext();) {
            Task curr = it.next();
            fw.write(curr.toString() + "\r\n");
        }
        fw.close();
    }
}
