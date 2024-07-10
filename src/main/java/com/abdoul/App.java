package com.abdoul;

import java.sql.Date;
import java.util.List;
import java.util.Scanner;

import com.abdoul.dao.CourseDAO;
import com.abdoul.dao.DepartmentDAO;
import com.abdoul.dao.SemesterDAO;
import com.abdoul.dao.StudentCoursesDAO;
import com.abdoul.dao.StudentDAO;
import com.abdoul.dao.StudentRegistrationDAO;
import com.abdoul.models.Course;
import com.abdoul.models.Department;
import com.abdoul.models.Semester;
import com.abdoul.models.Student;
import com.abdoul.models.StudentCourses;
import com.abdoul.models.StudentRegistration;

public class App {
    private static StudentDAO studentDAO = new StudentDAO();
    private static SemesterDAO semesterDAO = new SemesterDAO();
    private static DepartmentDAO departmentDAO = new DepartmentDAO();
    private static CourseDAO courseDAO = new CourseDAO();
    private static StudentCoursesDAO studentCoursesDAO = new StudentCoursesDAO();
    private static StudentRegistrationDAO studentRegistrationDAO = new StudentRegistrationDAO();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Menu:");
            System.out.println("1. Save Course");
            System.out.println("2. Save Department");
            System.out.println("3. Save Semester");
            System.out.println("4. Save Student");
            System.out.println("5. Register Student");
            System.out.println("6. Save Student Score");
            System.out.println("7. Retrieve All Courses");
            System.out.println("8. Retrieve All Departments");
            System.out.println("9. Retrieve All Semesters");
            System.out.println("10. Retrieve All Students");
            System.out.println("11. Retrieve All Student Registrations");
            System.out.println("12. Retrieve Student Total Marks");
            System.out.println("13. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    saveCourse(scanner);
                    break;
                case 2:
                    saveDepartment(scanner);
                    break;
                case 3:
                    saveSemester(scanner);
                    break;
                case 4:
                    saveStudent(scanner);
                    break;
                case 5:
                    registerStudent(scanner);
                    break;
                case 6:
                    saveStudentScore(scanner);
                    break;
                case 7:
                    retrieveAllCourses();
                    break;
                case 8:
                    retrieveAllDepartments();
                    break;
                case 9:
                    retrieveAllSemesters();
                    break;
                case 10:
                    retrieveAllStudents();
                    break;
                case 11:
                    retrieveAllStudentRegistrations();
                    break;
                case 12:
                    retrieveStudentTotalMarks(scanner);
                    break;
                case 13:
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void saveCourse(Scanner scanner) {
        System.out.print("Enter course name: ");
        String courseName = scanner.nextLine();
        System.out.print("Enter course code: ");
        String courseCode = scanner.nextLine();
        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();
        Semester semester = semesterDAO.getSemesterByName(semesterName);
        Course course = courseDAO.saveCourse(courseName, courseCode, semester);
        if (course != null) {
            System.out.println("Course saved successfully.");
        } else {
            System.out.println("Failed to save course.");
        }
    }

    private static void saveDepartment(Scanner scanner) {
        System.out.print("Enter department name: ");
        String departmentName = scanner.nextLine();
        System.out.print("Enter department code: ");
        String departmentCode = scanner.nextLine();
        Department department = departmentDAO.saveDepartment(departmentName, departmentCode);
        if (department != null) {
            System.out.println("Department saved successfully.");
        } else {
            System.out.println("Failed to save department.");
        }
    }

    private static void saveSemester(Scanner scanner) {
        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();
        System.out.print("Enter start date (yyyy-mm-dd): ");
        String startDateStr = scanner.nextLine();
        System.out.print("Enter end date (yyyy-mm-dd): ");
        String endDateStr = scanner.nextLine();
        Date startDate = Date.valueOf(startDateStr);
        Date endDate = Date.valueOf(endDateStr);
        Semester semester = semesterDAO.saveSemester(semesterName, startDate, endDate);
        if (semester != null) {
            System.out.println("Semester saved successfully.");
        } else {
            System.out.println("Failed to save semester.");
        }
    }

    private static void saveStudent(Scanner scanner) {
        System.out.print("Enter first name: ");
        String firstName = scanner.nextLine();
        System.out.print("Enter last name: ");
        String lastName = scanner.nextLine();
        System.out.print("Enter date of birth (yyyy-mm-dd): ");
        String dobStr = scanner.nextLine();
        System.out.print("Enter gender (M/F): ");
        char gender = scanner.nextLine().charAt(0);
        Date dob = Date.valueOf(dobStr);
        Student student = studentDAO.saveStudent(firstName, lastName, dob, gender);
        if (student != null) {
            System.out.println("Student saved successfully.");
        } else {
            System.out.println("Failed to save student.");
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter registration number: ");
        int registrationNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("Enter student first name: ");
        String studentFirstName = scanner.nextLine();
        System.out.print("Enter student last name: ");
        String studentLastName = scanner.nextLine();
        Student student = studentDAO.getByFirstNameAndLastName(studentFirstName, studentLastName);
        System.out.print("Enter semester name: ");
        String semesterName = scanner.nextLine();
        Semester semester = semesterDAO.getSemesterByName(semesterName);
        System.out.print("Enter department code: ");
        String departmentCode = scanner.nextLine();
        Department department = departmentDAO.getDepartmentByCode(departmentCode);
        studentRegistrationDAO.registerStudent(registrationNumber, student, semester, department);
        System.out.println("Student registered successfully.");
    }

    private static void saveStudentScore(Scanner scanner) {
        System.out.print("Enter registration number: ");
        int registrationNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(registrationNumber);

        System.out.print("Enter course code for CS101: ");
        String courseCode1 = scanner.nextLine();
        Course course1 = courseDAO.getCourseByCode(courseCode1);
        StudentCourses studentCourse1 = studentCoursesDAO.saveStudentCourse(course1, 20, registration);
        System.out.println("Saved course: " + courseCode1 + " with score 20");

        System.out.print("Enter course code for CS102: ");
        String courseCode2 = scanner.nextLine();
        Course course2 = courseDAO.getCourseByCode(courseCode2);
        StudentCourses studentCourse2 = studentCoursesDAO.saveStudentCourse(course2, 18, registration);
        System.out.println("Saved course: " + courseCode2 + " with score 18");

        System.out.print("Enter course code for CS103: ");
        String courseCode3 = scanner.nextLine();
        Course course3 = courseDAO.getCourseByCode(courseCode3);
        StudentCourses studentCourse3 = studentCoursesDAO.saveStudentCourse(course3, 22, registration);
        System.out.println("Saved course: " + courseCode3 + " with score 22");

        System.out.print("Enter course code for CS104: ");
        String courseCode4 = scanner.nextLine();
        Course course4 = courseDAO.getCourseByCode(courseCode4);
        StudentCourses studentCourse4 = studentCoursesDAO.saveStudentCourse(course4, 19, registration);
        System.out.println("Saved course: " + courseCode4 + " with score 19");

        System.out.print("Enter course code for CS105: ");
        String courseCode5 = scanner.nextLine();
        Course course5 = courseDAO.getCourseByCode(courseCode5);
        StudentCourses studentCourse5 = studentCoursesDAO.saveStudentCourse(course5, 21, registration);
        System.out.println("Saved course: " + courseCode5 + " with score 21");
    }

    private static void retrieveAllCourses() {
        List<Course> courses = courseDAO.getAllCourses();
        for (Course course : courses) {
            System.out.println(course);
        }
    }

    private static void retrieveAllDepartments() {
        List<Department> departments = departmentDAO.getAllDepartments();
        for (Department department : departments) {
            System.out.println(department);
        }
    }

    private static void retrieveAllSemesters() {
        List<Semester> semesters = semesterDAO.getAllSemesters();
        for (Semester semester : semesters) {
            System.out.println(semester);
        }
    }

    private static void retrieveAllStudents() {
        List<Student> students = studentDAO.getAllStudents();
        for (Student student : students) {
            System.out.println(student);
       
        }
    }

    private static void retrieveAllStudentRegistrations() {
        List<StudentRegistration> registrations = studentRegistrationDAO.getAllStudentRegistrations();
        for (StudentRegistration registration : registrations) {
            System.out.println(registration);
        }
    }

    private static void retrieveStudentTotalMarks(Scanner scanner) {
        System.out.print("Enter registration number: ");
        int registrationNumber = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        StudentRegistration registration = studentRegistrationDAO.getByRegistrationNumber(registrationNumber);
        int totalMarks = studentCoursesDAO.getTotalMarks(registration);
        System.out.println("Total marks for student with registration number " + registrationNumber + ": " + totalMarks);
    }
}
