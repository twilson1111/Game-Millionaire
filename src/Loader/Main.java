package Loader;

import Database.MillionaireConnection;

public class Main {

    public static void main(String[] args) {
        
        MillionaireConnection connection = new MillionaireConnection();

        QuestionLoader loader = new QuestionLoader(connection);
        
        for (Question q : loader.getRandom(5)) {
            System.out.println(q);
        }
        
    }
}
