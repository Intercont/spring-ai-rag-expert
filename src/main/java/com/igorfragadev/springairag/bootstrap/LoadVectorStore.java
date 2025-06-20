package com.igorfragadev.springairag.bootstrap;

import com.igorfragadev.springairag.config.VectorStoreProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.document.Document;
import org.springframework.ai.reader.tika.TikaDocumentReader;
import org.springframework.ai.transformer.splitter.TextSplitter;
import org.springframework.ai.transformer.splitter.TokenTextSplitter;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class LoadVectorStore implements CommandLineRunner {

    @Autowired
    VectorStore vectorStore;

    @Autowired
    VectorStoreProperties vectorStoreProperties;

    @Override
    public void run(String... args) throws Exception {
        log.debug("Loading vector store...");

        if (vectorStore.similaritySearch("Sportsmans").isEmpty()) {
            log.debug("Vector store is empty, loading documents...");
            vectorStoreProperties.getDocumentsToLoad().forEach(document -> {
                log.debug("Loading document: {}", document.getFilename());

                TikaDocumentReader documentReader = new TikaDocumentReader(document);
                List<Document> documents = documentReader.get();

                TextSplitter textSplitter = new TokenTextSplitter();

                List<Document> splitDocuments = textSplitter.apply(documents);

                vectorStore.add(splitDocuments);
            });
        } else {
            log.debug("Vector store already contains data, skipping load.");
        }

        log.debug("Vector store loaded successfully.");
    }
}
