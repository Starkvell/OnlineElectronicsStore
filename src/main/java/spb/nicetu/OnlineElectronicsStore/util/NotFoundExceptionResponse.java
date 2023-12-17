package spb.nicetu.OnlineElectronicsStore.util;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;


@AllArgsConstructor
@Getter
@Setter
public class NotFoundExceptionResponse {
    private String message;
    private long timestamp;
}
