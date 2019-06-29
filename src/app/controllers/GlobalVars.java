package app.controllers;

/**
 * public class used for getting and setting ID's used for retrieving data from the database
 */
public class GlobalVars {
    private static int studentId;
    private static int courseId;
    private static int docentId;
    //TODO variabele naam 'student' was voldoende
    private static boolean studentOrDocent; //true==student, false==docent

    public static int getDocentId() {
        return docentId;
    }

    public static void setDocentId(int docentId) {
        GlobalVars.docentId = docentId;
    }

    public static boolean isStudentOrDocent() {
        return studentOrDocent;
    }

    public static void setStudentOrDocent(boolean studentOrDocent) {
        GlobalVars.studentOrDocent = studentOrDocent;
    }

    public static int getStudentId() {
        return studentId;
    }

    public static void setStudentId(int studentId) {
        GlobalVars.studentId = studentId;
    }

    public static int getCourseId() {
        return courseId;
    }

    public static void setCourseId(int courseId) {
        GlobalVars.courseId = courseId;
    }
}
