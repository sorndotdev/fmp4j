package dev.sorn.fmp4j.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serial;
import java.net.URI;
import java.time.LocalDateTime;

public record FmpNews(
        String symbol,
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime publishedDate,
        String publisher,
        String title,
        URI image,
        URI site,
        String text,
        URI url)
        implements FmpModel {
    @Serial
    private static final long serialVersionUID = 1L;
}
