package edu.java.api;

import edu.java.api.request.AddLinkRequest;
import edu.java.api.request.RemoveLinkRequest;
import edu.java.api.response.ApiErrorResponse;
import edu.java.api.response.LinkResponse;
import edu.java.api.response.ListLinksResponse;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class ScrapperController {

    @Operation(summary = "Зарегистрировать чат")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Чат зарегистрирован"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @PostMapping("/tg-chat/{id}")
    public ResponseEntity<?> registerChat(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Удалить чат")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Чат успешно удалён"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @DeleteMapping("/tg-chat/{id}")
    public ResponseEntity<?> deleteChat(@PathVariable long id) {
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Получить все отслеживаемые ссылки")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ссылки успешно получены"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @GetMapping("/links")
    public ResponseEntity<ListLinksResponse> getAllLinks(@RequestHeader("Tg-Chat-Id") long chatId) {
        var response = new ListLinksResponse();
        response.setLinks(new ArrayList<>(List.of(new LinkResponse())));
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Добавить отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ссылка успешно добавлена"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @PostMapping("/links")
    public ResponseEntity<LinkResponse> addLink(@RequestHeader("Tg-Chat-Id") long chatId, @RequestBody
    AddLinkRequest request) throws URISyntaxException {
        var response = new LinkResponse();
        response.setId(1);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Убрать отслеживание ссылки")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Ссылка успешно убрана"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @DeleteMapping("/links")
    public ResponseEntity<LinkResponse> removeLink(@RequestHeader("Tg-Chat-Id") long chatId, @RequestBody
    RemoveLinkRequest request) {
        var response = new LinkResponse();
        response.setId(1);
        return ResponseEntity.ok(response);
    }
}
