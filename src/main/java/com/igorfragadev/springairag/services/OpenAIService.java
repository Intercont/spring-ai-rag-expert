package com.igorfragadev.springairag.services;


import com.igorfragadev.springairag.model.Answer;
import com.igorfragadev.springairag.model.Question;

public interface OpenAIService {

    Answer getAnswer(Question question);

}
