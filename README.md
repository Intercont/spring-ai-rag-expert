# Spring AI RAG Expert

This repository is a practical implementation of Spring AI with Java and Milvus Vector Database, using RAG (Retrieval Augmented Generation) to implement a truck recommendation system for towing boats. The boat data is fetched directly from the Yamaha site.

## Project Goals

- Demonstrate the integration of Spring AI and Milvus vector database in a Java application.
- Use Retrieval Augmented Generation (RAG) to enhance recommendations for truck and boat towing.
- Fetch real-world boat data from Yamaha for accurate recommendations.

## Technologies Used

- **Java 21**
- **Spring Boot 3.5.0**
- **Spring AI 1.0.0**
  - `spring-ai-starter-model-openai`
  - `spring-ai-starter-vector-store-milvus`
  - `spring-ai-tika-document-reader`
- **Milvus Vector Database**
- **Docker** (for containerizing Milvus)
- **Lombok** (for boilerplate code reduction)
- **Apache Tika** (for document reading)
- **Spring Boot DevTools** (development convenience)
- **Spring Boot Starter Web** (REST API)
- **Spring Boot Starter Test** (testing)

## Requirements

- **Java 21** or higher
- **Maven 3.8+**
- **Docker** installed and running on your system (for Milvus container)
- **OpenAI API key** for acessing OpenAI models with Spring AI

## Running Locally

1. **Clone the repository:**
    ```bash
    git clone https://github.com/Intercont/spring-ai-rag-expert.git
    cd spring-ai-rag-expert
    ```

2. **Start Milvus using Docker Compose:**
    - Make sure Docker is installed and running.
    - Start the required Docker containers for Milvus using the provided `docker-compose.yml`:
    ```bash
    docker compose up
    ```

3. **Configure OpenAI API key:**
    - You need to set the OpenAI API key as an environment variable instead of having it on your properties file:
      ```bash
      export OPENAI_API_KEY=YOUR_OPENAI_API_KEY
      ```

4. **Build the project:**
    ```bash
    mvn clean install
    ```

5. **Run the application:**
    ```bash
    mvn spring-boot:run
    ```

6. **Access the API:**
    - The application will start on [http://localhost:8080](http://localhost:8080) by default.

## Notes

- Ensure Milvus is running and accessible by your application.
- Lombok should be supported by your IDE for code generation features to work correctly.

## Contributing

Contributions are welcome! Feel free to open issues or submit pull requests.

## License

This project is licensed under standard open source terms. See [`pom.xml`](pom.xml) for details.

---
*Built with [Spring Boot](https://spring.io/projects/spring-boot) and [Spring AI](https://github.com/spring-projects/spring-ai).*
**Subscribe at [igorfragadev.com](https://igorfragadev.com) for more**
