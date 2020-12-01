package edu.niu.z1864126.assignment8;

public class homeworkItem {
    private String descOfTask;
    private int ID;
    private boolean completed;

    public homeworkItem(String desc, int age){
        this.descOfTask = desc;
        this.ID = age;
        this.completed = false;
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
