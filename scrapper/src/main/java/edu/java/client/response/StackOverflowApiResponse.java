package edu.java.client.response;

import java.util.List;
import lombok.Getter;

@Getter public class StackOverflowApiResponse {
    private List<StackOverflowQuestionResponse> items;
}
