package task05;

import task04.Command;

/**
 * The {@code Queue} interface defines the contract for a queue that can store and retrieve {@code Command} objects.
 * It provides methods for adding a command to the queue and taking a command from the queue.
 */
public interface Queue {

    /**
     * Adds the specified {@code Command} to the queue.
     *
     * @param command the {@code Command} object to be added to the queue
     */
    void put(Command command);

    /**
     * Retrieves and removes the {@code Command} from the queue.
     *
     * @return the {@code Command} object that was removed from the queue
     */
    Command take();
}
