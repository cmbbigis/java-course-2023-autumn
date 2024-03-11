package edu.java.client.response;

import lombok.Getter;
import java.util.List;

@Getter public class StackOverflowApiResponse {
    private List<StackOverflowQuestionResponse> items;
}
