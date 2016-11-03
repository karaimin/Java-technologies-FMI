package sofia.fmi.todolist;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Problem implements Comparable<Problem>{
	private String title;
	private String description;
	private LocalDate deadline;
	private int priority;
	private ProblemState state;
	
	public Problem(String title, String description, LocalDate deadline) {
		this(title,description,deadline,1);
	}

	public Problem(String title, String description, LocalDate deadline, int priority) {
		this(title,description,deadline,priority,ProblemState.INITIAL);
	}
	
	public Problem(String title, String description, LocalDate deadline, int priority, ProblemState status) {
		this.title = title;
		this.description = description;
		this.deadline = deadline;
		this.priority = priority;
		this.state = status;
	}
	
	public void setPriority(int priority){
		this.priority = priority;
	}
	
	public void setStatus(ProblemState status){
		this.state = status;
	}
	
	public void print(){
		System.out.println(String.format("Title: %s | Deadline: %s | Priority: %s | Status: %s",
				title, deadline, priority, state.toString()));
	}
	
	public ProblemState getState(){
		return state;
	}
	
	public boolean isFinishInDays(int days){
		LocalDate currentDate = LocalDate.now();
		return ChronoUnit.DAYS.between(currentDate, deadline) < days;
	}

	@Override
	public int compareTo(Problem o) {
		return priority - o.priority;
	}
	
	
}
