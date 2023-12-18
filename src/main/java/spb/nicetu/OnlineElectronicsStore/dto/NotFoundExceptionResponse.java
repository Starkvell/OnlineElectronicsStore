package spb.nicetu.OnlineElectronicsStore.dto;

import lombok.*;

import java.io.Serializable;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NotFoundExceptionResponse implements Serializable {
    private String message;
    private long timestamp;
}
