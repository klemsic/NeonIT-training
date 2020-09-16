package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.domain.kpi.Kpi;
import cz.neonit.klemsa.training.domain.kpi.KpiCounter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KpisController {

    @GetMapping("/kpis")
    public ResponseEntity<Kpi> kips(@RequestParam(value = "date", defaultValue = "") String date,
                                       @Autowired KpiCounter kpiCounter) {
        Kpi kpi = kpiCounter.getKpi();

        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }
}
