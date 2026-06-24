package com.gscience.crypto.functions.asymmetric.keypair;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class KeyPairStringResult {
    private String privateKey;
    private String publicKey;

}
