package com.hfad.joke.quizapplication;

/**
 * Created by serpentcs on 14/7/16.
 */
public class Question {

    private int ID;

    private String QUESTION;

    private String OPTION_ONE;

    private String OPTION_TWO;

    private String ANSWER;


    public Question() {
    }

    public Question(String QUESTION, String OPTION_ONE, String OPTION_TWO, String ANSWER) {

        this.QUESTION = QUESTION;
        this.OPTION_ONE = OPTION_ONE;
        this.OPTION_TWO = OPTION_TWO;
        this.ANSWER = ANSWER;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getQUESTION() {
        return QUESTION;
    }

    public void setQUESTION(String QUESTION) {
        this.QUESTION = QUESTION;
    }

    public String getOPTION_ONE() {
        return OPTION_ONE;
    }

    public void setOPTION_ONE(String OPTION_ONE) {
        this.OPTION_ONE = OPTION_ONE;
    }

    public String getOPTION_TWO() {
        return OPTION_TWO;
    }

    public void setOPTION_TWO(String OPTION_TWO) {
        this.OPTION_TWO = OPTION_TWO;
    }

    public String getANSWER() {
        return ANSWER;
    }

    public void setANSWER(String ANSWER) {
        this.ANSWER = ANSWER;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
