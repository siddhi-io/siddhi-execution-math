/*
 * Copyright (c)  2016, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
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

import io.siddhi.core.util.snapshot.state.State;
/**
 * This PercentileAttributeState interface to implement parse value functionality
 */
public abstract class PercentileAttributeState extends State {

    public abstract Object processAdd(Object data);

    public abstract Object processRemove(Object obj);

    public abstract Object reset();

    public abstract Object currentValue();
}
