/******************************************************************************
 *                                                                           *
 *    Class Name: homeworkItem.java                                          *
 *                                                                           *
 *           Purpose: A class used to hold one user entry                    *
 *           from a "To-do" list/database entry                              *
 *           Created By Brendon Brewer and Eric Kirchman                     *
 *                                                                           *
 *****************************************************************************/
package edu.niu.z1864126.assignment8;

public class homeworkItem {

    //Declaration of initial variable
    private String descOfTask; //Holds the task to be done
    private int ID; //Holds ID of task
    private boolean completed; //Holds complete/on-complete (true/false) state of task

    /*homeworkItem: Constructor of homeworkItem; Initializes object's ID,task, and completion status*/
    public homeworkItem(String desc, int id)
    {
        this.descOfTask = desc;
        this.ID = id;
        this.completed = false;
    }

    /*homeworkItem: Cosntructor of homeworkItem when no parameters are given*/
    public homeworkItem(){

    }

    //get methods
    public String getDescOfTask() {
        return descOfTask;
    }

    public int getID() {
        return ID;
    }

    public boolean isCompleted(){
        return completed;
    }

    //set methods
    public void setDescOfTask(String descOfTask) {
        this.descOfTask = descOfTask;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public void setCompleted(boolean completed){
        this.completed = completed;
    }
}
