package task05;

import task01.CalculationData;
import task02.ViewResult;
import task04.Command;

import java.util.concurrent.TimeUnit;

/**
 * The {@code MaxCommand} class implements the {@code Command} interface and is responsible for
 * finding the maximum {@code CalculationData} item from the list of items in a {@code ViewResult}.
 * The command iterates over the items in the view result, compares each item's value, and keeps track
 * of the highest value found. During execution, it updates the progress and simulates processing
 * with a delay.
 */
public class MaxCommand implements Command {

    /** The result of the max calculation, storing the maximum item found. */
    private CalculationData result = new CalculationData();

    /** The current progress of the calculation, from 0 to 100. */
    private int progress = 0;

    /** The {@code ViewResult} containing the items for which the maximum is to be found. */
    private ViewResult viewResult;

    /**
     * Returns the {@code ViewResult} associated with this command.
     *
     * @return the {@code ViewResult} object
     */
    public ViewResult getViewResult() {
        return viewResult;
    }

    /**
     * Sets the {@code ViewResult} for this command.
     *
     * @param viewResult the {@code ViewResult} object to be set
     * @return the {@code ViewResult} object that was set
     */
    public ViewResult setViewResult(ViewResult viewResult) {
        return this.viewResult = viewResult;
    }

    /**
     * Constructs a new {@code MaxCommand} with the specified {@code ViewResult}.
     *
     * @param viewResult the {@code ViewResult} to be used for finding the maximum item
     */
    public MaxCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    /**
     * Returns the result of the max calculation.
     *
     * @return the {@code CalculationData} containing the maximum item found
     */
    public CalculationData getResult() {
        return result;
    }

    /**
     * Returns {@code true} if the calculation is still running (i.e., the progress is less than 100%),
     * otherwise returns {@code false}.
     *
     * @return {@code true} if the calculation is in progress, {@code false} otherwise
     */
    public boolean running() {
        return progress < 100;
    }

    /**
     * Executes the command by finding the maximum {@code CalculationData} item in the
     * {@code ViewResult}. It compares each item's value and updates the progress, simulating
     * processing with a delay.
     */
    @Override
    public void execute() {
        progress = 0;
        System.out.println("Max executed...");
        int size = viewResult.getItems().size();
        result = new CalculationData();

        // Iterate through each item to find the maximum value
        for (int i = 1; i < size; i++) {
            if (result.getNumber() < viewResult.getItems().get(i).getNumber()) {
                result = viewResult.getItems().get(i);
            }

            progress = i * 100 / size;

            // Update progress every third of the items
            if (i % (size / 3) == 0) {
                System.out.println("Max " + progress + "%");
            }

            // Simulate processing time
            try {
                TimeUnit.MILLISECONDS.sleep(3000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        // Output the result
        System.out.println("Max item found: " + result);
        progress = 100;
    }
}
