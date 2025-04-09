package task05;

import task01.CalculationData;
import task02.ViewResult;
import task04.Command;
import java.util.concurrent.TimeUnit;

/**
 * The {@code AvgCommand} class implements the {@code Command} interface and is responsible
 * for calculating the average of {@code CalculationData} items in a {@code ViewResult}.
 * The command iterates over the items in the view result, computes the sum, and then calculates
 * and stores the average. During execution, it updates the progress and simulates processing
 * with a delay.
 */
public class AvgCommand implements Command {

    /** The result of the average calculation. */
    private CalculationData result = new CalculationData();

    /** The current progress of the calculation, from 0 to 100. */
    private int progress = 0;

    /** The {@code ViewResult} containing the items for which the average is to be calculated. */
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
     * Constructs a new {@code AvgCommand} with the specified {@code ViewResult}.
     *
     * @param viewResult the {@code ViewResult} to be used for calculating the average
     */
    public AvgCommand(ViewResult viewResult) {
        this.viewResult = viewResult;
    }

    /**
     * Returns the result of the average calculation.
     *
     * @return the {@code CalculationData} containing the average value
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
     * Executes the command by calculating the average of the {@code CalculationData} items in the
     * {@code ViewResult}. It calculates the sum of the values, computes the average, updates the
     * progress, and simulates processing with a delay.
     */
    @Override
    public void execute() {
        progress = 0;
        System.out.println("Average executed...");
        result = new CalculationData();
        int sum = 0;
        int idx = 1, size = viewResult.getItems().size();
        for (CalculationData item : viewResult.getItems()) {
            sum += item.getNumber();
            progress = idx * 100 / size;

            if (idx++ % (size / 2) == 0) {
                System.out.println("Average " + progress + "%");
            }

            try {
                TimeUnit.MILLISECONDS.sleep(2000 / size);
            } catch (InterruptedException e) {
                System.err.println(e);
            }
        }
        int avg = sum / size;
        result.setNumber(avg);
        System.out.println("Average done. Result = " + result);
        progress = 100;
    }
}
