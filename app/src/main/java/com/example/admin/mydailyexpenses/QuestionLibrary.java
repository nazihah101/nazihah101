package com.example.admin.mydailyexpenses;
public class QuestionLibrary {

    public String mQuestions [] = {
            "What you should do if you have RM100?", //q1
            "In your opinion in what way can you earn money?",//q2
            "What is money?",//q3
            "What is Malaysian currency?",//q4
            "Those answers are correct about why we need money, EXCEPT",//q5
            "Which is your priority of your needs?",//q6
            "Where is the safest place to keep our money?",//q7
            "What will you do if you see a beggar?",//q8
            "What will you do if your parents doesn't have enough money?",//q9
            "You have RM5, what is your priority to buy?",//q10
            "In your opinion, what is the important of saving money?"//q11


    };


    private String mChoices [][] = {
            {"Buy A Toy", "Save Money", "Buy Video Games"},//a1
            {"Allowance", "Steal", "Pick Pocket"},//a2
            {"Food", "Toy", "Medium of exchange"},//a3
            {"USD", "RM", "Rupiah"},//a4
            {"Education", "Enjoy", "Living"},//a5
            {"Education & Living", "Shopping", "Entertainment"},//a6
            {"Bag", "Bank", "House"},//a7
            {"Ignore", "Give Some Money", "Laughing"},//a8
            {"Steal from your friends", "Force your parents to give money", "Be understanding & don't waste your money"},//a9
            {"Stationary", "Ice Cream", "Balloon"},//a10
            {"To treat your friends", "To be use in future", "To buy our wants"}//a11

    };



    private String mCorrectAnswers[] = {"Save Money", "Allowance", "Medium of exchange", "RM", "Enjoy", "Education & Living", "Bank", "Give Some Money", "Be understanding & don't waste your money", "Stationary", "To be use in future"};




    public String getQuestion(int a) {
        String question = mQuestions[a];
        return question;
    }


    public String getChoice1(int a) {
        String choice = mChoices[a][0];
        return choice;
    }


    public String getChoice2(int a) {
        String choice = mChoices[a][1];
        return choice;
    }

    public String getChoice3(int a) {
        String choice = mChoices[a][2];
        return choice;
    }

    public String getCorrectAnswer(int a) {
        String answer = mCorrectAnswers[a];
        return answer;
    }

}
