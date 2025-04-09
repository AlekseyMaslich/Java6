package task05.test;

import task01.CalculationData;
import task02.View;
import task02.ViewResult;
import task02.ViewableResult;
import task05.AvgCommand;
import task05.MaxCommand;
import task05.MinMaxCommand;
import task05.ExecuteConsoleCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for the {@code AvgCommand}, {@code MaxCommand}, {@code MinMaxCommand}, and {@code ExecuteConsoleCommand}.
 * These tests ensure the correct behavior of these commands, including the calculation and execution of average, max, and min/max operations.
 */
public class TestMain {

    private View viewResult;

    /**
     * Sets up the testing environment by initializing a ViewResult instance with mock data.
     */
    @BeforeEach
    public void setUp() {
        // Initialize the viewResult with a real ViewableResult
        viewResult = new ViewableResult().getView();

        // Create mock data for calculation
        ArrayList<CalculationData> items = new ArrayList<>();
        items.add(new CalculationData(10));
        items.add(new CalculationData(20));
        items.add(new CalculationData(30));
        items.add(new CalculationData(40));
        items.add(new CalculationData(50));

        // Manually set items into the viewResult
        viewResult.setItems(items);
    }

    /**
     * Test the execution of the {@code AvgCommand}.
     * Verifies that the average command correctly calculates and stores the average.
     */
    @Test
    public void testAvgCommand() {
        AvgCommand avgCommand = new AvgCommand((ViewResult) viewResult);

        // Execute the average calculation command
        avgCommand.execute();

        // Verify the result
        CalculationData result = avgCommand.getResult();
        assertNotNull(result, "Result should not be null");
        assertEquals(30, result.getNumber(), "The average should be 30");
    }

    /**
     * Test the execution of the {@code MaxCommand}.
     * Verifies that the max command correctly identifies the maximum value.
     */
    @Test
    public void testMaxCommand() {
        MaxCommand maxCommand = new MaxCommand((ViewResult) viewResult);

        // Execute the max calculation command
        maxCommand.execute();

        // Verify the result
        CalculationData result = maxCommand.getResult();
        assertNotNull(result, "Result should not be null");
        assertEquals(50, result.getNumber(), "The maximum value should be 50");
    }

    /**
     * Test the execution of the {@code MinMaxCommand}.
     * Verifies that the MinMax command correctly calculates both the min and max values.
     */
    @Test
    public void testMinMaxCommand() {
        MinMaxCommand minMaxCommand = new MinMaxCommand((ViewResult) viewResult);

        // Execute the MinMax calculation command
        minMaxCommand.execute();

        // Verify the result
        CalculationData resultMin = minMaxCommand.getResultMin();
        CalculationData resultMax = minMaxCommand.getResultMax();

        assertNotNull(resultMin, "Min result should not be null");
        assertNotNull(resultMax, "Max result should not be null");

        assertEquals(10, resultMin.getNumber(), "The minimum value should be 10");
        assertEquals(50, resultMax.getNumber(), "The maximum value should be 50");
    }

    /**
     * Test the execution of the {@code ExecuteConsoleCommand}.
     * Verifies that the command correctly queues and executes the commands.
     */
    @Test
    public void testExecuteConsoleCommand() {
        ExecuteConsoleCommand executeConsoleCommand = new ExecuteConsoleCommand(viewResult);

        // Since this command involves multithreading, we just verify that it executes without exceptions
        assertDoesNotThrow(executeConsoleCommand::execute, "ExecuteConsoleCommand should not throw exceptions");
    }
}
