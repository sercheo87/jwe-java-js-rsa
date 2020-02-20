const jose = require('jose');

console.log('Start app');

const jwkRsa = {
    "p": "7cfFEz_cdiYT7-V2qiuyZlR2yAaXeSzpLQAjRc41cITsxj5SsvsFVeaXfXi6easXjCbTgtgTvPIyiH6KTQAbnzowKFv7KaJpycsR5QtDYzR5XNyVUSBWlqKRezD-faZA5btUnNaVEAQuBgbzhXT-65j-0uaZoxtQEseu6A5VNDE",
    "kty": "RSA",
    "q": "zEQx1tW7RbiaEC_XGJdd5NJXETml2sleYdKSEw10Y7UoHGw7O1YszDkSqa8dnEwneeSuddGkv2uU8vGl2H0c-v8O3uIiWsv98uwknHX_BHvKzYMPOTbmylZlYjRo_xloHtXZueAB_cqwxLTvalHC8JA1nmfiHvYQie_A-_BWSmk",
    "d": "F-7Ow_ZI6v0ZBhFZ2cgYbHVKaL_edtpxFBmVfnczaHM4Ty4cnKyZ_affTP5a5zEj56lnaIQz85aQYjVlHlDXYNwfACjNIkU69GFoPr8BdsfEtoZORftkJ4ShSedirusFMVFIBgRnOdfokJIw_5tM9LjmzfZHw_xGbFg47ifhfufsKYoEMqu6jA9KVSwJhLMC29CXf0tADwWfn_P-D8fkL-ZwSJg4LbWz0nLP_STxaau7pnaE_mnuJYh2rvg-nlcsRQkFwQ_6sOjNq2Igv4i8cW0vasoEcYB_I8AAcuncEk4oe-fRPird1Uua_QSSoEWpjQgQRTedU1nomyPAcsW2gQ",
    "e": "AQAB",
    // "use": "sig",
    "kid": "fd4a6dcc-4fc7-43a9-a310-80991e76ef7f",
    "qi": "tQrwtQ3yItyYSZddRak7eOmm-vBODxZ8XNjlpsx_G5EGDfoWCUIfTYBGlZKPg1rdOPAQrGDFPRJoYPQUNPc9Uyny2s9eAR1Auu94dgSHxxaBsF76JZuPG6QoN1nNaLTXUIRNb2xevLanjl93sPCg7MFU3EBMR4ELGW0R6ov12qk",
    "dp": "lsxZ7OJUU83EWSEih7ucsY8hoaukhuH3o2Vx-URU2d5DN1qxIfdWo-rQ6m8VfDIIhgbdnx6gLfVAHRZ7aWgHHI1Pm1jySa5J_wM9fxAMla7ixQTT4ZZrs664KvOggllc0RVkDO99q6Gq-oGQEXRoc0wdV-2NREb_GlnvUBY-7RE",
    "dq": "KDm9vtr0ODS0Uj-5kiQs32T0tHWvzbgeGBGJpqrdos0ICyDdQUAlLkqG_Fd580-j7oM0QAt68B9Vg8pGqK1qyZr06LuLnGnTpsUOKKmsAMwIEhSfKNtA89Ozv3UmwCXWtUvTxqDwg3Qo_3PKOhvD0e_0Py-h_grjEubeED8yHxE",
    "n": "vbqIZng9Oq6W56QWw7Ths3Elv9iWFgOm5SwtNm39M8OzZrqY_7JrJuuvRCWHN3ZbYEDggS4fpCtaKtnMJzPMJyBLMgE3foJoKMNbI56GI-W5h4KMaTa41hCPo53qlKFG5IwiSgXsELHyNCvF9mjetjPSwPRw6X0WGSovVBlhuULh4koFM4j4N4xp8OYLXseSDzi5Qk52nzkN_UaEuwX0DdGhwvDMxzyGBrmuQthCBOgsK7fWEkLciBktsd2jkmVOVMjSmiuhYamPE6wuwIIKXE36IIP4iR_7AesCxSGYcBVc8oyLG6sHAB7e0cwShBhn5zv6EDTMuCOjSG7q-n6SGQ"
};

const jwkPrivatePublic = jose.JWK.asKey(jwkRsa);

const dataEncrypted = jose.JWE.encrypt(
    'Hello************************************************',
    jwkPrivatePublic
);

console.log('\n\n-----------------------------------------------------------------');
console.log('Data Encrypted:', dataEncrypted);

console.log('\n\n-----------------------------------------------------------------');
console.log('Data Decrypted', jose.JWE.decrypt(dataEncrypted, jwkPrivatePublic).toString('ascii'));