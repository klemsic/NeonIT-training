package cz.neonit.klemsa.training.controller;

import cz.neonit.klemsa.training.domain.kpi.Kpi;
import cz.neonit.klemsa.training.service.KpiCounterService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KpisController {
    private final KpiCounterService kpiCounterService;

    public KpisController(KpiCounterService kpiCounterService) {
        this.kpiCounterService = kpiCounterService;
    }

    @GetMapping("/kpis")
    public ResponseEntity<Kpi> kips(@RequestParam(value = "date", defaultValue = "") String date) {
        Kpi kpi = kpiCounterService.getKpi();
        return new ResponseEntity<>(kpi, HttpStatus.OK);
    }
}
