package task05;

import task01.CalculationData;
import task02.ViewResult;
import task04.Command;

import java.util.concurrent.TimeUnit;

/**
 * The {@code MinMaxCommand} class implements the {@code Command} interface and is responsible for
 * finding both the minimum and maximum {@code CalculationData} items from the list of items in a {@code ViewResult}.
 * The command iterates over the items in the view result, compares each item's value to determine the min and max,
 * and tracks progress throughout the operation. It simulates processing time with delays and reports the result
 * once the calculation is complete.
 */
public class MinMaxCommand implements Command {

    /** The result of the min calculation, storing the minimum item found. */
    private final CalculationData resultMin = new CalculationData(Integer.MAX_VALUE);

    /** The result of the max calculation, storing the maximum item found. */
    private final CalculationData resultMax = new CalculationData(Integer.MIN_VALUE);

    /** The current progress of the calculation, from 0 to 100. */
    private int progress = 0;

    /** The {@code ViewResult} containing the items to find the min and max values. */
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
     * Constructs a new {@code MinMaxCommand} with the specified {@code ViewResult}.
     *
     * @param viewResult the {@code ViewResult} to be used for finding the min and max items
     */
    public MinMaxCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    /**
     * Returns the result of the min calculation.
     *
     * @return the {@code CalculationData} containing the minimum item found
     */
    public CalculationData getResultMin() {
        return resultMin;
    }

    /**
     * Returns the result of the max calculation.
     *
     * @return the {@code CalculationData} containing the maximum item found
     */
    public CalculationData getResultMax() {
        return resultMax;
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
     * Executes the command by finding both the minimum and maximum {@code CalculationData} items in the
     * {@code ViewResult}. It compares each item's value, updates the progress, and simulates processing
     * time with a delay. Once the calculation is complete, it outputs the results.
     */
    @Override
    public void execute() {
        progress = 0;
        System.out.println("MinMax executed...");
        int idx = 0, size = viewResult.getItems().size();

        // Iterate through each item to find both the min and max values
        for (CalculationData item : viewResult.getItems()) {

            if (resultMin.getNumber() > item.getNumber()) {
                resultMin.setNumber(item.getNumber());
            }

            if (resultMax.getNumber() < item.getNumber()) {
                resultMax.setNumber(item.getNumber());
            }

            idx++;
            progress = idx * 100 / size;

            // Update progress every fifth of the items
            if (idx % (size / 5) == 0) {
                System.out.println("MinMax " + progress + "%");
            }

            // Simulate processing time
            try {
                TimeUnit.MILLISECONDS.sleep(5000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }

        // Output the results
        System.out.print("MinMax done." + System.lineSeparator());
        if (resultMin.getNumber() < Integer.MAX_VALUE) {
            System.out.print("Min number found: " + resultMin + System.lineSeparator());
        } else {
            System.out.print("Min item not found." + System.lineSeparator());
        }
        if (resultMax.getNumber() > Integer.MIN_VALUE) {
            System.out.println("Max number found: " + resultMax + System.lineSeparator());
        } else {
            System.out.println("Max item not found." + System.lineSeparator());
        }

        progress = 100;
    }
}
