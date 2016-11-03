package sofia.fmi.todolist;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ProblemOperator {
	
	private List<Problem> problems;
	
	public ProblemOperator(){
		problems = new ArrayList<>();
		generate();
	}
	private void generate(){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-M-d");
		problems.add(new Problem("Problem1", "Description1", LocalDate.parse("2016-12-30", dtf)));
		problems.add(new Problem("Problem2", "Description2", LocalDate.parse("2016-11-5", dtf), 2));
		problems.add(new Problem("Problem3", "Description3", LocalDate.parse("2017-12-30", dtf)));
		problems.add(new Problem("Problem4", "Description4", LocalDate.parse("2018-6-5", dtf), 1));
		problems.add(new Problem("Problem5", "Description5", LocalDate.parse("2019-12-31", dtf)));
		problems.add(new Problem("Problem6", "Description6", LocalDate.parse("2020-1-20", dtf), 3));
		problems.add(new Problem("Problem7", "Description7", LocalDate.parse("2016-12-29", dtf)));
		
		problems.get(3).setStatus(ProblemState.DONE);
		problems.get(0).setStatus(ProblemState.INPROCESS);
		problems.get(4).setStatus(ProblemState.INPROCESS);
	}
	
	public void execute() {
		boolean stopper = false;
		Scanner scanner = new Scanner(System.in);
		while (!stopper) {
			System.out.println("Изберете опция:");
			System.out.println("1) Всички задачи подредени по приоритет");
			System.out.println("2) Задачи със статус IN PROCESS");
			System.out.println("3) Задачи, които да се завършат в следващите три дни");
			System.out.println("4) Изход");

			Collections.sort(problems);
			int option = scanner.nextInt();

			try {
				switch (option) {
				case 1: {
					print();
					break;
				}
				case 2: {
					printByState(ProblemState.INPROCESS);
					break;
				}
				case 3: {
					printByDaysLeft(3);
					break;
				}

				case 4: {
					stopper = true;
					break;
				}
				default:
					throw new Exception("Invalid command...");
				}
			} catch (Exception e) {
				System.out.println(e.getMessage());
				continue;
			}
		}
	}
	
	private void print(){
		for(Problem problem : problems){
			problem.print();
		}
	}
	
	private void printByDaysLeft(int days){
		for(Problem problem : problems){
			if(problem.isFinishInDays(days) && (problem.getState().equals(ProblemState.INITIAL) || 
				problem.getState().equals(ProblemState.INPROCESS))){
				problem.print();
			}
		}
	}
	
	private void printByState(ProblemState state){
		for(Problem problem : problems){
			if(problem.getState().equals(state)){
				problem.print();
			}
		}
	}
	
}
