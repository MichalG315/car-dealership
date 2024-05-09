package pl.zajavka.integration.support;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.matching.StringValuePattern;
import org.springframework.http.MediaType;
import wiremock.org.apache.http.HttpHeaders;

import java.util.Map;

import static com.github.tomakehurst.wiremock.client.WireMock.*;

public interface WireMockTestSupport {

    Map<String, String> VEHICLE_IDS = Map.of(
            "5545675395589858", "pojazd-1.json",
            "2009890057568575", "pojazd-2.json",
            "6196440748089697", "pojazd-3.json",
            "1108911639040750", "pojazd-4.json",
            "7055204331204764", "pojazd-5.json"
    );


    default void stubForSlowniki(WireMockServer wireMockServer) {
        wireMockServer
                .stubFor(get(urlPathMatching("/slowniki/wojewodztwa"))
                        .willReturn(aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("wiremock/slowniki-wojewodztwa.json")));
    }

    default void stubForPojazdy(WireMockServer wireMockServer, String dateFrom, String dateTo) {
        Map<String, StringValuePattern> queryParamsPattern = Map.of(
                "data-od", equalTo(dateFrom.replace("-", "")),
                "data-do", equalTo(dateTo.replace("-", ""))
        );
        wireMockServer
                .stubFor(get(urlPathEqualTo("/pojazdy"))
                        .withQueryParams(queryParamsPattern)
                        .willReturn(aResponse()
                                .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                                .withBodyFile("wiremock/pojazdy.json")));
    }

    default void stubForPojazd(WireMockServer wireMockServer) {
        VEHICLE_IDS.forEach((vehicleId, fileName) -> {
            wireMockServer.stubFor(get(urlPathEqualTo("/pojazdy/%s".formatted(vehicleId)))
                    .willReturn(aResponse()
                            .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                            .withBodyFile("wiremock/%s".formatted(fileName))));
        });
    }
}
