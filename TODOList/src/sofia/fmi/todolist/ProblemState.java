package sofia.fmi.todolist;

public enum ProblemState {
	INITIAL ("Initial"),
	INPROCESS ("In progress"),
	DONE ("Done");
	
	private final String name;       

    private ProblemState(String s) {
        name = s;
    }

    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
       return this.name;
    }
}
