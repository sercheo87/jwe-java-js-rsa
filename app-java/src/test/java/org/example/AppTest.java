package org.example;

import com.nimbusds.jose.JOSEException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;

class AppTest {
    private static final Logger LOGGER = LogManager.getLogger(App.class);

    @Test
    @DisplayName("Validate decrypt data")
    void validateDecryptData() throws NoSuchAlgorithmException, IOException, InvalidKeySpecException, ParseException, JOSEException {
        File filePrivateKey = new File("../keys/private_key.der");

        String dataEncrypted = "eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiUlNBLU9BRVAifQ.lN2FKtXjVYRc6HoG2RfI3fZ4x5sXNQzECeLAgtoiHAktLHQu6Gen1dlJkmav7yc67OqKEmIgZ1gH_oIP1m1aKxZJGbKQnUklHtjaXJ87xOYaj0x-KWXW-G9JYXwDdvtflv-dVJyW-Rx_ccT7ICE3uskJ8FMzWPl69N7C3fHr4JRn7fSeUxmnXHuuGnNmuVc8SkfWuPr68Cnq8xDqYvMmNg_pYsZvKHeQav1V_ZNP19cF4a2zC45xv0pzD6uNH-y7GqgTIYxRRfN7z3qjejWINFjPUBUUjvuiFP__q2oWJTtCFxSQ2VSEQuJAOVG8gh_96Z6Y64_IgQZW87m_qEtLYw.lsvLqOtAdTuH7MTygUJXtw.Xw0R1fhzs5qyhc7WwqkIgm0W5WPg-fV0oIkKPDemfwR7o9IHXID2xlQT4Yu2tvrVkJfQTe4IVnzinVBYsjHmfg.xTiWSTn_2Ktn6Al-JBjPQA";
        String dataDecrypted = App.decrypt(filePrivateKey, dataEncrypted);

        LOGGER.info("dataDecrypted: {}", dataDecrypted);
        Assertions.assertNotNull(dataDecrypted);
        Assertions.assertEquals("Hello************************************************", dataDecrypted);
    }
}