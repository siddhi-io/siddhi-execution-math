/*
 * Copyright (c)  2019, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.extension.siddhi.execution.math.util;

import org.wso2.siddhi.core.exception.SiddhiAppRuntimeException;
import org.wso2.siddhi.query.api.definition.Attribute;

/**
 * Util class for the extensions
 */
public class MathUtil {

    /**
     * Converts the input to double
     * @param data Input for the conversion
     * @return     Double value
     */
    public static double convertToDouble(Object data) {
        if (data instanceof Integer) {
            return ((Integer) data).doubleValue();
        } else if (data instanceof Long) {
            return ((Long) data).doubleValue();
        } else if (data instanceof Float) {
            return ((Float) data).doubleValue();
        } else if (data instanceof Double) {
            return (Double) data;
        }
        throw new SiddhiAppRuntimeException("Failed to convert to double, "
                + "invalid parameter type:" + data.getClass() + " . Only supports converting " + Attribute.Type.INT
                + ", " + Attribute.Type.LONG + ", " + Attribute.Type.FLOAT + "and " + Attribute.Type.DOUBLE
                + "types to double.");
    }
}
