package seedu.address.ui;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;

import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import seedu.address.model.employee.Employee;
import seedu.address.testutil.EmployeeBuilder;

public class EmployeeCardTest extends ApplicationTest {
    private VBox taskList;

    @Test
    public void updateTaskList_updatesTaskListCorrectly() {
        EmployeeBuilder employeeBuilder = new EmployeeBuilder();

        employeeBuilder.withTask("Task 1");
        employeeBuilder.withTask("Task 2");

        Employee employee = employeeBuilder.build();
        EmployeeCard employeeCard = new EmployeeCard(employee, 1); // Initialize employeeCard
        employeeCard.updateTaskList(employee);

        Label taskLabel1 = (Label) employeeCard.getTaskList().getChildren().get(1);
        assertEquals("2. [_] Task 1", taskLabel1.getText());

        Label taskLabel2 = (Label) employeeCard.getTaskList().getChildren().get(2);
        assertEquals("3. [_] Task 2", taskLabel2.getText());
    }
}
