import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class AttendanceManager_1 {
    Scanner sc;
    String[] studentName;
    String[] attendance;
    String username;
    String password;

    public AttendanceManager_1(String[] studentName, String username, String password) {
        this.studentName = studentName;
        this.attendance = new String[studentName.length];
        this.username = username;
        this.password = password;
        this.sc = new Scanner(System.in);
    }

    public boolean authenticate() {
        System.out.println("Enter username:");
        String inputUsername = sc.next();
        sc.nextLine();
        System.out.println("Enter password:");
        String inputPassword = sc.next();
        sc.nextLine();
        return inputUsername.equals(username) && inputPassword.equals(password);
    }

    public void displayMenu() {
        int attempts = 3;
        boolean authenticated = false;
        while (attempts > 0) {
            if (!authenticate()) {
                System.out.println("Authentication failed. Access denied.");
                System.out.println("Enter Correct Username and Password. You have " + attempts + " chances left.");
                attempts--;
            } else {
                System.out.println("Authentication Success. Access Granted.");
                authenticated = true;
                break;
            }
        }

        while (authenticated) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                B.Tech IT Student Attendance");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                    Options                                           ");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                            1. Full Student Name List");
            System.out.println("                            2. Today's Attendance");
            System.out.println("                            3. Final Attendance List");
            System.out.println("                            4. Update Attendance ");
            System.out.println("                            5. Exit the Attendance ");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                 Enter Your Choice                                   ");
            System.out.println("-------------------------------------------------------------------------------------");
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    studentList();
                    break;
                case 2:
                    markAttendance();
                    break;
                case 3:
                    displayAttendance();
                    break;
                case 4:
                    updateAttendance();
                    break;
                case 5:
                    System.out.println("The App will be Closed");
                    authenticated = false;
                    break;
                default:
                    System.out.println("Enter correct option");
                    break;
            }
        }
    }

    public void studentList() {
        System.out.println("                             Your Choice is Full Student Name List                      ");
        System.out.println("-------------------------------------------------------------------------------------");
        for (int i = 0; i < studentName.length; i++) {
            System.out.println((i + 1) + ". " + formatStudentName(studentName[i]));
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    private String formatStudentName(String name) {
        String formattedName = name.replaceAll("^\\s*\\d+\\.?\\s*", "");
        return (formattedName.isEmpty() ? name : formattedName);
    }

    public void markAttendance() {
        System.out.println("                             Your choice is Today's Attendance                       ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("      Present means click 1, Absent means click 0      ");
        System.out.println("-------------------------------------------------------------------------------------");
        for (int i = 0; i < studentName.length; i++) {
            System.out.println(studentName[i]);
            int attendanceInput = sc.nextInt();
            if (attendanceInput == 0) {
                attendance[i] = "A";
            } else if (attendanceInput == 1) {
                attendance[i] = "P";
            } else {
                System.out.println("Invalid input");
                i--;
            }
        }
    }

    public void displayAttendance() {
        System.out.println("                             Your choice is the Final Attendance List                        ");
        System.out.println("-------------------------------------------------------------------------------------");
        for (int i = 0; i < studentName.length; i++) {
            System.out.println(studentName[i] + " - " + (attendance[i] == null ? "Not marked" : (attendance[i].equals("P") ? "Present" : "Absent")));
            System.out.println("-------------------------------------------------------------------------------------");
        }
    }

    public void updateAttendance() {
        System.out.println("                             Your choice is Update Attendance                        ");
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("                             Enter Roll NO to Update the Attendance");
        int change_no = sc.nextInt();
        if (change_no - 1 < attendance.length) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("      Present means click 1, Absent means click 0      ");
            int update_input = sc.nextInt();
            if (update_input == 0) {
                attendance[change_no - 1] = "A";
            } else if (update_input == 1) {
                attendance[change_no - 1] = "P";
            } else {
                System.out.println("Invalid input");
            }
        } else {
            System.out.println("Invalid roll no");
        }
    }
}

class Classroom1 extends AttendanceManager_1 {
    public Classroom1() {
        super(new String[]{
                "1. Alice Smith",
                "2. Bob Johnson",
                "3. Charlie Brown",
                "4. Diana Lee",
                "5. Emma Garcia",
                "6. Frank Wilson",
                "7. Grace Martinez",
                "8. Henry Davis",
                "9. Ivy Taylor",
                "10. Jack Robinson"
        }, "classroom1", "password1");
    }
}

class Classroom2 extends AttendanceManager_1 {
    public Classroom2() {
        super(new String[]{
                "1. Aisha Patel",
                "2. Rahul Sharma",
                "3. Arjun Gupta",
                "4. Devika Desai",
                "5. Priya Singh",
                "6. Vikram Kumar",
                "7. Ananya Mishra",
                "8. Siddharth Choudhary",
                "9. Maya Verma",
                "10. Rajesh Kumar"
        }, "classroom2", "password2");
    }
}

public class Attendance_1 {
    private static final List<AttendanceManager_1> classroomManagers = new ArrayList<>();
    private static final List<String> classroomNames = new ArrayList<>();

    public static void main(String[] args) {
        boolean condition = true;
        Scanner sc = new Scanner(System.in);
        classroomNames.add("Classroom 1");
        classroomNames.add("Classroom 2");
        classroomManagers.add(new Classroom1());
        classroomManagers.add(new Classroom2());

        while (condition) {
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                         Attendance");
            System.out.println("-------------------------------------------------------------------------------------");
            System.out.println("                                    Select Option:");
            System.out.println("                                    1. Enter Classroom Details");
            System.out.println("                                    2. Use Predefined Classroom 1");
            System.out.println("                                    3. Use Predefined Classroom 2");
            System.out.println("                                    4. View All Classroom Names");
            System.out.println("                                    5. Access Newly Created Classroom");
            System.out.println("                                    6. Exit the Attendance");
            System.out.println("-------------------------------------------------------------------------------------");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    System.out.println("                                    Enter Classroom Name:");
                    String classroomName = sc.nextLine();
                    classroomNames.add(classroomName);

                    int numberOfStudents = 0;
                    boolean validInput = false;
                    while (!validInput) {
                        System.out.println("                                    Enter Number of Students:");
                        if (sc.hasNextInt()) {
                            numberOfStudents = sc.nextInt();
                            sc.nextLine();
                            validInput = true;
                        } else {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();
                        }
                    }

                    String[] studentNames = new String[numberOfStudents];
                    for (int i = 0; i < numberOfStudents; i++) {
                        boolean validName = false;
                        while (!validName) {
                            System.out.println("                                    Enter Name for Student " + (i + 1) + ":");
                            String studentName = sc.nextLine();
                            if (isValidStudentName(studentName)) {
                                studentNames[i] = studentName;
                                validName = true;
                            } else {
                                System.out.println("Invalid student name. Please enter a valid name without numbers.");
                            }
                        }
                    }

                    System.out.println("                                    Enter Username for Classroom:");
                    String username = sc.nextLine();

                    System.out.println("                                    Enter Password for Classroom :");
                    String password = sc.nextLine();

                    AttendanceManager_1 newClassroom = new AttendanceManager_1(studentNames, username, password);
                    classroomManagers.add(newClassroom);
                    System.out.println("Classroom created successfully! Returning to main menu.");
                    break;
                case 2:
                    classroomManagers.get(0).displayMenu();
                    break;
                case 3:
                    classroomManagers.get(1).displayMenu();
                    break;
                case 4:
                    displayClassroomNames();
                    break;
                case 5:
                    if (classroomManagers.size() > 2) {
                        System.out.println("Select Classroom to Access:");
                        for (int i = 2; i < classroomManagers.size(); i++) {
                            System.out.println((i - 1) + ". " + classroomNames.get(i));
                        }
                        int classroomChoice = 1 +sc.nextInt();
                        sc.nextLine();
                        if (classroomChoice >= 2 && classroomChoice < classroomManagers.size()) {
                            classroomManagers.get(classroomChoice).displayMenu();
                        } else {
                            System.out.println("Invalid choice.");
                        }
                    } else {
                        System.out.println("No newly created classrooms to access.");
                    }
                    break;
                case 6:
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("Exit the Attendance ");
                    System.out.println("-------------------------------------------------------------------------------------");
                    condition = false;
                    break;
                default:
                    System.out.println("-------------------------------------------------------------------------------------");
                    System.out.println("Enter correct option");
                    System.out.println("-------------------------------------------------------------------------------------");
                    break;
            }

            if (condition) {
                System.out.println("                                    Do you want to continue? (yes/no):");
                String continueChoice = sc.nextLine();
                condition = continueChoice.equalsIgnoreCase("yes");
            }
        }
        sc.close();
    }

    public static void displayClassroomNames() {
        System.out.println("-------------------------------------------------------------------------------------");
        System.out.println("                              Classroom Names                                         ");
        System.out.println("-------------------------------------------------------------------------------------");
        for (String name : classroomNames) {
            System.out.println(name);
        }
        System.out.println("-------------------------------------------------------------------------------------");
    }

    private static boolean isValidStudentName(String name) {
        return name.matches("[a-zA-Z\\s]+");
    }
}
