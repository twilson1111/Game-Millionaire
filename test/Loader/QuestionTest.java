/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package Loader;

import Database.QA;
import org.junit.Test;

/**
 *
 * @author Xu Duo
 */
public class QuestionTest {

    @Test
    public void CreationTest() {
        QA qa = new QA(
                1,
                "Test question",
                "correct",
                "wrong",
                "wrong",
                "wrong",
                "wrong",
                null
        );
        
        Question ques = new Question(qa);
        
        System.out.println(ques);
    }

}
