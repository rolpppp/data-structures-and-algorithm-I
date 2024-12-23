public class TriageScoreData {
    private String patientFirstName;
    private String patientLastName;
    private int triageScore;

    public TriageScoreData(String pfName, String plName, int triageScore) {
        this.patientFirstName = pfName;
        this.patientLastName = plName;
        this.triageScore = triageScore;
    }

    // Getters and setters
    public String getPatientFirstName() { return patientFirstName; }
    public String getPatientLastName() { return patientLastName; }
    public int getTriageScore() { return triageScore; }
    public void setTriageScore(int triageScore) { this.triageScore = triageScore; }
}