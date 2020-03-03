package net.xerosoft.utils;

import net.xerosoft.dto.QuoteCreateDto;
import net.xerosoft.model.Quote;

public class MapUtil {
    public static Quote mapToEntity(QuoteCreateDto dto) {
        Quote quote = new Quote();
        quote.setQuote(dto.getQuote());
        quote.setAuthor(dto.getAuthor());
        return quote;
    }
}
