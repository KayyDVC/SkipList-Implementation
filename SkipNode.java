/*
Author: Malakai Stanisclaus
Email: Mstanisclaus2019@gmail.com
Description: This is the class declaration and definition for the SkipNodes
used for HW5. It contains a constructor as well as getters and setters for
each variable in the class.
*/

public class SkipNode {
	//declaring pointers for Skip List Functionality
	SkipNode next, prev, upper, lower;

	//data storage for activity and times. Can be viewed as key (time) and
	// value (activity) pair
	String activity;
	Integer dateTime;

	//default constructor
	public SkipNode(String a, Integer dt){
		//date and activity should only be manipulated once: during
		// instantiation; Therefore, they will not have setters
		activity = a;
		dateTime = dt;
		//all pointers should be initialized to null so they don't take
		// create any unwanted empty references
		next = prev = upper = lower = null;
	}

	//getters and setters for pointer refs
	public void setNext(SkipNode n){
		this.next = n;}

	public SkipNode getNext(){
		return this.next;}

	public void setPrev (SkipNode n){
		this.prev = n;}

	public SkipNode getPrev(){
		return this.prev;}

	public void setUpper (SkipNode n){
		this.upper = n;
	}

	public SkipNode getUpper (){
		return this.upper;
	}

	public void setLower (SkipNode n){
		this.lower = n;
	}

	public SkipNode getLower (){
		return this.lower;
	}

	//returns values for activity and dateTime when called

	public String getActivity (){
		return this.activity;
	}

	public Integer getDateTime (){
		return this.dateTime;
	}


}
