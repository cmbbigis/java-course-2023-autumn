package edu.java.client;

import edu.java.client.response.GitHubRepoResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Component
public class GitHubClient {
    private final WebClient gitHubWebClient;

    public GitHubClient(WebClient gitHubWebClient) {
        this.gitHubWebClient = gitHubWebClient;
    }

    public Mono<GitHubRepoResponse> fetchRepository(String user, String repo) {
        return gitHubWebClient.get()
            .uri(uriBuilder -> uriBuilder.path("/repos/{user}/{repo}").build(user, repo))
            .retrieve()
            .bodyToMono(GitHubRepoResponse.class);
    }
}
