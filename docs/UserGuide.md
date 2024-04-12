---
  layout: default.md
  title: "User Guide"
  pageNav: 3
---

# ContactSwift User Guide

Welcome to ContactSwift, the desktop app designed for efficient employee contact and task management. Optimized for use via a [Command Line Interface (CLI)](#cli-command-line-interface) while also providing a [Graphical User Interface (GUI)](#gui-graphical-user-interface), ContactSwift is ideal for small business owners, managers, and team leaders who aim to streamline their remote team’s workflows.

This guide will equip you with everything you need to maximize the benefits of ContactSwift, from initial setup to advanced features.

**Key Features:**

- Rapid contact management through intuitive CLI commands.
- Comprehensive task tracking and productivity analysis.
- Advanced filtering and searching capabilities for employee information (even if employees may have some same fields).

**Unique ID System:**
Each contact in ContactSwift is assigned a unique identifier (UID), ensuring precise and efficient management of contact details. This UID is key to performing actions like editing, deleting, or adding tasks for specific contacts. You will encounter these UIDs as you use various commands, offering a streamlined way to manage large datasets. This system is designed to enhance the user experience, enabling you to easily deal with duplicates!

**Who is this for?**
This guide is tailored for small business owners and remote team managers seeking an effective solution to manage contact details and tasks. We assume users have a basic understanding of command-line operations but have structured this guide to be accessible even to those new to CLI environments.

**Purpose of this Guide:**
To help you quickly become proficient with ContactSwift, enabling you to manage your team’s contacts and tasks more effectively and efficiently.

## How to Use This Guide

Navigate through this guide using the [Table of Contents](#table-of-contents). Icons and formatting are used throughout to signify different types of information:

- **Bold** for commands and important terms.
- _Italics_ for notes and additional information.
- `Code` for actual input commands and any technical references to code, filenames, terminology, etc.

Check the [Glossary](#glossary) for explanations of technical terms to ensure a smooth learning experience with ContactSwift.

---

## Table of Contents

1. [Quick Start](#quick-start)
2. [Features](#features)
3. [Managing Your Employees](#managing-your-employees)
4. [FAQ](#faq)
5. [Known Issues](#known-issues)
6. [Command Summary](#command-summary)
7. [Glossary](#glossary)

---

## Quick Start

Embark on your ContactSwift journey with these straightforward steps:

1. **Installation**: Ensure Java `11` or above is installed on your computer. [Learn how to check your Java version](https://www.java.com/en/download/help/version_manual.html).
2. **Download**: Access the latest `contactswift.jar` from our [releases page](https://github.com/AY2324S2-CS2103T-T17-2/tp/releases/tag/v1.3).
3. **Setup**: Select a folder as your home for ContactSwift and move the downloaded file there.
4. **Launch**: Open a command terminal, navigate to your home folder, and initiate the application with `java -jar contactswift.jar`. The GUI, populated with sample data, will appear as shown below:

   ![ContactSwift Main Interface](./images/v1.3.png)  
   _Figure 1: The main interface of ContactSwift, showcasing sample data._

5. **Get Commanding**: Input commands in the command box and press Enter. Try these to get started:
   - `list` – Displays all contacts.
   - `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 T/A r/Manager` – Adds a new contact.
   - `delete uid/101` – Removes the contact with the 'uid' of 101.
   - `edit 2 n/James Lee` – Updates the name of the second contact.
   - `find John` – Searches for contacts with the name `John`.
   - `filter r/Manager` – Filters contacts by role.
   - `addTask uid/1 Complete the report by 5pm` – Adds a task to the contact with the `uid` of 1.
   - `clear` – Deletes all contacts.
   - `exit` – Closes the application.

For a detailed explanation of all commands, refer to the [Features](#features) section.

---

## Features

<box type="info" seamless>

**Understanding the command format is crucial for using ContactSwift effectively. Here are some tips:**<br>

- Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add n/NAME`, `NAME` is a parameter which can be used as `add n/John Doe`.

- Items in square brackets are optional.<br>
  e.g. `n/NAME [t/TAG]` can be used as `n/John Doe t/friend` or as `n/John Doe`.

- Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `t/friend`, `t/friend t/family` etc.

- Parameters can be in any order.<br>
  e.g. if the command specifies `n/NAME p/PHONE_NUMBER`, `p/PHONE_NUMBER n/NAME` is also acceptable.

- Extraneous parameters for commands that do not take in parameters (such as `help`, `list`, `exit` and `clear`) will be ignored.<br>
  e.g. if the command specifies `help 123`, it will be interpreted as `help`.

- If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
  </box>

### Viewing help : `help`

Shows a message explaining how to access the help page.

![help message](images/helpMessage.png)
_Figure: The help command output in ContactSwift._

Format: `help`

### Adding an employee: `add`

Adds an employee to the address book.

Format: `add n/NAME p/PHONE_NUMBER e/EMAIL a/ADDRESS r/ROLE T/TEAM [t/TAG]…​`

<box type="tip" seamless>

**Tip:** An employee can have any number of tags (including 0)
</box>

Examples:

- `add n/John Doe p/98765432 e/johnd@example.com a/John street, block 123, #01-01 T/A r/Manager`
- `add n/Betsy Crowe t/friend e/betsycrowe@example.com a/Newgate Appartment p/1234567 T/B r/Secretary`

<box type="warning">
**Caution:** Ensure the `PHONE_NUMBER` is valid; ContactSwift does not accept phone numbers with less than 3 digits.
</box>

### Add task to an employee's task list: `addtask`

Adds a task to an employee's task list.

Format: `addTask uid/UID DESCRIPTION`

- Adds a task to the employee with the specified `UID`.
- The `UID` refers to the user ID displayed beside the employee's name.
- The description of the task must be provided.
- The description of the task can only contain alphanumeric characters and spaces and cannot be empty.

Examples:

- `addTask uid/1 Complete the report by 5pm`
- `addTask uid/2 Submit the proposal by 10am`

### Mark a task as completed: `mark`

Marks a task as completed in the employee's task list.

Format: `mark uid/UID TASKINDEX`

- Marks the task at the specified `TASKINDEX` as completed for the employee with the specified `uid`.
- The `UID` refers to the user ID displayed beside the employee's name.
- The `TASKINDEX` refers to the index number shown in the displayed task list.
- The `TASKINDEX` **must be a positive integer** 1, 2, 3, …​

Examples:

- `mark uid/1 2` marks the 2nd task in the task list of the employee with the `UID` of 1 as completed.
- `mark uid/2 1` marks the 1st task in the task list of the employee with the `UID` of 2 as completed.

### Unmark a task as completed: `unmark`

Unmarks a task as completed in the employee's task list.

Format: `unmark uid/UID TASKINDEX`

- Unmarks the task at the specified `TASKINDEX` as not completed for the employee with the specified `uid`.
- The `UID` refers to the user ID displayed beside the employee's name.
- The `TASKINDEX` refers to the index number shown in the displayed task list.
- The `TASKINDEX` **must be a positive integer** 1, 2, 3, …​

Examples:

- `unmark uid/1 2` unmarks the 2nd task in the task list of the employee with the `UID` of 1 as not completed.
- `unmark uid/2 1` unmarks the 1st task in the task list of the employee with the `UID` of 2 as not completed.

### Delete a task from an employee's task list: `deleteTask`

Format: `deleteTask uid/UID TASKINDEX`

- Deletes the task at the specified `TASKINDEX` from the task list of the employee with the specified `uid`.
- The `UID` refers to the user ID displayed beside the employee's name.
- The `TASKINDEX` refers to the index number shown in the displayed task list.
- The `TASKINDEX` **must be a positive integer** 1, 2, 3, …​

Examples:

- `deleteTask uid/1 2` deletes the 2nd task in the task list of the employee with the `UID` of 1.
- `deleteTask uid/2 1` deletes the 1st task in the task list of the employee with the `UID` of 2.

### Filter employees by name, tags, roles, or teams: `filter`

Filters the list of employees based on their name, tags, roles, or teams.

Format: `filter [n/NAME] [t/TAG] [r/ROLE] [T/TEAM]`

- Filters the employee list according to the specified criteria.
- At least one of the parameters must be provided.
- Employees matching all provided criteria will be listed (i.e., `AND` search).
- Only single values are allowed for the name, role, and team parameters. Tags can accept multiple values, each preceded by `t/`.
- Name and team fields are case-insensitive, meaning they do not require an exact match of case to filter correctly. For instance, `T/Team HR` is equivalent to `T/team hr`.
- Role and tag fields are case-sensitive, meaning they require an exact match of case to filter correctly. For instance, `r/Manager` will not match `r/manager`.

Examples:

- `filter n/John Doe` : Shows all employees with the name `John Doe`.
- `filter t/friend` : Shows all employees tagged as `friend`.
- `filter r/Manager T/Team HR` : Shows all employees who are managers and belong to the 'HR' team, regardless of how the team name's case is entered.
- `filter t/friend t/Colleague` : Shows all employees tagged as `friend` and `Colleague`, respecting case for tags.
- `filter n/jane doe` : Shows employees named `Jane Doe`, regardless of the case used in the filter.
- `filter T/team marketing` : Shows all employees belonging to the Marketing team, regardless of the case used in the filter.
- `filter r/Executive T/TEAM SALES` : Shows employees with the role `Executive` (exact case match required) and in the 'Sales' team, regardless of how the team name's case is entered.
- `filter t/friends t/remote` : Shows employees tagged as `friends` and `remote`, respecting case for tags.
- `filter t/remote` should produce a similar output as below:
  ![result for 'filter tag remote'](./images/filterTagRemoteResult.png)

### Listing all employees : `list`

Shows a list of all employees in the address book.

Format: `list`

### Editing an employee : `edit`

Edits an existing employee in the address book.

Format: `edit INDEX [n/NAME] [p/PHONE] [e/EMAIL] [a/ADDRESS] [T/TEAM] [r/ROLE] [t/TAG]…​`

- Edits the employee at the specified `INDEX`. The index refers to the index number shown in the displayed employee list. The index **must be a positive integer** 1, 2, 3, …​
- At least one of the optional fields must be provided.
- Existing values will be updated to the input values.
- When editing tags, the existing tags of the employee will be removed i.e. adding of tags is not cumulative.
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

### Deleting an employee : `delete`

Deletes the specified employee from the address book.

Format: `delete INDEX` or `delete uid/UID` or `delete NAME`

- Deletes the employee at the specified `INDEX`/`UID`/`NAME`.
- The `NAME` should not include numbers.
- The index refers to the index number shown in the displayed employee list.
- The index **must be a positive integer** 1, 2, 3, …​ and must be within the range of the displayed list.
- The UID refers to the user ID displayed beside the employee's name.
- The name must be an exact match, however it is case-insensitive.

**Caution:** Deleting an employee is irreversible. Ensure you have selected the correct `INDEX`, `UID`, or `NAME` before proceeding.

Examples:

- `list` followed by `delete 2` deletes the 2nd employee in the address book.
- `delete betsy` deletes the employee with the name `betsy` if there are no duplicates. In the case of duplicates, the user will be prompted to delete by uid.
- `delete uid/101` deletes the employee with the `uid` of 101.

### Clearing all entries : `clear`

Clears all entries from the address book.

Format: `clear`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the Data

ContactSwift data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

**Note:** While ContactSwift attempts to save automatically, it's good practice to regularly back up your data file, especially before making bulk changes or updates.

<box type="warning">
**Caution:** If your changes to the data file make its format invalid, ContactSwift will discard all data and start with an empty data file at the next run. We recommend taking a backup of the file before editing it. Furthermore, certain edits can cause ContactSwift to behave in unexpected ways (e.g., if a value entered is outside the acceptable range). Edit the data file only if you are confident that you can update it correctly.
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
| **Add Task**    | `addTask uid/UID DESCRIPTION` <br> e.g., `addTask uid/1 Complete the report by 5pm`, `addTask uid/2 Submit the proposal by 10am`                                                                  |
| **Clear**       | `clear`                                                                                                                                                                                           |
| **Delete**      | `delete INDEX`/`delete uid/UID`/`delete NAME`<br> e.g., `delete 3`, `delete uid/101`, `delete John Doe`                                                                                           |
| **Delete Task** | `deleteTask uid/UID TASKINDEX` <br> e.g., `deleteTask uid/1 3`                                                                                                                                    |
| **Edit**        | `edit INDEX [n/NAME] [p/PHONE_NUMBER] [e/EMAIL] [a/ADDRESS] [T/TEAM] [r/ROLE] [t/TAG]…​`<br> e.g.,`edit 2 n/James Lee e/jameslee@example.com`                                                     |
| **Exit**        | `exit`                                                                                                                                                                                            |
| **Filter**      | `filter [n/NAME] [t/TAG] [r/ROLE] [T/TEAM]` <br> e.g., `filter t/friend`,`filter r/Manager T/HR`, `filter T/HR t/friend r/Executive`                                                              |
| **Find**        | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find James Jake`                                                                                                                                        |
| **List**        | `list`                                                                                                                                                                                            |
| **Help**        | `help`                                                                                                                                                                                            |
| **Mark Task**   | `mark uid/UID TASKINDEX` <br> e.g., `mark uid/1 3`                                                                                                                                                |
| **Unmark Task** | `unmark uid/UID TASKINDEX` <br> e.g., `unmark uid/1 2`                                                                                                                                            |

---

## Glossary

### CLI (Command Line Interface)

A type of user interface that allows users to interact with a computer program or operating system by typing commands into a console or terminal. CLI is known for its efficiency in performing tasks, enabling users to execute complex commands through concise textual input.

### GUI (Graphical User Interface)

A user interface that allows users to interact with electronic devices through graphical icons and visual indicators, as opposed to text-based interfaces, typed command labels, or text navigation. GUIs are typically considered user-friendly, especially for navigating complex software or managing multiple tasks simultaneously, as they provide a visual representation of the system’s operations.

### Unique Identifier (UID)

UID is a unique identifier assigned to each contact in ContactSwift, enabling precise and efficient management of contact details. Such identifiers serve to differentiate contacts and facilitate actions like editing, deleting, or adding tasks for specific contacts. UIDs are essential for managing large datasets and ensuring accurate contact management.

### Alphanumeric characters

Any combination of the alphabets A-Z and numbers 0-9.

---
