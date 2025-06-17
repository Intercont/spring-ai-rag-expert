package com.igorfragadev.springairag.services;

import com.igorfragadev.springairag.model.Answer;
import com.igorfragadev.springairag.model.Question;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.ai.chat.model.ChatResponse;
import org.springframework.ai.chat.prompt.Prompt;
import org.springframework.ai.chat.prompt.PromptTemplate;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.SimpleVectorStore;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class OpenAIServiceImpl implements OpenAIService {

    private final ChatModel chatModel;
    private final SimpleVectorStore vectorStore;

    @Value("classpath:templates/rag-prompt-metadata-template.st")
    private Resource ragPromptTemplate;

    @Override
    public Answer getAnswer(Question question) {

        //setting the query to the vector store with similarity search
        List<Document> documents = vectorStore.similaritySearch(
                SearchRequest.builder()
                        .query(question.question())
                        .topK(4) //scope of the search - how many documents to return - bigger uses more tokens
                        .build());

        //List of strings from the filtered documents
        List<String> content = documents.stream()
                .map(Document::getText)
                .toList();

        PromptTemplate promptTemplate = new PromptTemplate(ragPromptTemplate);
        Prompt prompt = promptTemplate.create(Map.of("input", question.question(),
                "documents", String.join("\n", content))); //map the variable from the template into the prompt with the desired received value

        content.forEach(System.out::println);

        ChatResponse response = chatModel.call(prompt);
        return new Answer(response.getResult().getOutput().getText());
    }
}
