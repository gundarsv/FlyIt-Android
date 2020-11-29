package com.flyit.application.repositories;

import com.flyit.application.models.QuizAnswer;
import com.flyit.application.models.QuizQuestion;

import java.util.ArrayList;
import java.util.List;

public class TriviaGameQuestionRepository {

    private static TriviaGameQuestionRepository triviaGameQuestionRepository = null;

    private TriviaGameQuestionRepository()
    {

    }

    public static TriviaGameQuestionRepository getTriviaGameQuestionRepository()
    {
        if(triviaGameQuestionRepository == null)
        {
            triviaGameQuestionRepository = new TriviaGameQuestionRepository();
        }
        return triviaGameQuestionRepository;
    }

    public List<QuizQuestion> getQuizQuestions()
    {

        //Question1
        QuizAnswer quizAnswer11 = new QuizAnswer("Transylvania", true );
        QuizAnswer quizAnswer12 = new QuizAnswer("Moldova", false );
        QuizAnswer quizAnswer13 = new QuizAnswer("Wallachia", false );
        QuizAnswer quizAnswer14 = new QuizAnswer("Dobrogea", false );
        List<QuizAnswer> answersQuestion1 = new ArrayList<>();
        answersQuestion1.add(quizAnswer11);
        answersQuestion1.add(quizAnswer12);
        answersQuestion1.add(quizAnswer13);
        answersQuestion1.add(quizAnswer14);

        //Question2
        QuizAnswer quizAnswer21 = new QuizAnswer("Copenhagen", false );
        QuizAnswer quizAnswer22 = new QuizAnswer("Billund", true );
        QuizAnswer quizAnswer23 = new QuizAnswer("Aalborg", false );
        QuizAnswer quizAnswer24 = new QuizAnswer("Aarhus", false );
        List<QuizAnswer> answersQuestion2 = new ArrayList<>();
        answersQuestion2.add(quizAnswer21);
        answersQuestion2.add(quizAnswer22);
        answersQuestion2.add(quizAnswer23);
        answersQuestion2.add(quizAnswer24);

        //Question3
        QuizAnswer quizAnswer31 = new QuizAnswer("Germany", false );
        QuizAnswer quizAnswer32 = new QuizAnswer("Andorra", false );
        QuizAnswer quizAnswer33 = new QuizAnswer("Italy", false );
        QuizAnswer quizAnswer34 = new QuizAnswer("Austria", true );
        List<QuizAnswer> answersQuestion3 = new ArrayList<>();
        answersQuestion3.add(quizAnswer31);
        answersQuestion3.add(quizAnswer32);
        answersQuestion3.add(quizAnswer33);
        answersQuestion3.add(quizAnswer34);

        //Question4
        QuizAnswer quizAnswer41 = new QuizAnswer("Romania", false );
        QuizAnswer quizAnswer42 = new QuizAnswer("Latvia", true );
        QuizAnswer quizAnswer43 = new QuizAnswer("Russia", false );
        QuizAnswer quizAnswer44 = new QuizAnswer("Norway", false );
        List<QuizAnswer> answersQuestion4 = new ArrayList<>();
        answersQuestion4.add(quizAnswer41);
        answersQuestion4.add(quizAnswer42);
        answersQuestion4.add(quizAnswer43);
        answersQuestion4.add(quizAnswer44);

        //Question5
        QuizAnswer quizAnswer51 = new QuizAnswer("The Alps", true );
        QuizAnswer quizAnswer52 = new QuizAnswer("The Andes", false );
        QuizAnswer quizAnswer53 = new QuizAnswer("The Urals", false );
        QuizAnswer quizAnswer54 = new QuizAnswer("The Himalayas", false );
        List<QuizAnswer> answersQuestion5 = new ArrayList<>();
        answersQuestion5.add(quizAnswer51);
        answersQuestion5.add(quizAnswer52);
        answersQuestion5.add(quizAnswer53);
        answersQuestion5.add(quizAnswer54);

        //Question6
        QuizAnswer quizAnswer61 = new QuizAnswer("Norway", false );
        QuizAnswer quizAnswer62 = new QuizAnswer("Greenland", false );
        QuizAnswer quizAnswer63 = new QuizAnswer("Iceland", true );
        QuizAnswer quizAnswer64 = new QuizAnswer("Russia", false );
        List<QuizAnswer> answersQuestion6 = new ArrayList<>();
        answersQuestion6.add(quizAnswer61);
        answersQuestion6.add(quizAnswer62);
        answersQuestion6.add(quizAnswer63);
        answersQuestion6.add(quizAnswer64);

        //Question7
        QuizAnswer quizAnswer71 = new QuizAnswer("France", false );
        QuizAnswer quizAnswer72 = new QuizAnswer("Italy", true );
        QuizAnswer quizAnswer73 = new QuizAnswer("Spain", false );
        QuizAnswer quizAnswer74 = new QuizAnswer("Greece", false );
        List<QuizAnswer> answersQuestion7 = new ArrayList<>();
        answersQuestion7.add(quizAnswer71);
        answersQuestion7.add(quizAnswer72);
        answersQuestion7.add(quizAnswer73);
        answersQuestion7.add(quizAnswer74);

        //Question8
        QuizAnswer quizAnswer81 = new QuizAnswer("Circle", false );
        QuizAnswer quizAnswer82 = new QuizAnswer("Square", false );
        QuizAnswer quizAnswer83 = new QuizAnswer("Eagle", false );
        QuizAnswer quizAnswer84 = new QuizAnswer("Cross", true );
        List<QuizAnswer> answersQuestion8 = new ArrayList<>();
        answersQuestion8.add(quizAnswer81);
        answersQuestion8.add(quizAnswer82);
        answersQuestion8.add(quizAnswer83);
        answersQuestion8.add(quizAnswer84);

        //Question9
        QuizAnswer quizAnswer91 = new QuizAnswer("Canada", false );
        QuizAnswer quizAnswer92 = new QuizAnswer("Russia", false );
        QuizAnswer quizAnswer93 = new QuizAnswer("Denmark", true );
        QuizAnswer quizAnswer94 = new QuizAnswer("United States", false );
        List<QuizAnswer> answersQuestion9 = new ArrayList<>();
        answersQuestion9.add(quizAnswer91);
        answersQuestion9.add(quizAnswer92);
        answersQuestion9.add(quizAnswer93);
        answersQuestion9.add(quizAnswer94);

        //Question10
        QuizAnswer quizAnswer101 = new QuizAnswer("Forints", false );
        QuizAnswer quizAnswer102 = new QuizAnswer("Deutsche Marks", true );
        QuizAnswer quizAnswer103 = new QuizAnswer("Kroner", false );
        QuizAnswer quizAnswer104 = new QuizAnswer("Leva", false );
        List<QuizAnswer> answersQuestion10 = new ArrayList<>();
        answersQuestion10.add(quizAnswer101);
        answersQuestion10.add(quizAnswer102);
        answersQuestion10.add(quizAnswer103);
        answersQuestion10.add(quizAnswer104);

        //Question11
        QuizAnswer quizAnswer111 = new QuizAnswer("Poland", true );
        QuizAnswer quizAnswer112 = new QuizAnswer("Spain", false );
        QuizAnswer quizAnswer113 = new QuizAnswer("Sweeden", false );
        QuizAnswer quizAnswer114 = new QuizAnswer("Czechia", false );
        List<QuizAnswer> answersQuestion11 = new ArrayList<>();
        answersQuestion11.add(quizAnswer111);
        answersQuestion11.add(quizAnswer112);
        answersQuestion11.add(quizAnswer113);
        answersQuestion11.add(quizAnswer114);

        //Question12
        QuizAnswer quizAnswer121 = new QuizAnswer("Germany", true );
        QuizAnswer quizAnswer122 = new QuizAnswer("Spain", false );
        QuizAnswer quizAnswer123 = new QuizAnswer("Finland", false );
        QuizAnswer quizAnswer124 = new QuizAnswer("Greece", false );
        List<QuizAnswer> answersQuestion12 = new ArrayList<>();
        answersQuestion12.add(quizAnswer121);
        answersQuestion12.add(quizAnswer122);
        answersQuestion12.add(quizAnswer123);
        answersQuestion12.add(quizAnswer124);

        //Question13
        QuizAnswer quizAnswer131 = new QuizAnswer("Netherlands", false );
        QuizAnswer quizAnswer132 = new QuizAnswer("Austria", false );
        QuizAnswer quizAnswer133 = new QuizAnswer("Belgium", false );
        QuizAnswer quizAnswer134 = new QuizAnswer("Poland", true );
        List<QuizAnswer> answersQuestion13 = new ArrayList<>();
        answersQuestion13.add(quizAnswer131);
        answersQuestion13.add(quizAnswer132);
        answersQuestion13.add(quizAnswer133);
        answersQuestion13.add(quizAnswer134);

        //Question14
        QuizAnswer quizAnswer141 = new QuizAnswer("Germany", false );
        QuizAnswer quizAnswer142 = new QuizAnswer("Ukraine", false );
        QuizAnswer quizAnswer143 = new QuizAnswer("France", true );
        QuizAnswer quizAnswer144 = new QuizAnswer("Poland", false );
        List<QuizAnswer> answersQuestion14 = new ArrayList<>();
        answersQuestion14.add(quizAnswer141);
        answersQuestion14.add(quizAnswer142);
        answersQuestion14.add(quizAnswer143);
        answersQuestion14.add(quizAnswer144);

        //Question15
        QuizAnswer quizAnswer151 = new QuizAnswer("San Marino", true );
        QuizAnswer quizAnswer152 = new QuizAnswer("Monaco", false );
        QuizAnswer quizAnswer153 = new QuizAnswer("Andorra", false );
        QuizAnswer quizAnswer154 = new QuizAnswer("Italy", false );
        List<QuizAnswer> answersQuestion15 = new ArrayList<>();
        answersQuestion15.add(quizAnswer151);
        answersQuestion15.add(quizAnswer152);
        answersQuestion15.add(quizAnswer153);
        answersQuestion15.add(quizAnswer154);

        QuizQuestion quizQuestion1 = new QuizQuestion("From which region of Romania do vampyires come?", answersQuestion1, "https://pngimg.com/uploads/vampire/vampire_PNG35.png");
        QuizQuestion quizQuestion2 = new QuizQuestion("In which Danish town was Lego founded?", answersQuestion2, "https://lh3.googleusercontent.com/proxy/ozef-8bJJLi43p04MKxxEGcfycMdaTGGsTg_mOU6LdXZNZ8LPrOW4QY-f4qkSwdhMz6bUpOqYFCtmhNxWeYhgK5q5BpjVur8RwIe7cRIE7unj2UzvmSa");
        QuizQuestion quizQuestion3 = new QuizQuestion("Which country does NOT border France?", answersQuestion3, "https://www.pngarts.com/files/1/France-PNG-Free-Download.png");
        QuizQuestion quizQuestion4 = new QuizQuestion("In which country was the first Christmas tree decorated, in the year 1510?", answersQuestion4, "https://www.seekpng.com/png/full/41-412919_christmas-tree-clipart-funny-christmas-tree-png-cartoon.png");
        QuizQuestion quizQuestion5 = new QuizQuestion("Which mountain range is found in Switzerland?", answersQuestion5, "https://pngimg.com/uploads/mountain/mountain_PNG10.png");
        QuizQuestion quizQuestion6 = new QuizQuestion("What country is called \"Land of Fire and Ice\"?", answersQuestion6, "https://images.vexels.com/media/users/3/146886/isolated/preview/82d967ff391133a0d4380497cc16d75b-fire-clipart-by-vexels.png");
        QuizQuestion quizQuestion7 = new QuizQuestion("Spaghetti, lasagna, ravioli, and cannelloni are typical foods found in the cuisine of which country?", answersQuestion7, "https://cdn4.iconfinder.com/data/icons/nutrition-big-set/100/nutrition-56-512.png");
        QuizQuestion quizQuestion8 = new QuizQuestion("What shape is in the center of Switzerland's flag?", answersQuestion8, "https://www.rawshorts.com/freeicons/wp-content/uploads/2017/01/red_webpict50_1484337222-1.png");
        QuizQuestion quizQuestion9 = new QuizQuestion("What country is split into 443 named islands and also holds the territory of Greenland?", answersQuestion9, "https://pngimg.com/uploads/question_mark/question_mark_PNG129.png");
        QuizQuestion quizQuestion10 = new QuizQuestion("Before changing to Euros, what currency was used in Germany?", answersQuestion10, "https://www.searchpng.com/wp-content/uploads/2019/02/Money-Clipart-PNG.png");
        QuizQuestion quizQuestion11 = new QuizQuestion("What country's cuisine includes dishes such as red beet soup, kielbasa, and pierogi (dumplings)?", answersQuestion11, "https://lh3.googleusercontent.com/proxy/oH1tw42oMKTqpYHLr-dMaBL0bSNmU2PwOw7vXXtxerc8UmUIoPJVQPQh_T48pOYodRTT1FNmlk2fTJZzV0t0Rh6mV9EBtBEWVkc4hSq4AHolhnHwJV-UHH2-5HZo1fpvcSU");
        QuizQuestion quizQuestion12 = new QuizQuestion("The Rhine River runs through what country?", answersQuestion12, "http://clipart-library.com/img1/1499332.png");
        QuizQuestion quizQuestion13 = new QuizQuestion("What country is bordered by Germany, the Czech Republic, Slovakia and the Ukraine?", answersQuestion13, "http://clipart-library.com/image_gallery/246832.gif");
        QuizQuestion quizQuestion14 = new QuizQuestion("What European country is divided into departments?", answersQuestion14, "http://clipart-library.com/data_images/105640.png");
        QuizQuestion quizQuestion15 = new QuizQuestion("Which of these countries is considered the world’s oldest republic?", answersQuestion15, "https://image.flaticon.com/icons/png/512/675/675240.png");
        List<QuizQuestion> listOfQuestions = new ArrayList<>();
        listOfQuestions.add(quizQuestion1);
        listOfQuestions.add(quizQuestion2);
        listOfQuestions.add(quizQuestion3);
        listOfQuestions.add(quizQuestion4);
        listOfQuestions.add(quizQuestion5);
        listOfQuestions.add(quizQuestion6);
        listOfQuestions.add(quizQuestion7);
        listOfQuestions.add(quizQuestion8);
        listOfQuestions.add(quizQuestion9);
        listOfQuestions.add(quizQuestion10);
        listOfQuestions.add(quizQuestion11);
        listOfQuestions.add(quizQuestion12);
        listOfQuestions.add(quizQuestion13);
        listOfQuestions.add(quizQuestion14);
        listOfQuestions.add(quizQuestion15);
        return listOfQuestions;

    }



}
