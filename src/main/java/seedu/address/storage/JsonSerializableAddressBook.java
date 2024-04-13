package seedu.address.storage;

import static seedu.address.model.employee.UniqueId.DEFAULT_BASE_UID;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.employee.Employee;

/**
 * An Immutable AddressBook that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_EMPLOYEE = "Employees list contains duplicate employee(s).";

    private final List<JsonAdaptedEmployee> employees = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given employees.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("employees") List<JsonAdaptedEmployee> employees) {
        this.employees.addAll(employees);
    }

    /**
     * Converts a given {@code ReadOnlyAddressBook} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created
     *               {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyAddressBook source) {
        employees.addAll(source.getEmployeeList().stream().map(JsonAdaptedEmployee::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code AddressBook} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public AddressBook toModelType() throws IllegalValueException {
        AddressBook addressBook = new AddressBook();
        for (JsonAdaptedEmployee jsonAdaptedEmployee : employees) {
            Employee employee = jsonAdaptedEmployee.toModelType();
            if (addressBook.hasEmployee(employee)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_EMPLOYEE);
            }
            addressBook.addEmployee(employee);
        }
        return addressBook;
    }

    public int getMaxUid() {
        return employees.stream()
                .mapToInt(JsonAdaptedEmployee::getUid)
                .max()
                .orElse(DEFAULT_BASE_UID); // returns default base id if list if empty
    }

    /**
     * Method to check if duplicate UIDs are present in the list of employees
     * if duplicate UIDs are present, return true
     * else return false
     *
     * @return true if duplicate UIDs are present, else false
     */
    public boolean hasDuplicateUids() {
        List<Integer> uids = employees.stream()
                .map(JsonAdaptedEmployee::getUid)
                .collect(Collectors.toList());
        return uids.size() != uids.stream().distinct().count();
    }
}
