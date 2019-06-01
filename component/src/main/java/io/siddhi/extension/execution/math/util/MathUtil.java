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
package io.siddhi.extension.execution.math.util;

import io.siddhi.core.exception.SiddhiAppRuntimeException;
import io.siddhi.query.api.definition.Attribute;

/**
 * Util class for the extensions
 */
public class MathUtil {

    /**
     * Converts the input to double
     */
    public static double convertToDouble(Object data) {
        if (data instanceof Integer) {
            int inputInt = (Integer) data;
            return (double) inputInt;
        } else if (data instanceof Long) {
            long inputLong = (Long) data;
            return (double) inputLong;
        } else if (data instanceof Float) {
            float inputLong = (Float) data;
            return (double) inputLong;
        } else if (data instanceof Double) {
            return (Double) data;
        }
        throw new SiddhiAppRuntimeException("Invalid parameter type found as the input of math:atan() function," +
                "required " + Attribute.Type.INT + " or " + Attribute.Type.LONG +
                " or " + Attribute.Type.FLOAT + " or " + Attribute.Type.DOUBLE +
                ", but found " + data.getClass());
    }
}
