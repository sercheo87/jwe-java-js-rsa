# JWE Encryption between JAVA / NODE JS

## Introduction

Example of encryption and decryption with key *RSA* between app's run on **Java** and **Node JS**.

Dependencies relevant used:

| App   | Dependency                                                                              | Version |
|-------|-----------------------------------------------------------------------------------------|---------|
|Java   |[nimbus-jose-jwt](https://mvnrepository.com/artifact/com.nimbusds/nimbus-jose-jwt/8.5.1) | 8.5.1 |
|NodeJs |[jose](https://www.npmjs.com/package/jose/v/1.23.0)                                      | 1.23.0 |

## Use

Before execute put your files private and public key foder **keys**:

- private_key.der
- public_key.der

### Running Java APP - Getting JWK

 Generate JWK [more information.](https://tools.ietf.org/html/rfc7517)

Unix

```bash
$ cd app-java
$ ./gradlew run
```

Windows

```bash
$ cd app-java
$ gradlew.bat run
```

1.2 Example response

```
...log
2020-02-20T13:39:41,131 [ INFO] [           main] [o.e.App                                 ] [] JWK RSA: {"p":"7cfFEz_cdiYT7-V2qiuyZlR2yAaXeSzpLQAjRc41cITsxj5SsvsFVeaXfXi6easXjCbTgtgTvPIyiH6KTQAbnzowKFv7KaJpycsR5QtDYzR5XNyVUSBWlqKRezD-faZA5btUnNaVEAQuBgbzhXT-65j-0uaZoxtQEseu6A5VNDE","kty":"RSA","q":"zEQx1tW7RbiaEC_XGJdd5NJXETml2sleYdKSEw10Y7UoHGw7O1YszDkSqa8dnEwneeSuddGkv2uU8vGl2H0c-v8O3uIiWsv98uwknHX_BHvKzYMPOTbmylZlYjRo_xloHtXZueAB_cqwxLTvalHC8JA1nmfiHvYQie_A-_BWSmk","d":"F-7Ow_ZI6v0ZBhFZ2cgYbHVKaL_edtpxFBmVfnczaHM4Ty4cnKyZ_affTP5a5zEj56lnaIQz85aQYjVlHlDXYNwfACjNIkU69GFoPr8BdsfEtoZORftkJ4ShSedirusFMVFIBgRnOdfokJIw_5tM9LjmzfZHw_xGbFg47ifhfufsKYoEMqu6jA9KVSwJhLMC29CXf0tADwWfn_P-D8fkL-ZwSJg4LbWz0nLP_STxaau7pnaE_mnuJYh2rvg-nlcsRQkFwQ_6sOjNq2Igv4i8cW0vasoEcYB_I8AAcuncEk4oe-fRPird1Uua_QSSoEWpjQgQRTedU1nomyPAcsW2gQ","e":"AQAB","use":"sig","kid":"fff4231f-37bd-4594-8ccf-81bcbcc26266","qi":"tQrwtQ3yItyYSZddRak7eOmm-vBODxZ8XNjlpsx_G5EGDfoWCUIfTYBGlZKPg1rdOPAQrGDFPRJoYPQUNPc9Uyny2s9eAR1Auu94dgSHxxaBsF76JZuPG6QoN1nNaLTXUIRNb2xevLanjl93sPCg7MFU3EBMR4ELGW0R6ov12qk","dp":"lsxZ7OJUU83EWSEih7ucsY8hoaukhuH3o2Vx-URU2d5DN1qxIfdWo-rQ6m8VfDIIhgbdnx6gLfVAHRZ7aWgHHI1Pm1jySa5J_wM9fxAMla7ixQTT4ZZrs664KvOggllc0RVkDO99q6Gq-oGQEXRoc0wdV-2NREb_GlnvUBY-7RE","dq":"KDm9vtr0ODS0Uj-5kiQs32T0tHWvzbgeGBGJpqrdos0ICyDdQUAlLkqG_Fd580-j7oM0QAt68B9Vg8pGqK1qyZr06LuLnGnTpsUOKKmsAMwIEhSfKNtA89Ozv3UmwCXWtUvTxqDwg3Qo_3PKOhvD0e_0Py-h_grjEubeED8yHxE","n":"vbqIZng9Oq6W56QWw7Ths3Elv9iWFgOm5SwtNm39M8OzZrqY_7JrJuuvRCWHN3ZbYEDggS4fpCtaKtnMJzPMJyBLMgE3foJoKMNbI56GI-W5h4KMaTa41hCPo53qlKFG5IwiSgXsELHyNCvF9mjetjPSwPRw6X0WGSovVBlhuULh4koFM4j4N4xp8OYLXseSDzi5Qk52nzkN_UaEuwX0DdGhwvDMxzyGBrmuQthCBOgsK7fWEkLciBktsd2jkmVOVMjSmiuhYamPE6wuwIIKXE36IIP4iR_7AesCxSGYcBVc8oyLG6sHAB7e0cwShBhn5zv6EDTMuCOjSG7q-n6SGQ"}
...
```

Structure **JWK** generate base on private and public key with algorithm **RSA**.

```json
{
  "p": "7cfFEz_cdiYT7-V2qiuyZlR2yAaXeSzpLQAjRc41cITsxj5SsvsFVeaXfXi6easXjCbTgtgTvPIyiH6KTQAbnzowKFv7KaJpycsR5QtDYzR5XNyVUSBWlqKRezD-faZA5btUnNaVEAQuBgbzhXT-65j-0uaZoxtQEseu6A5VNDE",
  "kty": "RSA",
  "q": "zEQx1tW7RbiaEC_XGJdd5NJXETml2sleYdKSEw10Y7UoHGw7O1YszDkSqa8dnEwneeSuddGkv2uU8vGl2H0c-v8O3uIiWsv98uwknHX_BHvKzYMPOTbmylZlYjRo_xloHtXZueAB_cqwxLTvalHC8JA1nmfiHvYQie_A-_BWSmk",
  "d": "F-7Ow_ZI6v0ZBhFZ2cgYbHVKaL_edtpxFBmVfnczaHM4Ty4cnKyZ_affTP5a5zEj56lnaIQz85aQYjVlHlDXYNwfACjNIkU69GFoPr8BdsfEtoZORftkJ4ShSedirusFMVFIBgRnOdfokJIw_5tM9LjmzfZHw_xGbFg47ifhfufsKYoEMqu6jA9KVSwJhLMC29CXf0tADwWfn_P-D8fkL-ZwSJg4LbWz0nLP_STxaau7pnaE_mnuJYh2rvg-nlcsRQkFwQ_6sOjNq2Igv4i8cW0vasoEcYB_I8AAcuncEk4oe-fRPird1Uua_QSSoEWpjQgQRTedU1nomyPAcsW2gQ",
  "e": "AQAB",
  "use": "sig",
  "kid": "fff4231f-37bd-4594-8ccf-81bcbcc26266",
  "qi": "tQrwtQ3yItyYSZddRak7eOmm-vBODxZ8XNjlpsx_G5EGDfoWCUIfTYBGlZKPg1rdOPAQrGDFPRJoYPQUNPc9Uyny2s9eAR1Auu94dgSHxxaBsF76JZuPG6QoN1nNaLTXUIRNb2xevLanjl93sPCg7MFU3EBMR4ELGW0R6ov12qk",
  "dp": "lsxZ7OJUU83EWSEih7ucsY8hoaukhuH3o2Vx-URU2d5DN1qxIfdWo-rQ6m8VfDIIhgbdnx6gLfVAHRZ7aWgHHI1Pm1jySa5J_wM9fxAMla7ixQTT4ZZrs664KvOggllc0RVkDO99q6Gq-oGQEXRoc0wdV-2NREb_GlnvUBY-7RE",
  "dq": "KDm9vtr0ODS0Uj-5kiQs32T0tHWvzbgeGBGJpqrdos0ICyDdQUAlLkqG_Fd580-j7oM0QAt68B9Vg8pGqK1qyZr06LuLnGnTpsUOKKmsAMwIEhSfKNtA89Ozv3UmwCXWtUvTxqDwg3Qo_3PKOhvD0e_0Py-h_grjEubeED8yHxE",
  "n": "vbqIZng9Oq6W56QWw7Ths3Elv9iWFgOm5SwtNm39M8OzZrqY_7JrJuuvRCWHN3ZbYEDggS4fpCtaKtnMJzPMJyBLMgE3foJoKMNbI56GI-W5h4KMaTa41hCPo53qlKFG5IwiSgXsELHyNCvF9mjetjPSwPRw6X0WGSovVBlhuULh4koFM4j4N4xp8OYLXseSDzi5Qk52nzkN_UaEuwX0DdGhwvDMxzyGBrmuQthCBOgsK7fWEkLciBktsd2jkmVOVMjSmiuhYamPE6wuwIIKXE36IIP4iR_7AesCxSGYcBVc8oyLG6sHAB7e0cwShBhn5zv6EDTMuCOjSG7q-n6SGQ"
}
```

### Running Node APP

- On file **node-app/index.js** is defined in a const **_jwkRsa_** with value generated by **app-java**.

- Execute next command for encrypt text `Hello************************************************`

Unix

```bash
$ cd node-app
$ node index.js
```

Windows

```bash
$ cd node-app
$ node index.js
```

- This generate message encrypted how next example response:

```
....
-----------------------------------------------------------------
Data Encrypted: eyJlbmMiOiJBMTI4Q0JDLUhTMjU2IiwiYWxnIjoiUlNBLU9BRVAifQ.Y3zoth2zfHoVxKyFSwRE9cZLNy3HNe8ftyTYh7PBa8afe9AOXl_bEPBej-PxK1BC77ohyToHjz35k4TS7bNV1Zhvd8d-YIc_cnpMgVKlQCwgCqyEcfVcus5H2gVnDxwZ4B05EuRqz2mIKZgkMCUfcZy8yRA5T0gtlz9b7lQB30v3HF4fdJLcbODYqgfV9pR3SV3WvDnWOaxiOR8GNWDUVLAbrsBLPCsky8P3LgxLsjnH-
uILqP5FbvH93y01kupgm2pZz8mr1JQOitnnPjLScc5ydNteovufhaXJMgWHuM9w6gEzEFfRdq5DBikxuAQ9YOJ8tLuZsx2iQFh7qs7A4w.K7oRT1B4d4fHT2Jd_vJ4rg.ndpojuH49Xi6Y6MQTGSSyT5NfMjhgLt356awoViAcev_SfQrJVVUd-B_xD8DhthLYXRJLUHcMdpeG4y9wow0Gg.fy9wnnOglMJsejmpBy2BEA
....
```

### Running Java APP - Decrypting data

For test decrypt data generate by **node-app**, on file **app-java/src/test/java/org/example/AppTest.java** is defined the variable **_dataEncrypted_** with value generated by *app-java*.

- Execute next command for get decrypt text `Hello************************************************`

Unix

```bash
$ cd node-app
$ ./gradlew test
```

Windows

```bash
$ cd node-app
$ gradlew.bat test
``` 

And response example is:

```log
2020-02-20T14:21:11,837 [ INFO] [    Test worker] [o.e.App                                 ] [] dataDecrypted: Hello************************************************
```