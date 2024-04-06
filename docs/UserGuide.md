---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ContactSwift User Guide

Welcome to ContactSwift, the **desktop app for managing employee contacts and tasks**, optimized for use via a Command Line Interface (CLI) while still offering the Graphical User Interface (GUI) benefits. Designed for small business owners, managers, and team leaders, ContactSwift streamlines contact management and task tracking, especially for those managing remote teams.

**Key Features:**
- Fast and efficient contact management through CLI commands.
- Task tracking and productivity analysis with completion rate statistics.
- Easy filtering and searching of employee information based on various parameters.

**Who is this for?**
- Small business owners and remote team managers looking for an efficient way to manage contact details and tasks.

Start managing your team more effectively with ContactSwift **today** by following the steps in our quick start guide.

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Managing Your Employees](#managing-your-employees)
4. [FAQ](#faq)
5. [Known Issues](#known-issues)
6. [Command Summary](#command-summary)

---

## Quick Start

**Begin your ContactSwift journey with these simple steps:**

1. Ensure Java `11` or above is installed on your computer. [Find out how to check your Java version](https://www.java.com/en/download/help/version_manual.html).

2. Download the latest `contactswift.jar` from our [releases page](https://github.com/AY2324S2-CS2103T-T17-2/tp/releases/tag/v1.3).

3. Choose a folder as your _home folder_ for ContactSwift and copy the downloaded file there.

4. Open a command terminal, navigate (`cd`) to your home folder, and run the application with the command `java -jar contactswift.jar`. You'll see the GUI, preloaded with some sample data, as shown below:

   ![ContactSwift Main Interface](./images/v1.3.png)  
   *Figure 1: The main interface of ContactSwift, showing sample data.*

5. To execute commands, type them into the command box and press Enter. For instance, typing **`help`** and pressing Enter will display the help window. Try out these commands:
    - `list` – Lists all contacts.
    - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 T/A r/Manager` – Adds a contact named `John Doe`.
    - `delete 3` – Deletes the 3rd contact in the list.
    - `clear` – Removes all contacts.
    - `exit` – Closes the app.

Refer to the [Features](#features) section for more detailed command descriptions.

---

## Features

<box type="info" seamless>

**Understanding the command format is crucial for using ContactSwift effectively. Here are some tips:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaning how to access the help page.

![help message](images/helpMessage.png)

Format: `help`

### Adding an employee: `add`

Adds a employee to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE T/TEAM [t/TAG]…​`

<box type="tip" seamless>

**Tip:** An employee can have any number of tags (including 0)
</box>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 T/A r/Manager`
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Appartment p/1234567 T/B r/Secretary`

### Add task to an employee's task list: `addtask`

Adds a task to an employee's task list.

Format: `addTask uid/<UID> <description>`

- Adds a task to the employee with the specified `uid`.
- The `uid` refers to the user ID displayed beside the employee's name.
- The description of the task must be provided.
- The description of the task can only contain alphanumeric characters and spaces and cannot be empty.

Examples:

- `addTask uid/1 Complete the report by 5pm`
- `addTask uid/2 Submit the proposal by 10am`

### Mark a task as completed: `mark`

Marks a task as completed in the employee's task list.

Format: `mark uid/<UID> <taskIndex>`

- Marks the task at the specified `taskIndex` as completed for the employee with the specified `uid`.
- The `uid` refers to the user ID displayed beside the employee's name.
- The `taskIndex` refers to the index number shown in the displayed task list.
- The `taskIndex` **must be a positive integer** 1, 2, 3, …​

Examples:

- `mark uid/1 2` marks the 2nd task in the task list of the employee with the `uid` of 1 as completed.
- `mark uid/2 1` marks the 1st task in the task list of the employee with the `uid` of 2 as completed.

### Unmark a task as completed: `unmark`

Unmarks a task as completed in the employee's task list.

Format: `unmark uid/<UID> <taskIndex>`

- Unmarks the task at the specified `taskIndex` as not completed for the employee with the specified `uid`.
- The `uid` refers to the user ID displayed beside the employee's name.
- The `taskIndex` refers to the index number shown in the displayed task list.
- The `taskIndex` **must be a positive integer** 1, 2, 3, …​

Examples:

- `unmark uid/1 2` unmarks the 2nd task in the task list of the employee with the `uid` of 1 as not completed.
- `unmark uid/2 1` unmarks the 1st task in the task list of the employee with the `uid` of 2 as not completed.

### Delete a task from an employee's task list: `deleteTask`

Format: `deleteTask uid/<UID> <taskIndex>`

- Deletes the task at the specified `taskIndex` from the task list of the employee with the specified `uid`.
- The `uid` refers to the user ID displayed beside the employee's name.
- The `taskIndex` refers to the index number shown in the displayed task list.
- The `taskIndex` **must be a positive integer** 1, 2, 3, …​

Examples:

- `deleteTask uid/1 2` deletes the 2nd task in the task list of the employee with the `uid` of 1.
- `deleteTask uid/2 1` deletes the 1st task in the task list of the employee with the `uid` of 2.

### Filter employees by name, tags, roles, or teams: `filter`

Filters the list of employees based on their name, tags, roles, or teams.

Format: `filter [n/NAME] [t/TAG] [r/ROLE] [T/TEAM]`

- Filters the employee list according to the specified criteria.
- At least one of the parameters must be provided.
- Employees matching all provided criteria will be listed (i.e., `AND` search).
- Multiple values for each parameter can be provided, separated by a space.

Examples:

- `filter n/John Doe` : Shows all employees with the name `John Doe`.
- `filter t/friend` : Shows all employees tagged as `friend`.
- `filter r/Manager T/HR` : Shows all employees who are managers and belong to the HR team.
- `filter t/friend t/colleague r/Technician` : Shows all employees tagged as `friend` and `colleague` who are also technicians.
- `filter n/Jane Doe t/friend r/Executive` : Shows employees named `Jane Doe`, tagged as `friend`, and with the role of `Executive`.

### Listing all employees : `list`

Shows a list of all employees in the address book.

Format: `list`

### Editing a employee : `edit`

Edits an existing employee in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [T/TEAM] [r/ROLE] [t/TAG]…​`

- Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employee list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the employee will be removed i.e adding of tags is not cumulative.
- You can remove all the employee’s tags by typing `t/` without
  specifying any tags after it.

Examples:

- `edit 1 p/91234567 e/johndoe@example.com` Edits the phone number and email address of the 1st employee to be `91234567` and `johndoe@example.com` respectively.
- `edit 2 n/Betsy Crower t/` Edits the name of the 2nd employee to be `Betsy Crower` and clears all existing tags.

### Locating employees by name: `find`

Finds employees whose names contain any of the given keywords.

Format: `find KEYWORD [MORE_KEYWORDS]`

- The order of the keywords does not matter. e.g. `Hans Bo` will match `Bo Hans`
- Only the name is searched.
- Persons matching at least one keyword will be returned (i.e. `OR` search).
  e.g. `Hans Bo` will return `Hans Gruber`, `Bo Yang`

Examples:

- `find John` returns `john` and `John Doe`
- `find alice david` returns `Alice Smith`, `David Williams`<br>
  ![result for 'find alex david'](./images/findAliceDavidResult.png)

### Deleting a employee : `delete`

Deletes the specified employee from the address book.

Format: `delete INDEX` or `delete uid/<UID>` or `delete NAME`

- Deletes the employee at the specified `INDEX`/`UID`/`NAME`.
- The index refers to the index number shown in the displayed employee list.
- The index **must be a positive integer** 1, 2, 3, …​ and must be within the range of the displayed list.
- The UID refers to the user ID displayed beside the employee's name.
- The name must be an exact match, however it is case-insensitive.

Examples:

- `list` followed by `delete 2` deletes the 2nd employee in the address book.
- `delete betsy` deletes the employee with the name `betsy` if there are no duplicates. In the case of duplicates, the user will be prompted to delete by uid.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

### Editing the data file

AddressBook data are saved automatically as a JSON file `[JAR file location]/data/addressbook.json`. Advanced users are welcome to update data directly by editing that data file.

<box type="warning" seamless>

**Caution:**
If your changes to the data file makes its format invalid, AddressBook will discard all data and start with an empty data file at the next run. Hence, it is recommended to take a backup of the file before editing it.<br>
Furthermore, certain edits can cause the AddressBook to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Therefore, edit the data file only if you are confident that you can update it correctly.
</box>

---

# Managing your employees

Great! You have successfully installed ContactSwift and are ready to manage your employees. Let's use all the awesome features that ContactSwift has to offer.

## Track your team's productivity!

We are thrilled to introduce a sophisticated enhancement to our application - the **Completion Rate Statistics**. This innovative feature is designed to provide a comprehensive overview of task management efficiency within your teams, offering a deeper insight into employee productivity.

##### Understanding the Essence of Completion Rate Statistics

Within the dynamic interface of ContactSwift, each employee now boasts a visible completion rate on their profile card. This rate, a calculated metric representing the proportion of completed tasks to total tasks assigned, serves as an indicator of individual productivity levels. Automatically updated as tasks evolve, this metric ensures that you have the most current view of your team's performance.

![Completion Rate Statistics](./images/statisticsUi.png)

**How to use it?** This feature requires no manual intervention to activate. The completion rate metric operates seamlessly, reflecting real-time updates as tasks are added, completed, or removed. This automatic integration ensures that productivity insights are consistently accurate and available without additional effort from you or your team.

We believe that the Completion Rate Statistics feature will significantly contribute to enhancing the efficiency and productivity of your team, offering a clear, quantifiable measure of task management success. We are confident that this addition will empower you and your team to achieve and surpass your productivity goals.

### Archiving data files `[coming in v2.0]`

_Details coming soon ..._

---

## FAQ

**Q1: How do I install Java 11?**  
**A1:** Follow the instructions on the [Java 11 download page](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html).

**Q2: How can I transfer my ContactSwift data to another computer?**  
**A2:** Install ContactSwift on the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ContactSwift home folder.

---

## Known issues

1. **When using multiple screens**, if you move the application to a secondary screen, and later switch to using only the primary screen, the GUI will open off-screen. The remedy is to delete the `preferences.json` file created by the application before running the application again.

---

## Command summary

| Action          | Format, Examples                                                                                                                                                                                  |
|-----------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| **Add**         | `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS T/TEAM r/ROLE [t/TAG]…​` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 T/A r/Cleaner t/friend t/colleague` |
| **Add Task**    | `addTask uid/<uid> <description>` <br> e.g., `addTask uid/1 Complete the report by 5pm`, `addTask uid/2 Submit the proposal by 10am`                                                              |
| **Clear**       | `clear`                                                                                                                                                                                           |
| **Delete**      | `delete INDEX`/`delete uid/<UID>`/`delete NAME`<br> e.g., `delete 3`, `delete uid/101`, `delete John Doe`                                                                                         |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [T/TEAM] [r/ROLE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                     |
| **Filter**      | `filter [n/NAME] [t/TAG] [r/ROLE] [T/TEAM]` <br> e.g., `filter t/friend`,`filter r/Manager T/HR`, `filter T/HR t/friend r/Executive`                                                              |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                        |
| **List**        | `list`                                                                                                                                                                                            |
| **Help**        | `help`                                                                                                                                                                                            |
| **Mark Task**   | `mark uid/<uid> <taskIndex>` <br> e.g., `mark uid/1 3`                                                                                                                                            |
| **Unmark Task** | `unmark uid/<uid> <taskIndex>` <br> e.g., `unmark uid/1 2`                                                                                                                                        |
