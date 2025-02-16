/*
 * Copyright 2019 Thunderberry.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package nl.altindag.ssl.trustmanager.validator;

import java.security.cert.X509Certificate;
import java.util.Objects;

/**
 * @author Hakan Altindag
 */
@FunctionalInterface
public interface ChainAndAuthTypeValidator {

    boolean test(X509Certificate[] certificateChain, String authType);

    default ChainAndAuthTypeValidator and(ChainAndAuthTypeValidator other) {
        Objects.requireNonNull(other);
        return (certificateChain, authType) -> test(certificateChain, authType) && other.test(certificateChain, authType);
    }

    default ChainAndAuthTypeValidator or(ChainAndAuthTypeValidator other) {
        Objects.requireNonNull(other);
        return (certificateChain, authType) -> test(certificateChain, authType) || other.test(certificateChain, authType);
    }

}
