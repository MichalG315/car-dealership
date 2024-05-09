package pl.zajavka.integration.rest;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import pl.zajavka.api.dto.CepikVehicleDTO;
import pl.zajavka.integration.configuration.RestAssuredIntegrationTestBase;
import pl.zajavka.integration.support.CepikControllerTestSupport;
import pl.zajavka.integration.support.WireMockTestSupport;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CepikIT
        extends RestAssuredIntegrationTestBase
        implements CepikControllerTestSupport, WireMockTestSupport {

    @Test
    void thatFindingRandomVehicleWorksCorrectly() {
        // given
        LocalDate dateFrom = LocalDate.of(2023, 1, 1);
        LocalDate dateTo = LocalDate.of(2024, 1, 1);
        stubForSlowniki(wireMockServer);
        stubForPojazdy(wireMockServer, dateFrom.toString(), dateTo.toString());
        stubForPojazd(wireMockServer);

        // when
        CepikVehicleDTO randomVehicle1 = getCepikRandomVehicle(dateFrom, dateTo);
        CepikVehicleDTO randomVehicle2 = getCepikRandomVehicle(dateFrom, dateTo);
        CepikVehicleDTO randomVehicle3 = getCepikRandomVehicle(dateFrom, dateTo);
        CepikVehicleDTO randomVehicle4 = getCepikRandomVehicle(dateFrom, dateTo);
        CepikVehicleDTO randomVehicle5 = getCepikRandomVehicle(dateFrom, dateTo);

        Set<CepikVehicleDTO> randomVehicles = new HashSet<>();
        randomVehicles.add(randomVehicle1);
        randomVehicles.add(randomVehicle2);
        randomVehicles.add(randomVehicle3);
        randomVehicles.add(randomVehicle4);
        randomVehicles.add(randomVehicle5);
        Assertions.assertThat(randomVehicles).hasSizeGreaterThan(1);

    }

}
