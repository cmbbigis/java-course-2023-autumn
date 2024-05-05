package edu.java.bot.api;

import edu.java.bot.api.request.LinkUpdate;
import edu.java.bot.api.response.ApiErrorResponse;
import edu.java.bot.service.UpdateHandler;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/updates")
@RequiredArgsConstructor
public class BotController {

    private final UpdateHandler updateHandler;

    @Operation(summary = "Отправить обновление")
    @ApiResponses(value = {
        @ApiResponse(code = 200, message = "Обновление обработано"),
        @ApiResponse(code = 400, message = "Некорректные параметры запроса", response = ApiErrorResponse.class)
    })
    @PostMapping
    public ResponseEntity<?> postUpdate(@RequestBody LinkUpdate update) {
        updateHandler.handleUpdate(update);
        return ResponseEntity.ok().build();
    }
}
