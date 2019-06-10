public class GlobalVars {
    private static int studentId;
    private static int id;
    private static boolean studentOrDocent; //true==student, false==docent
    private static int docentId;

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

    public static int getId() {
        return id;
    }

    public static void setId(int id) {
        GlobalVars.id = id;
    }
}
