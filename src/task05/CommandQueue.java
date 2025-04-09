package task05;

import task04.Command;
import java.util.Vector;

/**
 * The {@code CommandQueue} class implements the {@code Queue} interface and provides a thread-safe
 * implementation for storing and processing {@code Command} objects.
 * This class allows commands to be added to a queue, taken for execution by a worker thread,
 * and ensures that the commands are processed in the order they are added.
 */
public class CommandQueue implements Queue {

    /** A vector that holds the commands in the queue. */
    private final Vector<Command> tasks;

    /** A flag to indicate whether the thread is waiting for new commands. */
    private boolean waiting;

    /** A flag to indicate whether the queue should shut down. */
    private boolean shutdown;

    /**
     * Shuts down the command queue, preventing any further commands from being processed.
     */
    public void shutdown() {
        shutdown = true;
    }

    /**
     * Constructs a {@code CommandQueue} and starts a worker thread that processes commands from the queue.
     */
    public CommandQueue() {
        tasks = new Vector<>();
        waiting = false;
        new Thread(new Worker()).start();
    }

    /**
     * Adds a {@code Command} to the queue. If the queue is waiting for tasks, it notifies the worker thread.
     *
     * @param r the {@code Command} object to be added to the queue
     */
    @Override
    public void put(Command r) {
        tasks.add(r);
        if (waiting) {
            synchronized (this) {
                notifyAll();
            }
        }
    }

    /**
     * Retrieves and removes a {@code Command} from the queue. If the queue is empty, the thread waits for new commands.
     *
     * @return the {@code Command} object that was removed from the queue
     */
    @Override
    public Command take() {
        if (tasks.isEmpty()) {
            synchronized (this) {
                waiting = true;
                try {
                    wait();
                } catch (InterruptedException ie) {
                    waiting = false;
                }
            }
        }
        return tasks.removeFirst();
    }

    /**
     * A worker thread that continuously takes commands from the queue and executes them.
     */
    private class Worker implements Runnable {

        /**
         * Continuously retrieves and executes commands from the queue until shutdown is initiated.
         */
        public void run() {
            while (!shutdown) {
                Command r = take();
                r.execute();
            }
        }
    }
}
