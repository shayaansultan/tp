package seedu.address.model.employee;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ADDRESS_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_ROLE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_HUSBAND;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TEAM_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_UID_AMY;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalEmployees.ALICE;
import static seedu.address.testutil.TypicalEmployees.BOB;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.model.tag.Tag;
import seedu.address.testutil.EmployeeBuilder;

public class EmployeeTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Employee employee = new EmployeeBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> employee.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSameEmployee(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSameEmployee(null));

        // same name, all other attributes different -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withAddress(VALID_ADDRESS_BOB).withTeam(VALID_TEAM_BOB).withRole(VALID_ROLE_BOB)
                .withTags(VALID_TAG_HUSBAND).withUid(VALID_UID_AMY).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSameEmployee(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Employee editedBob = new EmployeeBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSameEmployee(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new EmployeeBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSameEmployee(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Employee aliceCopy = new EmployeeBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different employee -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Employee editedAlice = new EmployeeBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different address -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withAddress(VALID_ADDRESS_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different tags -> returns false
        editedAlice = new EmployeeBuilder(ALICE).withTags(VALID_TAG_HUSBAND).build();
        assertFalse(ALICE.equals(editedAlice));
    }

    @Test
    public void toStringMethod() {
        String expected = Employee.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", address=" + ALICE.getAddress() + ", team=" + ALICE.getTeam()
                + ", role=" + ALICE.getRole() + ", tags=" + ALICE.getTags() + ", uid=" + ALICE.getUid()
                + ", tasks=" + ALICE.getTaskList() + "}";
        assertEquals(expected, ALICE.toString());
    }

    @Test
    public void test_getTaskCompletionRate_noTasks() {
        Employee alice = new EmployeeBuilder().withName("Alice").build(); // Alice with no tasks
        assertEquals(0.0, alice.getTaskCompletionRate());
    }

    @Test
    public void test_getTaskCompletionRate_allTasksCompleted() {
        EmployeeBuilder aliceBuilder = new EmployeeBuilder().withName("Alice");
        // Add and mark all tasks as completed
        for (int i = 0; i < 5; i++) {
            aliceBuilder.withTask("Task " + i);
        }
        Employee alice = aliceBuilder.build();
        for (int i = 0; i < alice.getTaskList().size(); i++) {
            alice.markTask(i);
        }
        assertEquals(100.00, alice.getTaskCompletionRate());
    }

    @Test
    public void test_getTaskCompletionRate_someTasksCompleted() {
        EmployeeBuilder aliceBuilder = new EmployeeBuilder().withName("Alice");
        // Add tasks and mark half of them as completed
        for (int i = 0; i < 10; i++) {
            aliceBuilder.withTask("Task " + i);
        }
        Employee alice = aliceBuilder.build();
        for (int i = 0; i < alice.getTaskList().size() / 2; i++) {
            alice.markTask(i);
        }
        double expectedCompletionRate = (double) (alice.getTaskList().getCompletedTasks() * 100)
                / alice.getTaskList().size();
        expectedCompletionRate = Math.round(expectedCompletionRate * 100.0) / 100.0;
        assertEquals(expectedCompletionRate, alice.getTaskCompletionRate());
    }

    @Test
    public void constructor_nullName_throwsNullPointerException() {
        String nullName = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withName(nullName).build();
        });
    }

    @Test
    public void constructor_nullPhone_throwsNullPointerException() {
        String nullPhone = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withPhone(nullPhone).build();
        });
    }

    @Test
    public void constructor_nullEmail_throwsNullPointerException() {
        String nullEmail = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withEmail(nullEmail).build();
        });
    }

    @Test
    public void constructor_nullAddress_throwsNullPointerException() {
        String nullAddress = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withAddress(nullAddress).build();
        });
    }

    @Test
    public void constructor_nullTeam_throwsNullPointerException() {
        String nullTeam = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withTeam(nullTeam).build();
        });
    }

    @Test
    public void constructor_nullRole_throwsNullPointerException() {
        String nullRole = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withRole(nullRole).build();
        });
    }

    @Test
    public void constructor_nullTags_throwsNullPointerException() {
        String nullTags = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withTags(nullTags).build();
        });
    }

    @Test
    public void constructor_nullUid_throwsNullPointerException() {
        String nullUid = null;
        assertThrows(Exception.class, () -> {
            new EmployeeBuilder().withUid(nullUid).build();
        });
    }

    @Test
    public void constructor_nullTasks_throwsNullPointerException() {
        String nullTasks = null;
        assertThrows(NullPointerException.class, () -> {
            new EmployeeBuilder().withTask(nullTasks).build();
        });
    }

    @Test
    public void constructor_allFieldsPresent_success() {
        Name testName = new Name(EmployeeBuilder.DEFAULT_NAME);
        Phone testPhone = new Phone(EmployeeBuilder.DEFAULT_PHONE);
        Email testEmail = new Email(EmployeeBuilder.DEFAULT_EMAIL);
        Address testAddress = new Address(EmployeeBuilder.DEFAULT_ADDRESS);
        Team testTeam = new Team(EmployeeBuilder.DEFAULT_TEAM);
        Role testRole = new Role(EmployeeBuilder.DEFAULT_ROLE);
        Set<Tag> testTags = new HashSet<>();
        UniqueId testUid = new UniqueId(EmployeeBuilder.DEFAULT_UID);

        Employee employee = new Employee(testName, testPhone, testEmail, testAddress, testTeam, testRole, testTags,
                testUid);

        assertEquals(testName, employee.getName());
        assertEquals(testPhone, employee.getPhone());
        assertEquals(testEmail, employee.getEmail());
        assertEquals(testAddress, employee.getAddress());
        assertEquals(testTeam, employee.getTeam());
        assertEquals(testRole, employee.getRole());
        assertEquals(testTags, employee.getTags());
        assertEquals(testUid, employee.getUid());
    }
}
