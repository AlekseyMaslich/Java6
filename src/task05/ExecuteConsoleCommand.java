package task05;

import task02.View;
import task02.ViewResult;
import task04.ConsoleCommand;

import java.util.concurrent.TimeUnit;

/**
 * The {@code ExecuteConsoleCommand} class implements the {@code ConsoleCommand} interface and represents
 * a command that executes a series of tasks involving multiple threads. The tasks include calculating
 * the maximum value, average, and both minimum and maximum values of a set of data, and these operations
 * are performed in parallel.
 */
public class ExecuteConsoleCommand implements ConsoleCommand {

    /** The view associated with this command. */
    private View view;

    /**
     * Gets the current view associated with this command.
     *
     * @return the view associated with this command
     */
    public View getView() {
        return view;
    }

    /**
     * Sets the view associated with this command.
     *
     * @param view the view to be set
     * @return the set view
     */
    public View setView(View view) {
        return this.view = view;
    }

    /**
     * Constructs an {@code ExecuteConsoleCommand} with the specified view.
     *
     * @param view the view to be associated with this command
     */
    public ExecuteConsoleCommand(View view) {
        this.view = view;
    }

    /**
     * Returns the key that triggers this command.
     *
     * @return the key associated with this command, which is 'e'
     */
    @Override
    public char getKey() {
        return 'e';
    }

    /**
     * Returns a string representation of this command.
     *
     * @return the string representing this command, which is "'e'xecute"
     */
    @Override
    public String toString() {
        return "'e'xecute";
    }

    /**
     * Executes the command by creating and starting multiple worker threads that perform
     * different calculations (max, average, and min-max) concurrently. It waits for the tasks to finish,
     * shuts down the queues, and then prints a completion message.
     */
    @Override
    public void execute() {
        CommandQueue queue1 = new CommandQueue();
        CommandQueue queue2 = new CommandQueue();

        MaxCommand maxCommand = new MaxCommand((ViewResult)view);
        AvgCommand avgCommand = new AvgCommand((ViewResult)view);
        MinMaxCommand minMaxCommand = new MinMaxCommand((ViewResult)view);

        System.out.println("Execute all threads...");

        queue1.put(minMaxCommand);
        queue2.put(maxCommand);
        queue2.put(avgCommand);

        try {
            // Wait for all tasks to complete
            while (avgCommand.running() || maxCommand.running() || minMaxCommand.running()) {
                TimeUnit.MILLISECONDS.sleep(100);
            }

            // Shutdown the queues after task completion
            queue1.shutdown();
            queue2.shutdown();

            // Allow time for all tasks to finalize
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            System.err.println(e);
        }

        System.out.println("All done.");
    }
}
