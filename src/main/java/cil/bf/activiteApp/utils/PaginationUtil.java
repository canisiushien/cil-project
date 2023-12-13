package cil.bf.activiteApp.utils;

import java.text.MessageFormat;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.web.util.UriComponentsBuilder;

/**
 * Gestion de la pagination
 *
 * @author Canisius <canisiushien@gmail.com>
 */
public final class PaginationUtil {

    private static final String HEADER_X_TOTAL_COUNT = "X-Total-Count";
    private static final String HEADER_LINK_FORMAT = "<{0}>; rel=\"{1}\"";
    private static final String HEADER_X_PAGE_NUMBER = "X-Page-Number";
    private static final String HEADER_X_PAGE_SIZE = "X-Page-Size";
    private static final String HEADER_X_TOTAL_PAGE = "X-Total-Pages";

    private PaginationUtil() {
    }

    /**
     * @param size
     * @return
     */
    public static HttpHeaders getHeaders(int size) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_X_TOTAL_COUNT, Integer.toString(size));
        return headers;
    }

    /**
     * @param page
     * @return
     */
    public static HttpHeaders getHeaders(Page<?> page) {

        HttpHeaders headers = new HttpHeaders() {
            /**
             *
             */
            private static final long serialVersionUID = -1058785881293603457L;

            {
                add("Access-Control-Expose-Headers", HEADER_X_TOTAL_COUNT);
                add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));

                add("Access-Control-Expose-Headers", HEADER_X_PAGE_NUMBER);
                add(HEADER_X_PAGE_NUMBER, Integer.toString(page.getNumber()));

                add("Access-Control-Expose-Headers", HEADER_X_PAGE_SIZE);
                add(HEADER_X_PAGE_SIZE, Integer.toString(page.getSize()));

                add("Access-Control-Expose-Headers", HEADER_X_TOTAL_PAGE);
                add(HEADER_X_TOTAL_PAGE, Integer.toString(page.getTotalPages()));
            }
        };

        return headers;
    }

    /**
     * Generate pagination headers for a Spring Data {@link Page} object.
     *
     * @param uriBuilder The URI builder.
     * @param page The page.
     * @param <T> The type of object.
     * @return http header.
     */
    public static <T> HttpHeaders generatePaginationHttpHeaders(UriComponentsBuilder uriBuilder, Page<T> page) {
        HttpHeaders headers = new HttpHeaders();
        headers.add(HEADER_X_TOTAL_COUNT, Long.toString(page.getTotalElements()));
        int pageNumber = page.getNumber();
        int pageSize = page.getSize();
        StringBuilder link = new StringBuilder();
        if (pageNumber < page.getTotalPages() - 1) {
            link.append(prepareLink(uriBuilder, pageNumber + 1, pageSize, "next"))
                    .append(",");
        }
        if (pageNumber > 0) {
            link.append(prepareLink(uriBuilder, pageNumber - 1, pageSize, "prev"))
                    .append(",");
        }
        link.append(prepareLink(uriBuilder, page.getTotalPages() - 1, pageSize, "last"))
                .append(",")
                .append(prepareLink(uriBuilder, 0, pageSize, "first"));
        headers.add(HttpHeaders.LINK, link.toString());
        return headers;
    }

    private static String prepareLink(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize, String relType) {
        return MessageFormat.format(HEADER_LINK_FORMAT, preparePageUri(uriBuilder, pageNumber, pageSize), relType);
    }

    private static String preparePageUri(UriComponentsBuilder uriBuilder, int pageNumber, int pageSize) {
        return uriBuilder.replaceQueryParam("page", Integer.toString(pageNumber))
                .replaceQueryParam("size", Integer.toString(pageSize))
                .toUriString()
                .replace(",", "%2C")
                .replace(";", "%3B");
    }
}
