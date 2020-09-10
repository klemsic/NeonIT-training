package cz.neonit.klemsa.training.api;

import cz.neonit.klemsa.training.domain.message.MessageInfo;
import cz.neonit.klemsa.training.domain.message.MessageStatus;
import cz.neonit.klemsa.training.domain.msisdn.MSISDN;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Metrics {

    @GetMapping("/metrics")
    public void metrics(@RequestParam(value = "date", defaultValue = "") String date) {
        return;
    }
}
